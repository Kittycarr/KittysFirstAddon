package btw.community.kittystesting.blocks;


import btw.BTWMod;
import btw.block.BTWBlocks;
import btw.block.MechanicalBlock;
import btw.block.util.MechPowerUtils;
import btw.inventory.util.InventoryUtils;
import btw.util.MiscUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loom.configuration.providers.mappings.extras.unpick.UnpickLayer;
import net.minecraft.src.*;

import java.util.Random;

import static net.minecraft.src.BlockDispenser.dispenseBehaviorRegistry;

//Classes to make the rice cooker: kittystesting/blocks/RiceCooker, kittystesting/blocks/ContainerRiceCooker, kittystesting/blocks/ContainerRiceCookerGui,
//                                 kittystesting/blocks/TileEntityRiceCooker, kittystesting/blocks/KittysContainers, example/mixin/This

public class RiceCooker extends BlockContainer implements MechanicalBlock {

    protected Icon furnaceTopIcon;
    protected Icon furnaceFrontIcon;
    protected Icon field_96473_e;

    public RiceCooker(int par1) {
        super(par1, Material.iron);
        setUnlocalizedName("kittysricecooker");
        setHardness(2f);
        setPicksEffectiveOn();
        setNonBuoyant();
        setTextureName("kittysfirstaddon:ricecooker");
        this.setStepSound(BTWBlocks.boneStepSound);
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random rand) {
        boolean bMechPowered = this.isInputtingMechanicalPower(world, i, j, k);
        this.updateMechPoweredState(world, i, j, k, bMechPowered);
        if (world.isBlockGettingPowered(i,j,k)){
            this.dispenseCookedItem(world.getBlockMetadata(i,j,k),world,i,j,k);
        } else {
            world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate(world));
        }
    }

    @Override
    public void randomUpdateTick(World world, int i, int j, int k, Random rand) {
        if (!this.isCurrentStateValid(world, i, j, k) && !world.isUpdateScheduledForBlock(i, j, k, this.blockID)) {
            world.scheduleBlockUpdate(i, j, k, this.blockID, this.tickRate(world));
        }
    }

    protected void dispenseCookedItem(int iMetadata, World world, int i, int j, int k){
        int slot = 9;
        BlockSourceImpl blockSource = new BlockSourceImpl(world, i, j, k);
        TileEntityRiceCooker tileEntity = (TileEntityRiceCooker)world.getBlockTileEntity(i, j, k);
        ItemStack itemStack =  tileEntity.getStackInSlot(slot);
        if (itemStack != null) {
            IBehaviorDispenseItem dispenseItem = this.getBehaviorForItemStack(itemStack);
            if (dispenseItem != IBehaviorDispenseItem.itemDispenseBehaviorProvider) {
                ItemStack newItemStack = dispenseItem.dispense(blockSource, itemStack);
                tileEntity.setInventorySlotContents(slot, newItemStack.stackSize == 0 ? null : newItemStack);
            }
        }
    }


    protected IBehaviorDispenseItem getBehaviorForItemStack(ItemStack par1ItemStack) {
        return (IBehaviorDispenseItem)dispenseBehaviorRegistry.getObject(par1ItemStack.getItem());
    }

    protected boolean isCurrentStateValid(World world, int i, int j, int k) {
        return this.isRiceCookerOn(world, i, j, k) == this.isInputtingMechanicalPower(world, i, j, k);
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int par5) {
        super.onNeighborBlockChange(world, i, j, k, par5);
        world.scheduleBlockUpdate(i,j,k, this.blockID, this.tickRate(world));
    }

    @Override
    public int getFacing(int iMetadata) {
        return iMetadata & 7;
    }

    public int getFacingBack(World world, int i, int j, int k) {
        int currentFront = this.getFacing(world, i, j, k);
        int currentBack = currentFront;
        switch (currentFront){
            case 5, 3, 1:
                currentBack = currentFront-1;
                break;
            case 4, 2, 0:
                currentBack = currentFront+1;
                break;
        }
        if (currentBack == currentFront){
            System.out.println("Failed:"+ currentBack);
        }
        return currentBack;
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
    public boolean canRedstoneConnectToSide(IBlockAccess blockAccess, int x, int y, int z, int flatDirection) {
        return true;
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


    @Override
    public boolean canOutputMechanicalPower() {
        return false;
    }

    @Override
    public boolean canInputMechanicalPower() {
        return true;
    }

    @Override
    public boolean isInputtingMechanicalPower(World world, int i, int j, int k) {
        return MechPowerUtils.isBlockPoweredByAxleToSide(world, i, j, k, this.getFacingBack(world, i, j, k));
    }

    @Override
    public boolean isOutputtingMechanicalPower(World var1, int var2, int var3, int var4) {
        return false;
    }

    @Override
    public boolean canInputAxlePowerToFacing(World world, int i, int j, int k, int iFacing) {
        int iBlockFacing = this.getFacingBack(world, i, j, k);
        return iFacing == iBlockFacing;
    }

    @Override
    public void overpower(World world, int i, int j, int k) {
        if (this.isRiceCookerOn(world, i, j, k)) {
            this.breakRiceCooker(world, i, j, k);
        }
    }

    public boolean isRiceCookerOn(IBlockAccess blockAccess, int i, int j, int k) {
        return this.isRiceCookerOn(blockAccess.getBlockMetadata(i, j, k));
    }

    public boolean isRiceCookerOn(int iMetadata) {
        return (iMetadata & 8) > 0;
    }

    public int setRiceCookerOn(int iMetadata, boolean bOn) {
        iMetadata &= 7;
        if (bOn) {
            iMetadata |= 8;
        }
        return iMetadata;
    }

    public void setRiceCookerOn(World world, int i, int j, int k, boolean bOn) {
        int iMetadata = this.setRiceCookerOn(world.getBlockMetadata(i, j, k), bOn);
        world.setBlockMetadataWithNotify(i, j, k, iMetadata);
        world.playSoundEffect(i,j,k,"kittysfirstaddon69:metal_rumble", 0.5F, 1.0F);
    }

    public void breakRiceCooker(World world, int i, int j, int k){
        InventoryUtils.ejectInventoryContents(world, i, j, k, (IInventory)((Object)world.getBlockTileEntity(i, j, k)));
        world.playSoundEffect(i,j,k, "kittysfirstaddon69:metal_pipe", 0.5F, 1.0F);
        world.setBlockWithNotify(i, j, k, 0);
    }

    protected void updateMechPoweredState(World world, int i, int j, int k, boolean bShouldBePowered) {
        if (this.isRiceCookerOn(world, i, j, k) != bShouldBePowered) {
            this.setRiceCookerOn(world, i, j, k, bShouldBePowered);
        }
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        super.randomDisplayTick(world, i, j, k, random);
        if (isRiceCookerOn(world,i,j,k)) {
            this.emitParticles(world, i, j, k, random);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private void emitParticles(World world, int i, int j, int k, Random random) {
        for (int iTempCount = 0; iTempCount < 5; ++iTempCount) {
            float smokeX = (float)i + random.nextFloat();
            float smokeY = (float)j + random.nextFloat() * 0.5f + 1.0f;
            float smokeZ = (float)k + random.nextFloat();
            world.spawnParticle("smoke", smokeX, smokeY, smokeZ, 0.0, 0.0, 0.0);
        }
    }

}