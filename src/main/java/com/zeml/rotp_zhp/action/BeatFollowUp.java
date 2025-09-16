package com.zeml.rotp_zhp.action;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.init.power.non_stand.ModPowers;
import com.github.standobyte.jojo.init.power.non_stand.hamon.ModHamonActions;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.zeml.rotp_zhp.action.stand.projectile.HPVineAttack;
import com.zeml.rotp_zhp.entity.damaging.projectile.HPVineEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BeatFollowUp extends StandEntityAction {

    public BeatFollowUp(StandEntityAction.Builder builder){
        super(builder);
    }


    @Override
    protected void perform(World world, LivingEntity user, IStandPower power, ActionTarget target, @Nullable PacketBuffer extraInput) {
        if(!world.isClientSide){
            INonStandPower.getNonStandPowerOptional(user).ifPresent(ipower->{
                Optional<HPVineEntity> vineOp = HPVineAttack.getLandedVineStand(user);
                if(ipower.getTypeSpecificData(ModPowers.HAMON.get()).isPresent() && vineOp.isPresent() &&
                        vineOp.get().getEntityAttachedTo() != null
                ){
                    LivingEntity living = vineOp.get().getEntityAttachedTo();
                    vineOp.get().remove();
                    ipower.setHeldAction(ModHamonActions.HAMON_BEAT.get(), new ActionTarget(living));
                }
            });
        }
    }


}
