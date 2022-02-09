package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AddGroupCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a Player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.groups.add")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the Permission to do that.")
            return true
        }

        if (args.size != 2) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spgadd <name> <level>")
            return true
        }

        try {

            var level = args[1].toInt()

            if (GroupManager.getGroup(args[0])) {
                player.sendMessage("${SimplePerms.instance.error} The group §4${args[0]} §falready exists.")
                return true
            }

            GroupManager.addGroup(args[0], level)
            player.sendMessage("${SimplePerms.instance.prefix} §aYou have successfully created the group: §6${args[0]}")
        } catch (e: NumberFormatException) {
            player.sendMessage("${SimplePerms.instance.error} Please use a NUMBER as 3th argument!")
        }

        return true
    }
}