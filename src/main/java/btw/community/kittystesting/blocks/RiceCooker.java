package btw.community.kittystesting.blocks;


import net.minecraft.src.*;

public class RiceCooker extends Block{


    public RiceCooker(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setHardness(2f);
        this.setNonBuoyant();
        this.arePicksEffectiveOn();
        this.setUnlocalizedName("ricecooker");
        this.setTextureName("kittysfirstaddon69:ricecooker");
    }

}
