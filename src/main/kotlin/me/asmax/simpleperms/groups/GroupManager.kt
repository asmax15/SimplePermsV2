package me.asmax.simpleperms.groups

import me.asmax.simpleperms.utils.Config
import org.bukkit.entity.Player

object GroupManager {

    fun addGroup(group: String, level: Int) {
        var tempList = mutableListOf<String>()
        var playerTempList = mutableListOf<String>()
        tempList.add("spacer.space")
        playerTempList.add("DoNotTouchThis")

        Config.getConfig().set("Groups.${group}.level", level)
        Config.getConfig().set("Groups.${group}.permissions", tempList)
        Config.getConfig().set("Groups.${group}.players", playerTempList)
        Config.save()
    }

    fun addPlayerToGroup(player: Player, group: String) {
        if (!getGroup(group)) {
            return
        }

        var UUID = player.uniqueId.toString()

        var playerList: MutableList<String>? = (Config.getConfig().getList("Groups.${group}.players") ?: return) as MutableList<String>?

        playerList?.add(UUID)

        Config.getConfig().set("Groups.${group}.players", playerList)
        Config.save()
    }

    fun removePlayerFromGroup(player: Player, group: String) {
        var UUID = player.uniqueId.toString()
        var playerList = Config.getConfig().getList("Groups.${group}.players")

        if (!playerList!!.contains(UUID)) {
            return
        }

        playerList.remove(UUID)
        Config.getConfig().set("Groups.${group}.players", playerList)
        Config.save()
    }

    fun getPlayerGroup(player: Player, group: String): Boolean {
        var UUID = player.uniqueId.toString()
        var playerList = Config.getConfig().getList("Groups.${group}.players")

        return playerList!!.contains(UUID)
    }

    fun addPermission(group: String, permission: String) {
        var groupPermissionList = Config.getConfig().getList("Groups.${group}.permissions") as ArrayList<String>

        if (Config.getConfig().get("Groups.$group") == null) {
            return
        }

        groupPermissionList.add(permission)
        Config.getConfig().set("Groups.${group}.permissions", groupPermissionList)
        Config.save()
    }

    fun listPlayers(group: String): List<String> {
        return Config.getConfig().getList("Groups.${group}.players") as List<String>
    }

    fun copyDefaultGroup() {

        if (Config.getConfig().get("Groups.default") != null) {
            return
        }

        Config.getConfig().set("Groups.default.level", 1)
        Config.save()
    }

    fun removePermission(group: String, permission: String) {
        var groupPermissionList = Config.getConfig().getList("Groups.${group}.permissions") as ArrayList<String>

        if (Config.getConfig().get("Groups.$group") == null) {
            return
        }

        if (!groupPermissionList.contains(permission)) {
            return
        }

        groupPermissionList.remove(permission)
        Config.getConfig().set("Groups.${group}.permissions", groupPermissionList)
        Config.save()
    }

    fun getPermission(group: String, permission: String): Boolean {
        var groupPermissionList = Config.getConfig().getList("Groups.${group}.permissions") as ArrayList<String>

        if (Config.getConfig().get("Groups.$group") == null) {
            return false
        }

        if (groupPermissionList.contains(permission)) {
            return true
        } else {
            return false
        }
    }

    fun getGroup(name: String): Boolean {
        return Config.getConfig().get("Groups.$name") != null
    }

    fun removeGroup(name: String) {
        if (Config.getConfig().get("Groups.$name") == null) {
            return
        }

        Config.getConfig().set("Groups.$name", null)
        Config.save()
    }

    fun listGroups(): String {
        if (Config.getConfig().get("Groups") == null) {
            return "error"
        }

        return Config.getConfig().getConfigurationSection("Groups")?.getKeys(false).toString()
    }

    fun listPermissions(group: String): String {
        if (Config.getConfig().get("Groups.$group") == null) {
            return "error"
        }

        var groupPermissionList = Config.getConfig().getList("Groups.${group}.permissions")

        var permissions = groupPermissionList.toString()
        return permissions
    }
}