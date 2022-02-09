package me.asmax.simpleperms.permissions

import me.asmax.simpleperms.SimplePerms
import me.asmax.simpleperms.groups.GroupManager
import me.asmax.simpleperms.utils.Config
import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionAttachment

object PermissionManager {

    fun setPermission(player: Player, permission: String) {
        var attachment: PermissionAttachment = player.addAttachment(SimplePerms.instance)
        attachment.setPermission(permission, true)
    }

    fun revokePermissions(player: Player) {
        var UUID: String = player.uniqueId.toString()

        if (Config.getConfig().get("Player.${UUID}.permissions") == null) {
            return
        }

        var permissions: List<String> = Config.getConfig().getList("Player.${UUID}.permissions") as List<String>

        permissions.forEach {
            var permission = it

            var attachment = player.addAttachment(SimplePerms.instance)
            attachment.setPermission(permission, true)
        }
    }

    fun revokeGroups(player: Player) {
        var totalGroups = Config.getConfig().getConfigurationSection("Groups")!!.getKeys(false)
        var groupString: StringBuilder = StringBuilder(totalGroups.toString())
        groupString.delete(0, 10)
        var test = groupString.toString()
        test = StringUtils.removeEnd(test, "]")
        test = StringUtils.deleteWhitespace(test)

        var totalGroupList = listOf(test.split(",")) as List<String>

        totalGroupList.forEach {

            try {

                if (GroupManager.getPlayerGroup(player, it)) {

                    var tempPermissionList: List<String> = Config.getConfig().getList("Groups.${it}.permissions") as List<String>

                    tempPermissionList.forEach { permission ->
                        var attachment = player.addAttachment(SimplePerms.instance)
                        attachment.setPermission(permission, true)
                    }

                }

            } catch (e: NullPointerException) {
                Bukkit.getLogger().info("${SimplePerms.instance.prefix} NullPointerException in Group Management System!")
            }
        }
    }

    fun setTempPermission(player: Player, permission: String, time: Long) {
        var now = System.currentTimeMillis()
        var dif = (time -now) / 1000
        var available = dif*20 as Int
        var attachment = player.addAttachment(SimplePerms.instance)
        attachment.setPermission(permission, false)
    }

    fun removePermission(player: Player, permission: String) {
        var attachment = player.addAttachment(SimplePerms.instance)
        attachment.setPermission(permission, false)
    }

    fun getPermission(player: Player, permission: String): Boolean {
        return player.hasPermission(permission)
    }

    fun savePlayerPermissions(player: Player, permission: String) {
        if (Config.getConfig().get("Player.${player.uniqueId}.permissions") == null) {
            var tempPlayerPerms = mutableListOf<String>()
            tempPlayerPerms.add("default")
            Config.getConfig().set("Player.${player.uniqueId}.permissions", tempPlayerPerms)
            Config.save()
        }

        var playerPerms = Config.getConfig().getList("Player.${player.uniqueId}.permissions") as MutableList<String>
        playerPerms.add(permission)
        Config.getConfig().set("Player.${player.uniqueId}.permissions", playerPerms)
        Config.save()
    }

    fun removeSavedPlayerPermissions(player: Player, permission: String) {
        if (Config.getConfig().get("Player.${player.uniqueId}.permissions") == null) {
            Bukkit.getLogger().warning("ABORT!")
            return
        }

        var playerPerms = Config.getConfig().getList("Player.${player.uniqueId}.permissions") as MutableList<String>
        playerPerms.remove(permission)
        Config.getConfig().set("Player.${player.uniqueId}.permissions", playerPerms)
        Config.save()
    }
}