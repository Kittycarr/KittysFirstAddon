package btw.community.kittystesting.blocks;

import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class ContainerRiceCookerGui extends GuiContainer {
    static final int SELECTION_ICON_HEIGHT = 20;
    static final int GUI_HEIGHT = 182;
    private static final ResourceLocation riceCookerGuiTextures = new ResourceLocation("textures/gui/container/dispenser.png");
    public TileEntityRiceCooker theRiceCooker;

    public ContainerRiceCookerGui(InventoryPlayer inventoryPlayer, TileEntityRiceCooker tileEntityRiceCooker) {
        super(new ContainerRiceCooker(inventoryPlayer, tileEntityRiceCooker));
        this.theRiceCooker = tileEntityRiceCooker;
        this.ySize = 182;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        this.fontRenderer.drawString("Rice Cooker", 48, 6, 0x404040);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 94 + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(riceCookerGuiTextures);
        int xPos = (this.width - this.xSize) / 2;
        int yPos = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xPos, yPos, 0, 0, this.xSize, this.ySize);
    }

}
