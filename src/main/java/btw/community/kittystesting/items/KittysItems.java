package btw.community.kittystesting.items;

import btw.item.items.FoodItem;
import btw.item.items.SeedItem;
import net.minecraft.src.CreativeTabs;


public class KittysItems {

    public static FoodItem chickenBurger;
    public static FoodItem lettuce;
    public static SeedItem lettuceSeed;
    public static FoodItem cheese;
    public static FoodItem slicedBun;
    public static FoodItem lettuceSlice;


    public static void InitializeItems() {
        chickenBurger = (detailedFoodItem) new detailedFoodItem(6900-256, 7, 3, false, "kittyschickenburger").setCreativeTab(CreativeTabs.tabFood).setTextureName("kittysfirstaddon69:chickenburger").setMaxStackSize(16).setIncineratedInCrucible().setUnlocalizedName("kittyschickenburger").setBuoyant();
        lettuce = (detailedFoodItem) new detailedFoodItem(6901-256, 1, 1, false, "kittyslettuce").setCreativeTab(CreativeTabs.tabFood).setTextureName("kittysfirstaddon69:lettuce").setMaxStackSize(16).setIncineratedInCrucible().setUnlocalizedName("kittyslettuce").setBuoyant();
        lettuceSeed = (SeedItem) new SeedItem(6902-256, 693).setCreativeTab(CreativeTabs.tabMaterials).setIncineratedInCrucible().setBuoyant().setTextureName("kittysfirstaddon69:lettuceseed").setMaxStackSize(64).setUnlocalizedName("kittyslettuceseed");
        cheese = (detailedFoodItem) new detailedFoodItem(6904-256, 1, 2, false, "kittyscheese").setCreativeTab(CreativeTabs.tabFood).setIncineratedInCrucible().setBuoyant().setTextureName("kittysfirstaddon69:cheese").setMaxStackSize(16).setUnlocalizedName("kittyscheese");
        slicedBun = (detailedFoodItem) new detailedFoodItem(6905-256, 2, 3, false, "kittysslicedbun").setCreativeTab(CreativeTabs.tabFood).setIncineratedInCrucible().setBuoyant().setMaxStackSize(16).setTextureName("kittysfirstaddon69:slicedbun").setUnlocalizedName("kittysslicedbun");
        lettuceSlice = (detailedFoodItem) new detailedFoodItem(6906-256, 1, -1, false, "kittyslettuceslice").setCreativeTab(CreativeTabs.tabFood).setIncineratedInCrucible().setBuoyant().setMaxStackSize(16).setTextureName("kittysfirstaddon69:lettuceslice").setUnlocalizedName("kittyslettuceslice");

    }

}

