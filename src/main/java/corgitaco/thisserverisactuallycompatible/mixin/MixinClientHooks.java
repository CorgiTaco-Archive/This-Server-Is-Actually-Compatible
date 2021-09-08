package corgitaco.thisserverisactuallycompatible.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ForgeI18n;
import net.minecraftforge.fml.client.ClientHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.List;

@Mixin(ClientHooks.class)
public class MixinClientHooks {


    @Redirect(method = "drawForgePingInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/AbstractGui;blit(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIIFFIIII)V"))
    private static void thisIsCompatible(MatrixStack stack, int p_238466_1_, int p_238466_2_, int p_238466_3_, int p_238466_4_, float p_238466_5_, float p_238466_6_, int p_238466_7_, int p_238466_8_, int p_238466_9_, int p_238466_10_, MultiplayerScreen gui, ServerData target, MatrixStack mStack, int x, int y, int width, int relativeMouseX, int relativeMouseY) {
        AbstractGui.blit(mStack, x + width - 18, y + 10, 16, 16, 0, 0, 16, 16, 256, 256);
    }

    @Redirect(method = "drawForgePingInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/MultiplayerScreen;setToolTip(Ljava/util/List;)V"))
    private static void compatiblePingInfo(MultiplayerScreen multiplayerScreen, List<ITextComponent> textComponents, MultiplayerScreen gui, ServerData target, MatrixStack mStack, int x, int y, int width, int relativeMouseX, int relativeMouseY) {
        multiplayerScreen.setToolTip(Collections.singletonList(new StringTextComponent(ForgeI18n.parseMessage("fml.menu.multiplayer.compatible", target.forgeData.numberOfMods))));
    }
}
