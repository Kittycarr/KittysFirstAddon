package btw.community.kittystesting.kittyscrafting;

import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.List;

public class RiceCookingManager {
    private static final RiceCookingManager instance = new RiceCookingManager();

    public static final RiceCookingManager getInstance() { return instance; }
    private List recipes = new ArrayList();


    public List<IRecipe> getRecipeList() {
        return this.recipes;
    }

    private ShapelessRecipes createShapelessRecipe(ItemStack par1ItemStack, Object[] par2ArrayOfObj) {
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        for (Object obj : par2ArrayOfObj) {
            if (obj instanceof ItemStack) {
                arraylist.add(((ItemStack)obj).copy());
                continue;
            }
            if (obj instanceof Item) {
                arraylist.add(new ItemStack((Item)obj));
                continue;
            }
            if (obj instanceof Block) {
                arraylist.add(new ItemStack((Block)obj));
                continue;
            }
            throw new RuntimeException("Invalid shapeless recipe!");
        }
        return new ShapelessRecipes(par1ItemStack, arraylist);
    }

    public boolean removeShapelessRecipe(ItemStack itemStack, Object[] recipeArray) {
        ShapelessRecipes recipe = this.createShapelessRecipe(itemStack, recipeArray);
        int iMatchingIndex = this.getMatchingRecipeIndex(recipe);
        if (iMatchingIndex >= 0) {
            this.recipes.remove(iMatchingIndex);
            return true;
        }
        return false;
    }

    private int getMatchingRecipeIndex(IRecipe recipe) {
        int iMatchingRecipeIndex = -1;
        for (int iIndex = 0; iIndex < this.recipes.size(); ++iIndex) {
            IRecipe tempRecipe = (IRecipe)this.recipes.get(iIndex);
            if (!tempRecipe.matches(recipe)) continue;
            return iIndex;
        }
        return -1;
    }

    public ItemStack findMatchingRecipeStack(InventoryCrafting inventorycrafting, World world) {
        for (int i = 0; i < this.recipes.size(); ++i) {
            IRecipe irecipe = (IRecipe)this.recipes.get(i);
            if (!irecipe.matches(inventorycrafting, world)) continue;
            return irecipe.getCraftingResult(inventorycrafting);
        }
        return null;
    }

    public IRecipe findMatchingRecipe(InventoryCrafting inventory, World world) {
        for (int iTempIndex = 0; iTempIndex < this.recipes.size(); ++iTempIndex) {
            IRecipe tempRecipe = (IRecipe)this.recipes.get(iTempIndex);
            if (!tempRecipe.matches(inventory, world)) continue;
            return tempRecipe;
        }
        return null;
    }

    public void addShapelessRecipe(ItemStack itemstack, Object[] aobj) {
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        for (Object obj : aobj) {
            if (obj instanceof ItemStack) {
                arraylist.add(((ItemStack)obj).copy());
                continue;
            }
            if (obj instanceof Item) {
                arraylist.add(new ItemStack((Item)obj));
                continue;
            }
            if (obj instanceof Block) {
                arraylist.add(new ItemStack((Block)obj));
                continue;
            }
            throw new RuntimeException("Invalid shapeless recipe!");
        }
        this.recipes.add(new ShapelessRecipes(itemstack, arraylist));
    }


}
