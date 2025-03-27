package winlyps.villagerProfession.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.metadata.FixedMetadataValue
import java.util.*

class VillagerCommand(private val plugin: JavaPlugin) : CommandExecutor, TabCompleter {

    private val professionMap = mapOf(
            "farmer" to Villager.Profession.FARMER,
            "fisherman" to Villager.Profession.FISHERMAN,
            "shepherd" to Villager.Profession.SHEPHERD,
            "fletcher" to Villager.Profession.FLETCHER,
            "librarian" to Villager.Profession.LIBRARIAN,
            "cartographer" to Villager.Profession.CARTOGRAPHER,
            "cleric" to Villager.Profession.CLERIC,
            "armorer" to Villager.Profession.ARMORER,
            "weaponsmith" to Villager.Profession.WEAPONSMITH,
            "toolsmith" to Villager.Profession.TOOLSMITH,
            "butcher" to Villager.Profession.BUTCHER,
            "leatherworker" to Villager.Profession.LEATHERWORKER,
            "mason" to Villager.Profession.MASON,
            "nitwit" to Villager.Profession.NITWIT,
            "unemployed" to Villager.Profession.NONE
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player || !sender.hasPermission("villagerprofession.summon") || args.isEmpty()) {
            return false
        }

        val professionName = args[0].lowercase(Locale.getDefault())
        val profession = professionMap[professionName] ?: return false

        try {
            val villager = sender.world.spawnEntity(sender.location, EntityType.VILLAGER) as Villager
            villager.profession = profession
            villager.setAdult()
            villager.setMetadata("custom_profession", FixedMetadataValue(plugin, profession.name))

            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun onTabComplete(
            sender: CommandSender,
            command: Command,
            alias: String,
            args: Array<out String>
    ): List<String> {
        if (sender !is Player || !sender.hasPermission("villagerprofession.summon") || args.size != 1) {
            return emptyList()
        }

        val input = args[0].lowercase(Locale.getDefault())
        return professionMap.keys.filter { it.startsWith(input) }.sorted()
    }
}