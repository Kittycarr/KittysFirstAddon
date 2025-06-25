package btw.community.kittystesting.blocks;

import btw.block.tileentity.dispenser.BlockDispenserTileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class ContainerRiceCooker extends Container {
    private TileEntityRiceCooker localTileEntity;
    private static final int NUM_SLOTS = 9;


    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.localTileEntity.isUseableByPlayer(entityPlayer);
    }



    public ContainerRiceCooker(IInventory iInventory, TileEntityRiceCooker tileEntityRiceCooker) {
        this.localTileEntity = tileEntityRiceCooker;
        this.localTileEntity.openChest();
        for (int i = 0; i < 4; ++i) {
            for (int l = 0; l < 4; ++l) {
                this.addSlotToContainer(new Slot(tileEntityRiceCooker, l + i * 4, 53 + l * 18, 17 + i * 18));
            }
        }
        for (int j = 0; j < 3; ++j) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.addSlotToContainer(new Slot(iInventory, i1 + j * 9 + 9, 8 + i1 * 18, 102 + j * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(iInventory, k, 8 + k * 18, 160));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int iSlotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(iSlotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (iSlotIndex < 16 ? !this.mergeItemStack(itemstack1, 16, this.inventorySlots.size(), true) : !this.mergeItemStack(itemstack1, 0, 16, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    public ItemStack slotClick(int i, int j, int k, EntityPlayer entityplayer) {
        return super.slotClick(i, j, k, entityplayer);
    }

    @Override
    public void onContainerClosed(EntityPlayer entityplayer) {
        super.onContainerClosed(entityplayer);
        this.localTileEntity.closeChest();
    }

    @Override
    public void onCraftGuiOpened(ICrafting craftingInterface) {
        super.onCraftGuiOpened(craftingInterface);
    }
}
