package btw.community.kittystesting.blocks;


import btw.BTWMod;
import btw.block.BTWBlocks;
import btw.inventory.util.InventoryUtils;
import btw.util.MiscUtils;
import net.minecraft.src.*;


public class RiceCooker extends BlockContainer {

    protected Icon furnaceTopIcon;
    protected Icon furnaceFrontIcon;
    protected Icon field_96473_e;

    public RiceCooker(int par1) {
        super(par1, Material.iron);
        setUnlocalizedName("ricecooker");
        setHardness(2f);
        setPicksEffectiveOn();
        setNonBuoyant();
        setTextureName("kittysfirstaddon:ricecooker");
        this.setStepSound(BTWBlocks.boneStepSound);
    }

    @Override
    public int getFacing(int iMetadata) {
        return iMetadata & 7;
    }

    @Override
    public int setFacing(int iMetadata, int iFacing) {
        return iMetadata & 0xFFFFFFF8 | iFacing;
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityLiving, ItemStack stack) {
        int iFacing = MiscUtils.convertPlacingEntityOrientationToBlockFacingReversed(entityLiving);
        this.setFacing(world, i, j, k, iFacing);
        world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate(world));
    }

    @Override
    public int tickRate(World par1World) {
        return 4;
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate(world));
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        int var3 = par2 & 7;
        return par1 == var3 ? (var3 != 1 && var3 != 0 ? this.furnaceFrontIcon : this.field_96473_e) : (var3 != 1 && var3 != 0 ? (par1 != 1 && par1 != 0 ? this.blockIcon : this.furnaceTopIcon) : this.furnaceTopIcon);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("furnace_side");
        this.furnaceTopIcon = par1IconRegister.registerIcon("furnace_top");
        this.furnaceFrontIcon = par1IconRegister.registerIcon(this.getTextureName() + "_front_horizontal");
        this.field_96473_e = par1IconRegister.registerIcon(this.getTextureName() + "_front_vertical");
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int iFacing, float fXClick, float fYClick, float fZClick) {
        if (!world.isRemote) {
            TileEntityRiceCooker tileEntity = (TileEntityRiceCooker)world.getBlockTileEntity(i, j, k);
            if (player instanceof EntityPlayerMP) {
                ContainerRiceCooker container = new ContainerRiceCooker(player.inventory, tileEntity);
                BTWMod.serverOpenCustomInterface((EntityPlayerMP)player, container, KittysContainers.riceCookerContainerID);
            }
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityRiceCooker();
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, int iBlockID, int iMetadata) {
        InventoryUtils.ejectInventoryContents(world, i, j, k, (IInventory)((Object)world.getBlockTileEntity(i, j, k)));
        super.breakBlock(world, i, j, k, iBlockID, iMetadata);
    }

}
