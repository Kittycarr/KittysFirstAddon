package btw.community.kittystesting.blocks;

import net.minecraft.src.*;

//Classes to make the rice cooker: kittystesting/blocks/RiceCooker, kittystesting/blocks/ContainerRiceCooker, kittystesting/blocks/ContainerRiceCookerGui,
//                                 kittystesting/blocks/TileEntityRiceCooker, kittystesting/blocks/KittysContainers, example/mixin/This

public class ContainerRiceCooker extends Container {

    private TileEntityRiceCooker localTileEntity;

    //probably not needed but idk :)
    private static final int NUM_SLOTS = 9;


    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.localTileEntity.isUseableByPlayer(entityPlayer);
    }


    //this is the blueprint for the actual slots, check out the GUI for the textures
    public ContainerRiceCooker(IInventory iinventory, TileEntityRiceCooker tileEntityRiceCooker) {
        this.localTileEntity = tileEntityRiceCooker;
        this.localTileEntity.openChest();
        for (int i = 0; i < 3; ++i) {        //amount of slots vertically
            for (int l = 0; l < 3; ++l) {    //amount of slots horizontally
                this.addSlotToContainer(new Slot(tileEntityRiceCooker, l + i * 4, 62 + l * 18, 17 + i * 18));
                // Placement of slots, can change first number each (before "+")     par3: horizontally    par4: vertically
                // the same with the ones below, those are the playerinventory
            }
        }
        int l2 = 3;
        int i2 = 1;
        this.addSlotToContainer(new Slot(tileEntityRiceCooker, l2 +i2 * 4, 77 + l2 * 18, 17 + i2 * 18));
        //Playerinventory: first 3 rows horizontally
        for (int j = 0; j < 3; ++j) {
            for (int i1 = 0; i1 < 9; ++i1) {
                //this.addSlotToContainer(new Slot(iinventory, i1 + j * 9 + 9, 8 + i1 * 18, 102 + j * 18));
                this.addSlotToContainer(new Slot(iinventory, i1 + j * 9 + 9, 8 + i1 * 18, 84 + j * 18));
            }
        }
        //Playerinventory: last row horizontally, for this one you can just change par4 the same as you did with the others (no need for multipication, whatsoever)
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(iinventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int iSlotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(iSlotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (iSlotIndex < 9 ? !this.mergeItemStack(itemstack1, 9, this.inventorySlots.size(), true) : !this.mergeItemStack(itemstack1, 0, 9, false)) {
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
