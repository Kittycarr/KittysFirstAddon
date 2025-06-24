package btw.community.example;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.community.kittystesting.items.KittysItems;

public class KittysFirstAddon extends BTWAddon {
    private static KittysFirstAddon instance;

    public KittysFirstAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        KittysItems.InitializeItems();

    }
}