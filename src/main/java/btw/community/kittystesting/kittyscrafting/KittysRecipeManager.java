package btw.community.kittystesting.kittyscrafting;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ShapelessRecipes;

public abstract class KittysRecipeManager {

    public static void addAllAddonRecipes() {

    }

    public static void addShapelessRiceCookingRecipe(ItemStack itemStack, Object[] pattern) {
        RiceCookingManager.getInstance().addShapelessRecipe(itemStack, pattern);
    }

    public static void removeShapelessRiceCookingRecipe(ItemStack itemStack, Object[] inputs) {
        RiceCookingManager.getInstance().removeShapelessRecipe(itemStack, inputs);
    }
}
