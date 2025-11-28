package btw.community.kittystesting.kittyscrafting;

import btw.item.tag.TagOrStack;
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

    private RiceRecipes createRecipe(ItemStack par1ItemStack, Object[] par2ArrayOfObj) {
        List<TagOrStack> arraylist = new ArrayList<TagOrStack>();
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
            throw new RuntimeException("Invalid ricecooking recipe!");
        }
        return new RiceRecipes(par1ItemStack, arraylist);
    }

    public boolean removeRecipe(ItemStack itemStack, Object[] recipeArray) {
        RiceRecipes recipe = this.createRecipe(itemStack, recipeArray);
        int iMatchingIndex = this.getMatchingRecipeIndex(recipe);
        if (iMatchingIndex >= 0) {
            this.recipes.remove(iMatchingIndex);
            return true;
        }
        return false;
    }

    private int getMatchingRecipeIndex(RiceRecipes recipe) {
        int iMatchingRecipeIndex = -1;
        for (int iIndex = 0; iIndex < this.recipes.size(); ++iIndex) {
            RiceRecipes tempRecipe = (RiceRecipes)this.recipes.get(iIndex);
            if (!tempRecipe.matches(recipe)) continue;
            return iIndex;
        }
        return -1;
    }

    public IRecipe findMatchingRecipe(InventoryCrafting inventory, World world) {
        for (int iTempIndex = 0; iTempIndex < this.recipes.size(); ++iTempIndex) {
            IRecipe tempRecipe = (IRecipe)this.recipes.get(iTempIndex);
            if (!tempRecipe.matches(inventory, world)) continue;
            return tempRecipe;
        }
        return null;
    }

    public void addRecipe(ItemStack itemstack, Object[] aobj) {
        List<TagOrStack> arraylist = new ArrayList<TagOrStack>();
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
            throw new RuntimeException("Invalid ricecooking recipe!");
        }
        this.recipes.add(new RiceRecipes(itemstack, arraylist));
    }

    public ItemStack getCraftingResult(IInventory inventory) {
        for (int i = 0; i < this.recipes.size(); ++i) {
            RiceRecipes tempRecipe = (RiceRecipes) this.recipes.get(i);
            if (!tempRecipe.doesInventoryContainIngredients(inventory)) continue;
            return tempRecipe.getCraftingOutputList();
        }
        return null;
    }

    public ItemStack consumeIngredientsAndReturnResult(IInventory inventory) {
        for (int i = 0; i < this.recipes.size(); ++i) {
            RiceRecipes tempRecipe = (RiceRecipes) this.recipes.get(i);
            if (!tempRecipe.doesInventoryContainIngredients(inventory)) continue;
            tempRecipe.consumeInventoryIngredients(inventory);
            return tempRecipe.getCraftingOutputList();
        }
        return null;
    }
}
