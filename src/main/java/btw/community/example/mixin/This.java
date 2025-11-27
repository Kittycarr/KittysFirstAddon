package btw.community.example.mixin;

import btw.community.kittystesting.blocks.riceCooker.ContainerRiceCookerGui;
import btw.community.kittystesting.blocks.KittysContainers;
import btw.community.kittystesting.blocks.riceCooker.TileEntityRiceCooker;
import btw.inventory.BTWContainers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//Classes to make the rice cooker: kittystesting/blocks/RiceCooker, kittystesting/blocks/ContainerRiceCooker, kittystesting/blocks/ContainerRiceCookerGui,
//                                 kittystesting/blocks/TileEntityRiceCooker, kittystesting/blocks/KittysContainers, example/mixin/This

@Mixin(BTWContainers.class)
public class This {
	@Environment(value= EnvType.CLIENT)
	@Inject(at = @At("HEAD"), method = "getAssociatedGui", remap = false, cancellable = true)
	private static void init(EntityClientPlayerMP entityclientplayermp, int containerID, CallbackInfoReturnable<GuiContainer> cir) {
		if (containerID == KittysContainers.riceCookerContainerID ) {
			TileEntityRiceCooker riceCookerEntity = new TileEntityRiceCooker();
			cir.setReturnValue(new ContainerRiceCookerGui(entityclientplayermp.inventory, riceCookerEntity));
		}
	}
}
