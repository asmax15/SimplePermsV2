package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.permissions.PermissionManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetPermissionCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.get")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permission to do that.")
            return true
        }

        if (args.size != 2) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spget <user> <permission>")
            return true
        }

        var user = Bukkit.getPlayer(args[0])

        if (user == null) {
            player.sendMessage("${SimplePerms.instance.error} The player is not online.")
            return true
        }

        var havePermission = PermissionManager.getPermission(user, args[1])

        if (havePermission) {
            player.sendMessage("${SimplePerms.instance.prefix} §aThe player: §6${args[0]} §a§lhas §r§athe Permission: §6${args[1]}")
        } else {
            player.sendMessage("${SimplePerms.instance.prefix} §aThe player: §6${args[0]} §a§lhasn't §r§athe Permission: §6${args[1]}")
        }

        return true
    }
}