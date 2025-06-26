package btw.community.kittystesting;

import btw.community.kittystesting.items.KittysItems;
import btw.crafting.recipe.RecipeManager;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class Recipes {


    public static void addRecipes() {
        //chickenburgir
        RecipeManager.addRecipe(new ItemStack(KittysItems.chickenBurger, 2), new Object[]{
                " A ",
                "BCD",
                " A ",
                'A', new ItemStack(KittysItems.slicedBun),
                'B', new ItemStack(KittysItems.lettuceSlice),
                'C', new ItemStack(Item.chickenCooked),
                'D', new ItemStack(KittysItems.cheese),
        } );
    }
}
