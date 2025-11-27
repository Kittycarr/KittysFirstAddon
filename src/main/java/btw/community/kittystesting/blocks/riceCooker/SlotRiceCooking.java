package btw.community.kittystesting.blocks.riceCooker;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotRiceCooking extends Slot {

    public SlotRiceCooking(IInventory par1IInventory, int par2, int par3, int par4) {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
        return false;
    }

    @Override
    public void putStack(ItemStack par1ItemStack) {
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {
        return false;
    }
}
