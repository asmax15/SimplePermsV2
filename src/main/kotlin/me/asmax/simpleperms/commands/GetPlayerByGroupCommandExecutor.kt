package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetPlayerByGroupCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.groups.player.get")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permission to do that.")
            return true
        }

        if (args.size != 2) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spgplget <player> <group>")
            return true
        }

        var user = Bukkit.getPlayer(args[0])

        if (user == null) {
            player.sendMessage("${SimplePerms.instance.error} The player is not online.")
            return true
        }

        if (!GroupManager.getGroup(args[1])) {
            player.sendMessage("${SimplePerms.instance.error} The Group §4${args[1]} §fdoesn't exists.")
            return true
        }

        if (GroupManager.getPlayerGroup(user, args[1])) {
            player.sendMessage("${SimplePerms.instance.prefix} §aThe Player: §6${args[0]} §2is in §athe Group: §b${args[1]}")
        } else {
            player.sendMessage("${SimplePerms.instance.prefix} §aThe Player: §6${args[0]} §cisn't in §athe Group: §b${args[1]}")
        }

        return true
    }
}