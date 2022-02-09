package me.asmax.simpleperms.listener

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import me.asmax.simpleperms.utils.Config
import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener: Listener {

    @EventHandler
    fun handleJoin(event: PlayerJoinEvent) {
        var player: Player = event.player
        var UUID = player.uniqueId.toString()

        if (Config.getConfig().get("Player.${UUID}.permissions") == null) {
            return
        }

        var permissions = Config.getConfig().getList("Player.${UUID}.permissions") as List<String>

        permissions.forEach {
            var attachment = player.addAttachment(SimplePerms.instance)
            attachment.setPermission(it, true)
        }

        var totalGroups = Config.getConfig().getConfigurationSection("Groups")?.getKeys(false)
        var groupString: StringBuilder = StringBuilder(totalGroups.toString())
        groupString.delete(0, 10)
        var test = groupString.toString()
        test = StringUtils.removeEnd(test, "]")
        test = StringUtils.deleteWhitespace(test)

        var totalGroupList = listOf(test.split(",")) as List<String>

        totalGroupList.forEach { group ->
            try {

                if (GroupManager.getPlayerGroup(player, group)) {

                    var tempPermissionList = Config.getConfig().getList("Groups.${group}.permissions") as List<String>

                    tempPermissionList.forEach { permission ->
                        var attachment = player.addAttachment(SimplePerms.instance)
                        attachment.setPermission(permission, true)
                    }

                }

            } catch (e: NullPointerException) {
                Bukkit.getLogger().warning("NullPointerException in Group Management System! If the Permission System work properly you can ignore this warning.")
            }
        }
    }
}