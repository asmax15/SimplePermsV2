# SimplePermsV2

This repository contains the code for the SimplePerms Minecraft server
plugin. SimplePerms allows you to manage the players permissions in a simple way.
This plugin also features groups.

## Commands
* `/spadd <user> <permission>` - *Add a permission to a specific player*
* `/spremove <user> <permission>` - *Remove a permission from a specific player*
* `/sptemp <user> <permission> <time>` - *Gives a permission to a player for a limited time*
* `/spget <user> <permission>` - *See if a player has a specific permission*
* `/spgadd <name> <level>` - *Creates a new group with a new security level*
* `/spgpadd <group> <permission>` - *Adds a permission to a specific group*
* `/spgpremove <group> <permission>` - *Removes a permission from a specific group*
* `/spgdel <name>` - *Deletes a specific group entirely*
* `/spglist` - *Displays you all currently available groups*
* `/spgplist <group>` - *Shows all permissions that are assigned to a specific group*
* `/spgpladd <player> <group>` - *Adds a player to a specific group*
* `/spgplrem <player> <group>` - *Removes a player from a specific group*
* `/spgplget <player> <group>` - *See if a player is in a specific group*
* `/spgpllist <group> ["offlinemode"]` - *Displays all the players that are in a specific group. If players are offline, use the second parameter*

## Permissions
* `simpleperms.groups.add` - Allows you to use the **/spgadd** command.
* `simpleperms.groups.permission.add` - Allows you to use the **/spgpadd** command.
* `simpleperms.add` - Allows you to use the **/spadd** command.
* `simpleperms.add.temp` - Allows you to use the **/sptemp** command.
* `simpleperms.groups.delete` - Allows you to use the **/spgdel** command.
* `simpleperms.get` - Allows you to use the **/spget** command.
* `simpleperms.groups.player.get` - Allows you to use the **/spgplget** command.
* `simpleperms.groups.list` - Allows you to use the **/spglist** command.
* `simpleperms.groups.permissions.list` - Allows you to use the **/spgplist** command.
* `simpleperms.groups.player.list` - Allows you to use the **/spgpllist** command.
* `simpleperms.groups.permission.remove` - Allows you to use the **/spgpremove** command.
* `simpleperms.remove` - Allows you to use the **/spremove** command.
* `simpleperms.groups.player.add` - Allows you to use the **/spgpladd** command.
* `simpleperms.groups.player.remove` - Allows you to use the **/spgplrem** command.

### Site note
This plugin is still in the development phase. That means that the plugin **will not work** properly.
The plugin has many bugs that will be fixed over time. Please be patient.

If the release version is available you can find the download here, in the CI or under [DevBukkit](http://bukkit.asmax.me/)