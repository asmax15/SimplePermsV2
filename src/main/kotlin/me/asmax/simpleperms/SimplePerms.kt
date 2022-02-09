package me.asmax.simpleperms

import me.asmax.simpleperms.commands.*
import me.asmax.simpleperms.groups.GroupManager
import me.asmax.simpleperms.listener.JoinListener
import me.asmax.simpleperms.permissions.PermissionManager
import me.asmax.simpleperms.utils.Config
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class SimplePerms : JavaPlugin() {

    val prefix = "§8[§9SimplePerms§8]§7"
    val error = "§8[§4ERROR§8]§f"

    companion object {
        lateinit var instance: SimplePerms
        private set
    }

    override fun onLoad() {
        instance = this

        Config.Config()
    }

    override fun onEnable() {
        registerCommands()
        registerListener()
        GroupManager.copyDefaultGroup()

        initPlayerPermissions()
        initPlayerGroups()
    }

    override fun onDisable() {
        Config.save()
    }

    private fun registerCommands() {
        val addPermissionCommand = getCommand("spadd") ?: error("Couldn't get info command! This should not happen!")
        val removePermissionCommand = getCommand("spremove") ?: error("Couldn't get info command! This should not happen!")
        val addTempPermission = getCommand("sptemp") ?: error("Couldn't get info command! This should not happen!")
        val getPermissionCommand = getCommand("spget") ?: error("Couldn't get info command! This should not happen!")
        val addGroupPermissionCommand = getCommand("spgpadd") ?: error("Couldn't get info command! This should not happen!")
        val addGroupCommand = getCommand("spgadd") ?: error("Couldn't get info command! This should not happen!")
        val removeGroupPermissionCommand = getCommand("spgprem") ?: error("Couldn't get info command! This should not happen!")
        val deleteGroupCommand = getCommand("spgrem") ?: error("Couldn't get info command! This should not happen!")
        val listGroupsCommand = getCommand("spglist") ?: error("Couldn't get info command! This should not happen!")
        val listPermissionsCommand = getCommand("spgplist") ?: error("Couldn't get info command! This should not happen!")
        val setPlayerGroupCommand = getCommand("spgpladd") ?: error("Couldn't get info command! This should not happen!")
        val unsetPlayerGroupCommand = getCommand("spgplrem") ?: error("Couldn't get info command! This should not happen!")
        val getPlayerByGroupCommand = getCommand("spgplget") ?: error("Couldn't get info command! This should not happen!")
        val listPlayerInGroupCommand = getCommand("spgpllist") ?: error("Couldn't get info command! This should not happen!")

        addPermissionCommand.setExecutor(AddPermissionCommandExecutor())
        removePermissionCommand.setExecutor(RemovePermissionCommandExecutor())
        addTempPermission.setExecutor(AddTempPermissionCommandExecutor())
        getPermissionCommand.setExecutor(GetPermissionCommandExecutor())
        addGroupPermissionCommand.setExecutor(AddGroupPermissionCommandExecutor())
        addGroupCommand.setExecutor(AddGroupCommandExecutor())
        removeGroupPermissionCommand.setExecutor(RemoveGroupPermissionCommandExecutor())
        deleteGroupCommand.setExecutor(DeleteGroupCommandExecutor())
        listGroupsCommand.setExecutor(ListGroupsCommandExecutor())
        listPermissionsCommand.setExecutor(ListPermissionCommandExecutor())
        setPlayerGroupCommand.setExecutor(SetPlayerGroupCommandExecutor())
        unsetPlayerGroupCommand.setExecutor(UnsetPlayerGroupCommandExecutor())
        getPlayerByGroupCommand.setExecutor(GetPlayerByGroupCommandExecutor())
        listPlayerInGroupCommand.setExecutor(ListPlayerInGroupCommandExecutor())
    }

    private fun registerListener() {
        val pluginManager = Bukkit.getPluginManager()

        pluginManager.registerEvents(JoinListener(), this)
    }

    private fun initPlayerPermissions() {
        Bukkit.getOnlinePlayers().forEach { player ->
            if (player != null) {
                PermissionManager.revokePermissions(player)
            }
        }
    }

    private fun initPlayerGroups() {
        Bukkit.getOnlinePlayers().forEach { player ->
            if (player != null) {
                PermissionManager.revokeGroups(player)
            }
        }
    }

}