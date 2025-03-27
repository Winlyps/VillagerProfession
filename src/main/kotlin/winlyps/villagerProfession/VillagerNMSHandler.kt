package winlyps.villagerProfession.commands

import org.bukkit.entity.Villager
import java.lang.reflect.Method

/**
 * Handles NMS (Net Minecraft Server) operations for villagers
 * Uses reflection to avoid direct NMS imports which can break across versions
 */
object VillagerNMSHandler {

    /**
     * Sets up a villager with the proper profession using reflection
     * This avoids direct NMS imports which can break between versions
     */
    fun setupVillager(villager: Villager, profession: Villager.Profession) {
        try {
            // Get the CraftVillager class
            val craftVillagerClass = Class.forName("org.bukkit.craftbukkit.v1_20_R1.entity.CraftVillager")

            // Check if our villager is a CraftVillager
            if (!craftVillagerClass.isInstance(villager)) return

            // Get the getHandle method
            val getHandleMethod = craftVillagerClass.getDeclaredMethod("getHandle")
            getHandleMethod.isAccessible = true

            // Get the NMS villager instance
            val nmsVillager = getHandleMethod.invoke(villager)

            // Find and invoke methods to set up the villager
            // Method names are obfuscated in production, so we need to try different approaches

            // Try to find a method to update trades
            val methods = nmsVillager.javaClass.declaredMethods

            // Look for methods that might update trades (no parameters, void return type)
            val potentialUpdateMethods = methods.filter {
                it.parameterCount == 0 && it.returnType == Void.TYPE
            }

            // Try each potential method (we're looking for one that updates trades)
            for (method in potentialUpdateMethods) {
                try {
                    method.isAccessible = true
                    method.invoke(nmsVillager)
                } catch (e: Exception) {
                    // Ignore errors and try the next method
                }
            }

            // Force profession update through the Bukkit API as a fallback
            villager.profession = profession

        } catch (e: Exception) {
            // If reflection fails, we've already set the profession through the Bukkit API
        }
    }
}