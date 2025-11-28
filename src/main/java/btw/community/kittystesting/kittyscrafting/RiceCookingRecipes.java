package btw.community.kittystesting.kittyscrafting;

import btw.item.BTWItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class RiceCookingRecipes {

    public static void initializeRecipies(){
        KittysRecipeManager.addRiceCookingRecipe(new ItemStack(BTWItems.tastySandwich, 2), new Object[]{
                new ItemStack(Item.bread),
                new ItemStack(BTWItems.cookedMutton)
        });
    }
}
