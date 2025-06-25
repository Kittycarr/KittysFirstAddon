package btw.community.kittystesting.blocks;

public class KittysBlocks {

    public static LettuceCrop lettuceCrop;


    public static void InitializeBlocks() {
        lettuceCrop = (LettuceCrop) new LettuceCrop(6903).hideFromEMI();
    }
}
