package btw.community.kittystesting.kittyscrafting;

import btw.item.BTWItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class RiceCookingRecipes {

    public static void initializeRecipies(){
        KittysRecipeManager.addShapelessRiceCookingRecipe(new ItemStack(BTWItems.tastySandwich), new Object[]{
                new ItemStack(Item.bread),
                new ItemStack(BTWItems.cookedMutton)
        });
    }
}
