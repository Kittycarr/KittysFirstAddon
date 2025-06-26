package btw.community.kittystesting.blocks;

import btw.block.blocks.DailyGrowthCropsBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

import java.util.Random;

public class LettuceCrop extends DailyGrowthCropsBlock {

    @Override
    protected boolean isFullyGrown(World world, int i, int j, int k) {
        return this.isFullyGrown(world.getBlockMetadata(i, j, k));
    }

    @Override
    protected boolean isFullyGrown(int iMetadata) {
        return this.getGrowthLevel(iMetadata) >= 5;
    }

    public LettuceCrop(int iBlockID) {
        super(iBlockID);
        this.setUnlocalizedName("LettuceCrop");
        setTextureName("kittysfirstaddon69:lettuce_stage_5");
    }

    @Override
    protected int getCropItemID() {
        return 6901;              //needs to be changed to the ID of the corresponding Item or 0 if it doesnt drop
    }

    @Override
    protected int getSeedItemID() {
        return 0;              //needs to be changed to the ID of the corresponding Item or 0 if it doesnt drop
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

