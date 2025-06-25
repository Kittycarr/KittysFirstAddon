package btw.community.kittystesting.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

public class KittysBlocks {

    public static LettuceCrop lettuceCrop;
    public static RiceCooker riceCooker;


    public static void InitializeBlocks() {
        lettuceCrop = (LettuceCrop) new LettuceCrop(693).hideFromEMI();
        riceCooker = (RiceCooker) new RiceCooker(695, Material.iron);
    }
}
