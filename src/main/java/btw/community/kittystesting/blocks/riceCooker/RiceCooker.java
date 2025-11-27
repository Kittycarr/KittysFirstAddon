package btw.community.kittystesting.blocks.riceCooker;


import btw.BTWMod;
import btw.block.BTWBlocks;
import btw.block.MechanicalBlock;
import btw.block.util.MechPowerUtils;
import btw.community.kittystesting.blocks.KittysContainers;
import btw.inventory.util.InventoryUtils;
import btw.util.MiscUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

import static net.minecraft.src.BlockDispenser.dispenseBehaviorRegistry;

//Classes to make the rice cooker: kittystesting/blocks/RiceCooker, kittystesting/blocks/ContainerRiceCooker, kittystesting/blocks/ContainerRiceCookerGui,
//                                 kittystesting/blocks/TileEntityRiceCooker, kittystesting/blocks/KittysContainers, example/mixin/This

public class RiceCooker extends BlockContainer implements MechanicalBlock {

    private Icon sideIcon;
    private Icon frontIcon;
    private Icon backIcon;
    private Icon[] iconBySideArray;

    public RiceCooker(int par1) {
        super(par1, Material.iron);
        setUnlocalizedName("kittysricecooker");
        setHardness(2f);
        setPicksEffectiveOn();
        setNonBuoyant();
        setTextureName("kittysfirstaddon69:ricecooker");
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

    public int getFacingBack(int iMetadata) {
        int currentFront = this.getFacing(iMetadata);
        return getBack(currentFront);
    }

    public int getFacingBack(World world, int i, int j, int k) {
        int currentFront = this.getFacing(world, i, j, k);
        return getBack(currentFront);
    }

    public int getBack(int facing) {
        int currentBack = facing;
        switch (facing){
            case 5, 3, 1:
                currentBack = facing-1;
                break;
            case 4, 2, 0:
                currentBack = facing+1;
                break;
        }
        if (currentBack == facing){
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
        int iFacing = MiscUtils.convertOrientationToFlatBlockFacingReversed(entityLiving);
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
    public Icon getIcon(int side, int metadata) {
        if (side == 5) {
            return this.frontIcon;
        }
        if (side == 4){
            return  this.backIcon;
        }
        return this.iconBySideArray[side];
    }

    @Override
    public void registerIcons(IconRegister register) {
        this.iconBySideArray = new Icon[6];
        this.sideIcon = register.registerIcon(this.textureName+"_side");
        this.frontIcon = register.registerIcon(this.textureName+"_front");
        this.backIcon = register.registerIcon(this.textureName+"_back");
        this.iconBySideArray[0] = register.registerIcon(this.textureName+"_bottom");
        this.iconBySideArray[1] = register.registerIcon(this.textureName+"_top");
        this.iconBySideArray[2] = sideIcon;
        this.iconBySideArray[3] = sideIcon;
        this.iconBySideArray[4] = sideIcon;
        this.iconBySideArray[5] = sideIcon;
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public Icon getBlockTexture(IBlockAccess blockAccess, int i, int j, int k, int iSide) {
        int iFacing = this.getFacing(blockAccess, i, j, k);
        int facingBack = this.getFacingBack(blockAccess.getBlockMetadata(i,j,k));
        if (iSide == iFacing) {
            return this.frontIcon;
        }
        if (iSide == facingBack){
            return this.backIcon;
        }
        return this.iconBySideArray[iSide];
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