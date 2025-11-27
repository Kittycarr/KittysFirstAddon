package btw.community.kittystesting.blocks;

import btw.community.kittystesting.blocks.riceCooker.RiceCooker;
import btw.community.kittystesting.blocks.riceCooker.TileEntityRiceCooker;
import net.minecraft.src.*;

public class KittysBlocks {

    public static LettuceCrop lettuceCrop;
    public static RiceCooker riceCooker;


    public static void InitializeBlocks() {
        lettuceCrop = (LettuceCrop) new LettuceCrop(693);
        riceCooker = (RiceCooker) new RiceCooker(695).setCreativeTab(CreativeTabs.tabRedstone);

        Item.itemsList[riceCooker.blockID] = new ItemBlockWithMetadata(riceCooker.blockID -256, riceCooker).setUnlocalizedName("ricecooker").setMaxStackSize(1);
        TileEntity.addMapping(TileEntityRiceCooker.class, "RiceCooker");
    }
}
