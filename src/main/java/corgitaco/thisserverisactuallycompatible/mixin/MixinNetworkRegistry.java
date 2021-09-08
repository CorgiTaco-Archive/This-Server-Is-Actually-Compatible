package corgitaco.thisserverisactuallycompatible.mixin;

import corgitaco.thisserverisactuallycompatible.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(NetworkRegistry.class)
public class MixinNetworkRegistry {

    @Inject(method = "checkListPingCompatibilityForClient", at = @At(value = "HEAD"), remap = false)
    private static void warn(Map<ResourceLocation, Pair<String, Boolean>> incoming, CallbackInfoReturnable<Boolean> cir) {
        Main.LOGGER.warn(Main.MOD_ID + " is in use, forcing the connection checkmark to always be green suggesting the connection is correct even when that may not be the case.");
    }
}
