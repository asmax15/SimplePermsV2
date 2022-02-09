package me.asmax.simpleperms.commands.override

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.permissions.PermissionManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class DeopCommandPreprocess: Listener {

    @EventHandler
    fun handleCommand(event: PlayerCommandPreprocessEvent) {
        var message = event.message
        var player: Player = event.player

        if (message.equals("/deop", true)) {
            event.isCancelled = true
            PermissionManager.removePermission(player, "*")
            PermissionManager.savePlayerPermissions(player, "*")
            player.sendMessage("${SimplePerms.instance} §4You have been successfully deoped!")
        }
    }
}