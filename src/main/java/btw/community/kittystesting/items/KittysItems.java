package btw.community.kittystesting.items;

import btw.item.items.FoodItem;
import btw.item.items.PlaceAsBlockItem;
import btw.item.items.SeedItem;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;


public class KittysItems {

    public static FoodItem chickenBurger;
    public static FoodItem lettuce;
    public static SeedItem lettuceSeed;
    public static FoodItem cheese;


    public static void InitializeItems() {
        chickenBurger = (FoodItem) new FoodItem(6900-256, 6, 1f, false, "kittyschickenburger", true ).setCreativeTab(CreativeTabs.tabFood).setTextureName("kittysfirstaddon69:kittyschickenburger").setMaxStackSize(16).setIncineratedInCrucible().setUnlocalizedName("chickenburger").setBuoyant();
        lettuce = (FoodItem) new FoodItem(6901-256, 1, 0.5f, false, "lettuce", false).setCreativeTab(CreativeTabs.tabFood).setTextureName("kittysfirstaddon69:kittyslettuce").setMaxStackSize(16).setIncineratedInCrucible().setUnlocalizedName("lettuce").setBuoyant();
        lettuceSeed = (SeedItem) new SeedItem(6902-256, 693).setCreativeTab(CreativeTabs.tabMaterials).setIncineratedInCrucible().setBuoyant().setTextureName("kittysfirstaddon69:lettuceseed").setMaxStackSize(64).setUnlocalizedName("lettuceseed");

    }

}

