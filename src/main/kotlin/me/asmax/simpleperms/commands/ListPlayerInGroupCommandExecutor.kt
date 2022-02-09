package me.asmax.simpleperms.commands

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class ListPlayerInGroupCommandExecutor: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${SimplePerms.instance.error} Just a player can execute this command.")
            return true
        }
        var player: Player = sender

        if (!player.hasPermission("simpleperms.groups.player.list")) {
            player.sendMessage("${SimplePerms.instance.error} You don't have the permission to do that.")
            return true
        }

        if (args.isEmpty()) {
            player.sendMessage("${SimplePerms.instance.error} Please use: §9/spgpllist <group>")
            return true
        } else if (args.size == 2 && args[1].equals("offlinemode", true)) {

            if (!GroupManager.getGroup(args[0])) {
                player.sendMessage("${SimplePerms.instance.error} The Group §4${args[0]} §f doesn't exists.")
                return true
            }
            var playerList = GroupManager.listPlayers(args[0]) as ArrayList<String>
            player.sendMessage("${SimplePerms.instance.prefix} §aIn the group: §b${args[0]} §aare the following Players: §6$playerList")
            return true
        }

        if (!GroupManager.getGroup(args[0])) {
            player.sendMessage("${SimplePerms.instance.error} The Group §4${args[0]} §f doesn't exists.")
            return true
        }

        var playerList = GroupManager.listPlayers(args[0]) as ArrayList<String>
        var playerListDecompiled = mutableListOf<String>() as ArrayList<String>

        var offline = 0

        playerList.forEach { playerUUID ->

            if (!(playerUUID.equals("DoNotTouchThis", true))) {

                var uuid = UUID.fromString(playerUUID)

                var user = Bukkit.getPlayer(uuid)

                if (user == null) {
                    offline++
                    return@forEach
                }

                var name = user.name
                playerListDecompiled.add(name)

            }
        }

        player.sendMessage("${SimplePerms.instance.prefix} §aIn the group: §b${args[0]} §aare the following Players: §6$playerListDecompiled")

        if (offline != 0) {
            player.sendMessage("§7[NOTE] §8§o$offline players are offline, try: §r§7/spgpllist <group> offlinemode, to get the raw uuid's.")
        }
        return true
    }
}