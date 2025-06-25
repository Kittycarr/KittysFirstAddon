package btw.community.kittystesting.blocks;

import btw.block.blocks.DailyGrowthCropsBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;

public class LettuceCrop extends DailyGrowthCropsBlock {

    public LettuceCrop(int iBlockID) {
        super(iBlockID);
        this.setUnlocalizedName("LettuceCrop");
        setTextureName("kittysfirstaddon69:lettuce_stage_5");
    }

    @Override
    protected int getCropItemID() {
        return 0;
    }

    @Override
    protected int getSeedItemID() {
        return 0;
    }

    @Environment(value= EnvType.CLIENT)
    private Icon[] iconArray;

    @Override
    protected boolean requiresNaturalLight() {
        return false;
    }
    @Override
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        int iTempIndex;
        this.iconArray = new Icon[8];
        for (iTempIndex = 0; iTempIndex < this.iconArray.length; ++iTempIndex) {
            this.iconArray[iTempIndex] = register.registerIcon("kittysfirstaddon69:lettuce_stage_"+ iTempIndex);
        }
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getIcon(int iSide, int iMetadata) {
        int iGrowthLevel = iMetadata & 7;
        return this.iconArray[iGrowthLevel];
    }

}

