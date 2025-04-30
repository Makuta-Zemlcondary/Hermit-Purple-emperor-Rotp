package com.zeml.rotp_zhp.client.playeranim.anim.kosmimpl;

import com.github.standobyte.jojo.client.playeranim.anim.kosmximpl.hamon.KosmXWindupAttackHandler;
import com.github.standobyte.jojo.client.playeranim.kosmx.anim.modifier.KosmXFixedFadeModifier;
import com.github.standobyte.jojo.client.playeranim.kosmx.anim.modifier.KosmXHandsideMirrorModifier;
import com.zeml.rotp_zhp.RotpHermitPurpleAddon;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.core.util.Ease;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;

public class KosmxDoxxLeftHandler extends KosmXWindupAttackHandler {

    public KosmxDoxxLeftHandler(ResourceLocation id) {
        super(id);
    }

    @Override
    protected ModifierLayer<IAnimation> createAnimLayer(AbstractClientPlayerEntity player) {
        return new ModifierLayer<>(null, new KosmXHandsideMirrorModifier(player));
    }

    private static final ResourceLocation LEFT = new ResourceLocation(RotpHermitPurpleAddon.MOD_ID, "doxx_left");



    @Override
    public boolean setWindupAnim(PlayerEntity player) {
        return setAnimFromName(player, LEFT, anim-> new KosmxGrabCommandHandler.ChargedAttackAnimPlayer(anim).windupStopsAt(anim.returnToTick));
    }

    @Override
    public boolean setAttackAnim(PlayerEntity player) {
        return setToSwingTick(player, -1, LEFT);
    }

    @Override
    public void stopAnim(PlayerEntity player) {
        fadeOutAnim(player, KosmXFixedFadeModifier.standardFadeIn(10, Ease.OUTCUBIC), null);
    }




}
