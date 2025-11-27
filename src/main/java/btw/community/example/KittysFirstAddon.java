package btw.community.example;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.community.kittystesting.Recipes;
import btw.community.kittystesting.blocks.KittysBlocks;
import btw.community.kittystesting.items.KittysItems;
import btw.community.kittystesting.kittyscrafting.KittysRecipeManager;
import btw.community.kittystesting.kittyscrafting.RiceCookingRecipes;
import btw.util.sounds.AddonSoundRegistryEntry;

public class KittysFirstAddon extends BTWAddon {
    private static KittysFirstAddon instance;

    public KittysFirstAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        KittysItems.InitializeItems();
        KittysBlocks.InitializeBlocks();

        RiceCookingRecipes.initializeRecipies();
        Recipes.InitializeRecipes();

        new AddonSoundRegistryEntry("kittysfirstaddon69:metal_pipe");
        new AddonSoundRegistryEntry("kittysfirstaddon69:metal_rumble");

    }




}