package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetPlayerGroupCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.groups.player.add")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permissions to do that.")
            return true
        }

        if (args.size != 2) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spgpladd <player> <group>")
            return true
        }

        var user = Bukkit.getPlayer(args[0])

        if (!GroupManager.getGroup(args[1])) {
            player.sendMessage("${SimplePerms.instance.error} The Group §4${args[1]} §fdoesn't exists.")
            return true
        }

        if (user == null) {
            player.sendMessage("${SimplePerms.instance.error} The Player §4${args[0]} §fisn't online.")
            return true
        }

        if (GroupManager.getPlayerGroup(user, args[1])) {
            player.sendMessage("${SimplePerms.instance.error} The Player §4${args[0]} §fis already in the Group: §b${args[1]}")
            return true
        }

        GroupManager.addPlayerToGroup(user, args[1])
        user.kickPlayer("${SimplePerms.instance.prefix} §2You're Group has been updated!")
        player.sendMessage("${SimplePerms.instance.prefix} §aYou have successfully §2added §6${args[0]} §ato Group: §b${args[1]}")
        return true
    }
}