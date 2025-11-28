package btw.community.kittystesting.kittyscrafting;

import btw.inventory.util.InventoryUtils;
import btw.item.tag.TagInstance;
import btw.item.tag.TagOrStack;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RiceRecipes {
    private final ItemStack recipeOutputStacks;
    private final List<TagOrStack> recipeInputStacks;
    private final boolean metadataExclusive;

    public RiceRecipes(ItemStack recipeOutputStack, List<TagOrStack> recipeInputStacks) {
        this(recipeOutputStack, recipeInputStacks, false);
    }

    public RiceRecipes(ItemStack recipeOutputStacks, List<TagOrStack> recipeInputStacks, boolean bMetaDataExclusive) {
        this.recipeOutputStacks = recipeOutputStacks;
        this.recipeInputStacks = recipeInputStacks;
        this.metadataExclusive = bMetaDataExclusive;
    }

    public ItemStack getCraftingOutputList() {
        return this.recipeOutputStacks;
    }

    public List<TagOrStack> getCraftingIngrediantList() {
        return this.recipeInputStacks;
    }

    public TagOrStack getFirstIngredient() {
        if (this.recipeInputStacks != null && this.recipeInputStacks.size() > 0) {
            return this.recipeInputStacks.get(0);
        }
        return null;
    }

    public boolean doesInventoryContainIngredients(IInventory inventory) {
        if (this.recipeInputStacks != null && this.recipeInputStacks.size() > 0) {
            for (int listIndex = 0; listIndex < this.recipeInputStacks.size(); ++listIndex) {
                TagOrStack temp = this.recipeInputStacks.get(listIndex);
                if (temp instanceof ItemStack) {
                    ItemStack tempStack = (ItemStack)temp;
                    if (InventoryUtils.countItemsInInventory(inventory, tempStack.getItem().itemID, tempStack.getItemDamage(), this.metadataExclusive) >= tempStack.stackSize) continue;
                    return false;
                }
                if (!(temp instanceof TagInstance)) continue;
                TagInstance tempTag = (TagInstance)temp;
                int totalCounted = 0;
                for (ItemStack tempStack : tempTag.tag().getItems()) {
                    tempStack.stackSize = tempTag.stackSize();
                    int counted = InventoryUtils.countItemsInInventory(inventory, tempStack.getItem().itemID, tempStack.getItemDamage(), this.metadataExclusive);
                    if (counted < tempStack.stackSize) continue;
                    totalCounted += counted;
                }
                if (totalCounted != 0) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean doesStackSatisfyIngredients(ItemStack stack) {
        if (this.recipeInputStacks != null && this.recipeInputStacks.size() == 1) {
            TagOrStack tagOrStack = this.recipeInputStacks.get(0);
            if (tagOrStack instanceof ItemStack) {
                ItemStack recipeStack = (ItemStack)tagOrStack;
                int recipeItemDamage = recipeStack.getItemDamage();
                if (stack.itemID == recipeStack.itemID && stack.stackSize >= recipeStack.stackSize && (recipeItemDamage == Short.MAX_VALUE || !this.metadataExclusive && stack.getItemDamage() == recipeItemDamage || this.metadataExclusive && stack.getItemDamage() != recipeItemDamage)) {
                    return true;
                }
            } else {
                TagInstance tagInstance;
                tagOrStack = this.recipeInputStacks.get(0);
                if (tagOrStack instanceof TagInstance && (tagInstance = (TagInstance)tagOrStack).tag().test(stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean consumeInventoryIngredients(IInventory inventory) {
        boolean bSuccessful = true;
        if (this.recipeInputStacks != null && this.recipeInputStacks.size() > 0) {
            block0: for (int listIndex = 0; listIndex < this.recipeInputStacks.size(); ++listIndex) {
                TagOrStack temp = this.recipeInputStacks.get(listIndex);
                if (temp instanceof ItemStack) {
                    ItemStack tempStack = (ItemStack)temp;
                    if (InventoryUtils.consumeItemsInInventory(inventory, tempStack.getItem().itemID, tempStack.getItemDamage(), tempStack.stackSize, this.metadataExclusive)) continue;
                    bSuccessful = false;
                    continue;
                }
                if (!(temp instanceof TagInstance)) continue;
                TagInstance tagInstance = (TagInstance)temp;
                for (ItemStack tempStack : tagInstance.tag().getItems()) {
                    if (!InventoryUtils.consumeItemsInInventory(inventory, tempStack.getItem().itemID, tempStack.getItemDamage(), tempStack.stackSize, this.metadataExclusive)) {
                        bSuccessful = false;
                        continue;
                    }
                    bSuccessful = true;
                    continue block0;
                }
            }
        }
        return bSuccessful;
    }

    public boolean matches(RiceRecipes recipe) {
        if (this.metadataExclusive == recipe.metadataExclusive && this.recipeInputStacks.size() == recipe.recipeInputStacks.size() && this.recipeOutputStacks == recipe.recipeOutputStacks) {
            int iListIndex;
            for (iListIndex = 0; iListIndex < this.recipeInputStacks.size(); ++iListIndex) {
                TagOrStack tagOrStack1 = this.recipeInputStacks.get(iListIndex);
                TagOrStack tagOrStack2 = recipe.recipeInputStacks.get(iListIndex);
                if (tagOrStack1 instanceof ItemStack) {
                    ItemStack stack1 = (ItemStack)tagOrStack1;
                    if (tagOrStack2 instanceof ItemStack) {
                        ItemStack stack2 = (ItemStack)tagOrStack2;
                        if (this.doStacksMatch(stack1, stack2)) continue;
                        return false;
                    }
                }
                if (tagOrStack1 instanceof TagInstance) {
                    TagInstance tag1 = (TagInstance)tagOrStack1;
                    if (tagOrStack2 instanceof TagInstance) {
                        TagInstance tag2 = (TagInstance)tagOrStack2;
                        if (tag1.equals(tag2)) continue;
                        return false;
                    }
                }
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean doStacksMatch(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem().itemID == stack2.getItem().itemID && stack1.stackSize == stack2.stackSize && stack1.getItemDamage() == stack2.getItemDamage();
    }
}
