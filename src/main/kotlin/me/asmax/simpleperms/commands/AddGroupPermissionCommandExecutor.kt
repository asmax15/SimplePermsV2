package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AddGroupPermissionCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.groups.permission.add")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permission to do that.")
            return true
        }

        if (args.size != 2) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spgpadd <group> <permission>")
            return true
        }

        if (!GroupManager.getGroup(args[0])) {
            player.sendMessage("${SimplePerms.instance.error} The group §4${args[0]} §fdoesn't exists.")
            return true
        }

        GroupManager.addPermission(args[0], args[1])
        player.sendMessage("${SimplePerms.instance.prefix} §aYou have successfully §2added §athe permission: §6${args[1]} §ato the Group: §6${args[0]}")

        return true
    }
}