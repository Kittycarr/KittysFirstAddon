package btw.community.kittystesting.blocks;

import net.minecraft.src.*;

public class KittysBlocks {

    public static LettuceCrop lettuceCrop;
    public static RiceCooker riceCooker;


    public static void InitializeBlocks() {
        lettuceCrop = (LettuceCrop) new LettuceCrop(693).hideFromEMI();
        riceCooker = (RiceCooker) new RiceCooker(695).setCreativeTab(CreativeTabs.tabRedstone);

        Item.itemsList[riceCooker.blockID] = new ItemBlockWithMetadata(riceCooker.blockID -256, riceCooker).setUnlocalizedName("ricecooker").setMaxStackSize(1);
    }
}
