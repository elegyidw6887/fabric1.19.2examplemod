package org.example.examplefabricmod.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// 继承FabricAPI中AttackEntityCallback接口
public class AttackEntityHandler implements AttackEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        // 判断被攻击的实体是否是羊，以及是否是服务端
        if ( entity instanceof SheepEntity && !world.isClient) {
            // 如果实体是羊则获取玩家ID并输出文字
            player.sendMessage(Text.literal(player.getName().getString()+"! Stop hurt SHEEP!"));
        }
        return ActionResult.PASS;
    }
}
