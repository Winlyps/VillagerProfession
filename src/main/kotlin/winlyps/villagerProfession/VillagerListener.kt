package winlyps.villagerProfession.listeners

import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.entity.VillagerAcquireTradeEvent
import org.bukkit.event.entity.VillagerCareerChangeEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Bukkit
import org.bukkit.inventory.MerchantRecipe
import java.util.*

class VillagerListener(private val plugin: JavaPlugin) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onVillagerSpawn(event: EntitySpawnEvent) {
        val entity = event.entity

        if (entity is Villager && entity.hasMetadata("custom_profession")) {
            val professionName = entity.getMetadata("custom_profession")[0].asString()
            val profession = Villager.Profession.valueOf(professionName)

            Bukkit.getScheduler().runTaskLater(plugin, Runnable {
                if (entity.isValid && !entity.isDead) {
                    entity.profession = profession
                    generateNaturalTrades(entity, profession)
                }
            }, 5L)
        }
    }

    @EventHandler
    fun onVillagerCareerChange(event: VillagerCareerChangeEvent) {
        val villager = event.getEntity()

        if (villager.hasMetadata("custom_profession")) {
            event.setCancelled(true)
        }
    }

    @EventHandler
    fun onVillagerAcquireTrade(event: VillagerAcquireTradeEvent) {
        val villager = event.getEntity()

        if (villager.hasMetadata("custom_profession")) {
        }
    }

    private fun generateNaturalTrades(villager: Villager, profession: Villager.Profession) {
        villager.recipes = mutableListOf()

        val tradeGenerator = VanillaTradeGenerator()

        val tradeCount = Random().nextInt(3) + 1
        val trades = tradeGenerator.generateTrades(profession, tradeCount)

        villager.recipes = trades
    }
}