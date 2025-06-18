package eu.midnightdust.swordblocking.mixin;

import eu.midnightdust.swordblocking.SwordBlockingClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class MixinHeldItemRenderer {
    // https://github.com/CCBlueX/LiquidBounce/blob/f1575080097b7f38aa08f1bd74b208e9e0ad5188/src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/item/MixinHeldItemRenderer.java#L97
    @Inject(at = @At("HEAD"), cancellable = true, method = "renderFirstPersonItem")
    public void hideShield(AbstractClientPlayerEntity player, float tickDelta, float pitch,
                           Hand hand, float swingProgress, ItemStack item, float equipProgress,
                           MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                           CallbackInfo ci) {
        if (item.getItem() instanceof ShieldItem && SwordBlockingClient.canWeaponBlock(player)) {
            ci.cancel();
        }
    }
}
