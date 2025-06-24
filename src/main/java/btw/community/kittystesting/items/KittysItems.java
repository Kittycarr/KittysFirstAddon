package btw.community.kittystesting.items;

import btw.item.items.FoodItem;
import net.minecraft.src.CreativeTabs;

public class KittysItems {

    public static FoodItem chickenBurger;

    static {
        chickenBurger = (FoodItem) new FoodItem(69000, 6, 1f, false, "kittyschickenburger", true ).setCreativeTab(CreativeTabs.tabFood).setTextureName("kittyschickenburger").setMaxStackSize(16);
    }
}
