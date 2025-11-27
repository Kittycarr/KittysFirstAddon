package btw.community.kittystesting.blocks.riceCooker;

import btw.inventory.util.InventoryUtils;
import net.minecraft.src.*;

//Classes to make the rice cooker: kittystesting/blocks/RiceCooker, kittystesting/blocks/ContainerRiceCooker, kittystesting/blocks/ContainerRiceCookerGui,
//                                 kittystesting/blocks/TileEntityRiceCooker, kittystesting/blocks/KittysContainers, example/mixin/This

public class TileEntityRiceCooker extends TileEntity implements IInventory {

    private ItemStack[] riceCookerContents = new ItemStack[10];

    @Override
    public int getSizeInventory() {
        return 10;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.riceCookerContents[i];
    }

    @Override
    public ItemStack decrStackSize(int iSlot, int iAmount) {
        return InventoryUtils.decreaseStackSize(this, iSlot, iAmount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (this.riceCookerContents[i] != null) {
            ItemStack itemStack = this.riceCookerContents[i];
            this.riceCookerContents[i] = null;
            return itemStack;
        }
        return null;
    }


    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        super.onInventoryChanged();
        this.riceCookerContents[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return "RiceCooker";
    }


    @Override
    public void readFromNBT(NBTTagCompound nBTTagCompound) {
        super.readFromNBT(nBTTagCompound);
        NBTTagList nBTTagList = nBTTagCompound.getTagList("Items");
        this.riceCookerContents = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nBTTagList.tagCount(); ++i) {
            NBTTagCompound nBTTagCompound2 = (NBTTagCompound)nBTTagList.tagAt(i);
            int n = nBTTagCompound2.getByte("Slot") & 0xFF;
            if (n < 0 || n >= this.riceCookerContents.length) continue;
            this.riceCookerContents[n] = ItemStack.loadItemStackFromNBT(nBTTagCompound2);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nBTTagCompound) {
        super.writeToNBT(nBTTagCompound);
        NBTTagList nBTTagList = new NBTTagList();
        for (int i = 0; i < this.riceCookerContents.length; ++i) {
            if (this.riceCookerContents[i] == null) continue;
            NBTTagCompound nBTTagCompound2 = new NBTTagCompound();
            nBTTagCompound2.setByte("Slot", (byte)i);
            this.riceCookerContents[i].writeToNBT(nBTTagCompound2);
            nBTTagList.appendTag(nBTTagCompound2);
        }
        nBTTagCompound.setTag("Items", nBTTagList);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        if (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
            return false;
        }
        return entityPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean isInvNameLocalized() {
        return true;
    }
}
