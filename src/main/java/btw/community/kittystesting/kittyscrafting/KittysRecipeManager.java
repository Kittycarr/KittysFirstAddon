package btw.community.kittystesting.kittyscrafting;

import net.minecraft.src.ItemStack;
import net.minecraft.src.ShapelessRecipes;

public abstract class KittysRecipeManager {

    public static void addAllAddonRecipes() {

    }

    public static void addRiceCookingRecipe(ItemStack itemStack, Object[] pattern) {
        RiceCookingManager.getInstance().addRecipe(itemStack, pattern);
    }

    public static void removeRiceCookingRecipe(ItemStack itemStack, Object[] inputs) {
        RiceCookingManager.getInstance().removeRecipe(itemStack, inputs);
    }
}
