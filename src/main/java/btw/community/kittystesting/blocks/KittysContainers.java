package btw.community.kittystesting.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.GuiContainer;

//Classes to make the rice cooker: kittystesting/blocks/RiceCooker, kittystesting/blocks/ContainerRiceCooker, kittystesting/blocks/ContainerRiceCookerGui,
//                                 kittystesting/blocks/TileEntityRiceCooker, kittystesting/blocks/KittysContainers, example/mixin/This

public class KittysContainers {
    public static int riceCookerContainerID = 696;

    @Environment(value= EnvType.CLIENT)
    public static GuiContainer getAssociatedGui(EntityClientPlayerMP entityclientplayermp, int containerID) {
        if (containerID == riceCookerContainerID) {
            TileEntityRiceCooker riceCookerEntity = new TileEntityRiceCooker();
            return new ContainerRiceCookerGui(entityclientplayermp.inventory, riceCookerEntity);
        }
        return null;
    }
}
