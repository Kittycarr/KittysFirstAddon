package btw.community.kittystesting.items;

import btw.item.items.FoodItem;

public class detailedFoodItem extends FoodItem {

    private final int healAmount;
    private final float saturationModifier;

    public detailedFoodItem(int iItemID, int iHungerHealed, float fSaturationModifier, boolean bWolfMeat, String sItemName) {
        super(iItemID, iHungerHealed, fSaturationModifier, bWolfMeat, sItemName);
        this.healAmount = iHungerHealed;
        this.saturationModifier = fSaturationModifier;
    }

    @Override
    public int getHungerRestored() {
        return (int) (this.healAmount * saturationModifier);
    }
}
