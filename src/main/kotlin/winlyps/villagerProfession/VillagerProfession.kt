package winlyps.villagerProfession

import org.bukkit.plugin.java.JavaPlugin
import winlyps.villagerProfession.commands.VillagerCommand
import winlyps.villagerProfession.listeners.VillagerListener

class VillagerProfession : JavaPlugin() {
    override fun onEnable() {
        getCommand("villager")?.let { command ->
            val commandExecutor = VillagerCommand(this)
            command.setExecutor(commandExecutor)
            command.tabCompleter = commandExecutor
        }

        server.pluginManager.registerEvents(VillagerListener(this), this)
    }
}