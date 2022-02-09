package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RemoveGroupPermissionCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.groups.permission.remove")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permission to do that.")
            return true
        }

        if (args.size != 2) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spgpremove <group> <permission>")
            return true
        }

        if (!GroupManager.getGroup(args[0])) {
            player.sendMessage("${SimplePerms.instance.error} The group §4${args[0]} §fdoesn't exists.")
            return true
        }

        if (!GroupManager.getPermission(args[0], args[1])) {
            player.sendMessage("${SimplePerms.instance.error} The Group: §9${args[0]} §fhas not the Permission: §9${args[1]}")
            return true
        }

        GroupManager.removePermission(args[0], args[1])
        player.sendMessage("${SimplePerms.instance.prefix} §aYou have successfully §cremoved §athe permission: §6${args[1]} §afrom the Group: §6${args[0]}")
        return true
    }
}