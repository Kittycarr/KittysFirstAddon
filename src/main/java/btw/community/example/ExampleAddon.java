package btw.community.example;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.community.kittystesting.Recipes;
import btw.community.kittystesting.blocks.KittysBlocks;
import btw.community.kittystesting.items.KittysItems;
import btw.util.sounds.AddonSoundRegistryEntry;

public class ExampleAddon extends BTWAddon {
    private static ExampleAddon instance;

    public ExampleAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        KittysItems.InitializeItems();
        KittysBlocks.InitializeBlocks();

        Recipes.InitializeRecipes();

        new AddonSoundRegistryEntry("kittysfirstaddon69:metal_pipe");
        new AddonSoundRegistryEntry("kittysfirstaddon69:metal_rumble");

    }




}