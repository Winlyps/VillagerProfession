package winlyps.villagerProfession.listeners

import org.bukkit.Material
import org.bukkit.entity.Villager
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MerchantRecipe
import java.util.*

class VanillaTradeGenerator {

    private val random = Random()

    fun generateTrades(profession: Villager.Profession, count: Int): MutableList<MerchantRecipe> {
        val trades = mutableListOf<MerchantRecipe>()

        for (i in 0 until count) {
            val trade = when (profession) {
                Villager.Profession.FARMER -> generateFarmerTrade()
                Villager.Profession.FISHERMAN -> generateFishermanTrade()
                Villager.Profession.SHEPHERD -> generateShepherdTrade()
                Villager.Profession.FLETCHER -> generateFletcherTrade()
                Villager.Profession.LIBRARIAN -> generateLibrarianTrade()
                Villager.Profession.CARTOGRAPHER -> generateCartographerTrade()
                Villager.Profession.CLERIC -> generateClericTrade()
                Villager.Profession.ARMORER -> generateArmorerTrade()
                Villager.Profession.WEAPONSMITH -> generateWeaponsmithTrade()
                Villager.Profession.TOOLSMITH -> generateToolsmithTrade()
                Villager.Profession.BUTCHER -> generateButcherTrade()
                Villager.Profession.LEATHERWORKER -> generateLeatherworkerTrade()
                Villager.Profession.MASON -> generateMasonTrade()
                else -> generateBasicTrade()
            }

            trades.add(trade)
        }

        return trades
    }

    private fun generateBasicTrade(): MerchantRecipe {
        val emeraldCount = random.nextInt(8) + 1
        val result = ItemStack(Material.BREAD, random.nextInt(3) + 1)

        val recipe = MerchantRecipe(result, 0, 7, true)
        recipe.addIngredient(ItemStack(Material.EMERALD, emeraldCount))

        return recipe
    }

    private fun generateFarmerTrade(): MerchantRecipe {
        val possibleItems = listOf(
                Material.WHEAT, Material.POTATO, Material.CARROT, Material.BEETROOT,
                Material.BREAD, Material.PUMPKIN, Material.MELON
        )

        val sellItem = possibleItems[random.nextInt(possibleItems.size)]
        val sellAmount = random.nextInt(6) + 1

        val recipe = if (random.nextBoolean()) {
            val result = ItemStack(Material.EMERALD, random.nextInt(2) + 1)
            val recipe = MerchantRecipe(result, 0, 7, true)
            recipe.addIngredient(ItemStack(sellItem, sellAmount))
            recipe
        } else {
            val result = ItemStack(sellItem, sellAmount)
            val recipe = MerchantRecipe(result, 0, 7, true)
            recipe.addIngredient(ItemStack(Material.EMERALD, random.nextInt(3) + 1))
            recipe
        }

        return recipe
    }

    private fun generateFishermanTrade(): MerchantRecipe {
        val possibleItems = listOf(
                Material.COD, Material.SALMON, Material.TROPICAL_FISH, Material.PUFFERFISH,
                Material.FISHING_ROD, Material.STRING, Material.LILY_PAD
        )

        val sellItem = possibleItems[random.nextInt(possibleItems.size)]
        val sellAmount = random.nextInt(4) + 1

        val recipe = if (random.nextBoolean()) {
            val result = ItemStack(Material.EMERALD, random.nextInt(2) + 1)
            val recipe = MerchantRecipe(result, 0, 7, true)
            recipe.addIngredient(ItemStack(sellItem, sellAmount))
            recipe
        } else {
            val result = ItemStack(sellItem, sellAmount)
            val recipe = MerchantRecipe(result, 0, 7, true)
            recipe.addIngredient(ItemStack(Material.EMERALD, random.nextInt(3) + 1))
            recipe
        }

        return recipe
    }

    private fun generateShepherdTrade(): MerchantRecipe {
        val woolColors = listOf(
                Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL,
                Material.LIGHT_BLUE_WOOL, Material.YELLOW_WOOL, Material.LIME_WOOL,
                Material.PINK_WOOL, Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL,
                Material.CYAN_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL,
                Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL
        )

        val woolType = woolColors[random.nextInt(woolColors.size)]

        val recipe = if (random.nextBoolean()) {
            val result = ItemStack(Material.EMERALD, random.nextInt(2) + 1)
            val recipe = MerchantRecipe(result, 0, 7, true)
            recipe.addIngredient(ItemStack(woolType, random.nextInt(8) + 1))
            recipe
        } else {
            val result = ItemStack(woolType, random.nextInt(4) + 1)
            val recipe = MerchantRecipe(result, 0, 7, true)
            recipe.addIngredient(ItemStack(Material.EMERALD, random.nextInt(3) + 1))
            recipe
        }

        return recipe
    }

    private fun generateFletcherTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateLibrarianTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateCartographerTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateClericTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateArmorerTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateWeaponsmithTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateToolsmithTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateButcherTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateLeatherworkerTrade(): MerchantRecipe = generateBasicTrade()
    private fun generateMasonTrade(): MerchantRecipe = generateBasicTrade()
}