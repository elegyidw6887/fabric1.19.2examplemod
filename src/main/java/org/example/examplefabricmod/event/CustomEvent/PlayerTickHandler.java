package org.example.examplefabricmod.event.CustomEvent;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.tutorial.TutorialManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.example.examplefabricmod.effect.ModEffects;
import org.example.examplefabricmod.util.CustomData.BloodyData;
import org.example.examplefabricmod.util.CustomData.ThirstData;
import org.example.examplefabricmod.util.ModArmorMaterials;
import org.example.examplefabricmod.util.ModEntityDataSaver;
import org.example.examplefabricmod.util.ModFullArmorEffect;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick {

    int bloodyTick = 0;

    @Override
    public void onStartTick(MinecraftServer server) {
        // 从服务器全部玩家列表中遍历得到目标玩家
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {

            // 创建客户端对象用于获取玩家实体对象
            MinecraftClient client = MinecraftClient.getInstance();
            // 获取渴血值
            int bloodyValue = ((ModEntityDataSaver) playerEntity).getPersistentData().getInt("bloody_thirst");

            // 口渴值系统
            // 判断是否为生存模式
            if (new TutorialManager(client, null).isInSurvival()) {
                // 随机增加玩家的口渴值
                if (new Random().nextFloat() < 0.001F) {
                    ModEntityDataSaver player = (ModEntityDataSaver) playerEntity;
                    ThirstData.addThirst(player, 1);
                    playerEntity.sendMessage(Text.literal("Adding Thirst Value"));
                }
            }
            // 为装备全套紫水晶的玩家赋予锋利V的永久BUFF
            if (ModFullArmorEffect.getArmorEffect(playerEntity, ModArmorMaterials.AMETHYST)) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 40, 4));
            } else {
                BloodyData.emptyBloodyThirst((ModEntityDataSaver) playerEntity);
            }
            // 渴血值系统
            bloodyTick++;
            if (bloodyValue > 8){
                if (bloodyTick > 600) {
                    BloodyData.reduceBloodyThirst((ModEntityDataSaver) playerEntity);
                    bloodyTick =0;
                }
            } else {
                if (bloodyTick > 1200) {
                    BloodyData.reduceBloodyThirst((ModEntityDataSaver) playerEntity);
                    bloodyTick = 0;
                }
            }
            if (bloodyValue > 8) {
                playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_THIRST, 40, 5));
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 40, 1));
            } else if (bloodyValue > 6) {
                playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_THIRST, 40, 4));
            } else if (bloodyValue > 4) {
                playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_THIRST, 40, 3));
            } else if (bloodyValue >2) {
                playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_THIRST, 40, 2));
            } else if (bloodyValue > 0) {
                playerEntity.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_THIRST, 40, 1));
            }
        }
    }
}
