package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.permissions.PermissionManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AddTempPermissionCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.add.temp")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permission to do that.")
            return true
        }

        if (args.size != 3) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/sptemp <user> <permission> <time>")
            return true
        }

        var user = Bukkit.getPlayer(args[0])

        if (user == null) {
            player.sendMessage("${SimplePerms.instance.error} The player is not online.")
            return true
        }

        if (user.hasPermission(args[1])) {
            player.sendMessage("${SimplePerms.instance.error} The player has already the permission: §4${args[1]}")
            return true
        }

        try {

            var time = args[2].toLong()

            PermissionManager.setTempPermission(user, args[1], time)

        } catch (e: NumberFormatException) {
            player.sendMessage("${SimplePerms.instance.error} Please use a NUMBER for the TIME")
        }

        return true
    }
}