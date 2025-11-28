package btw.community.kittystesting.blocks.riceCooker;

import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

//Classes to make the rice cooker: kittystesting/blocks/RiceCooker, kittystesting/blocks/ContainerRiceCooker, kittystesting/blocks/ContainerRiceCookerGui,
//                                 kittystesting/blocks/TileEntityRiceCooker, kittystesting/blocks/KittysContainers, example/mixin/This

public class ContainerRiceCookerGui extends GuiContainer {
    static final int SELECTION_ICON_HEIGHT = 20;
    static final int GUI_HEIGHT = 182;
    private static final ResourceLocation RICE_COOKER_GUI = new ResourceLocation("kittysfirstaddon69:textures/gui/container/ricecooker_gui.png");
    private static final ResourceLocation RICE_COOKER_POWERED = new ResourceLocation("kittysfirstaddon69:textures/gui/container/ricecooker_powered.png");
    private static final ResourceLocation RICE_COOKER_FIRED = new ResourceLocation("kittysfirstaddon69:textures/gui/container/ricecooker_fired.png");
    public TileEntityRiceCooker theRiceCooker;

    public ContainerRiceCookerGui(InventoryPlayer inventoryPlayer, TileEntityRiceCooker tileEntityRiceCooker) {
        super(new ContainerRiceCooker(inventoryPlayer, tileEntityRiceCooker));
        this.theRiceCooker = tileEntityRiceCooker;
        this.ySize = 182;
    }

    //foreground layer, which is the text in the inventory
    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        //  par2 is location horizontally     par3 is location vertically
        this.fontRenderer.drawString("Rice Cooker", 106, 10, 0x1F2233);
        this.fontRenderer.drawString("Inventory", 114, this.ySize - 112, 0x404040);
    }

    //background layer
    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.renderEngine.bindTexture(RICE_COOKER_GUI);
        int xPos = (this.width - this.xSize) / 2;
        int yPos = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xPos, yPos, 0, 0, this.xSize, this.ySize);
    }

    protected void drawGuiIcons(boolean powered, boolean fired, int craftProgress){

    }

}
