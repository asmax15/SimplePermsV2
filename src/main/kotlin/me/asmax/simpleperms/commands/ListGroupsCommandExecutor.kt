package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ListGroupsCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.groups.list")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permission to do that.")
            return true
        }

        if (args.isNotEmpty()) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spglist")
            return true
        }

        var groups = GroupManager.listGroups()
        player.sendMessage("${SimplePerms.instance.prefix} §aGroups: §6$groups")
        return true
    }
}