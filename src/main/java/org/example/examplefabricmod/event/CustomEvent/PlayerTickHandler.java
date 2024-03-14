package org.example.examplefabricmod.event.CustomEvent;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.tutorial.TutorialManager;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.example.examplefabricmod.util.CustomData.ThirstData;
import org.example.examplefabricmod.util.ModArmorMaterials;
import org.example.examplefabricmod.util.ModEntityDataSaver;
import org.example.examplefabricmod.util.ModFullArmorEffect;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer server) {
        // 从服务器全部玩家列表中遍历得到目标玩家
        for (ServerPlayerEntity playerEntity : server.getPlayerManager().getPlayerList()) {
            GameOptions options = null;
            MinecraftClient client = MinecraftClient.getInstance();
            // 判断是否为生存模式
            if (new TutorialManager(client, options).isInSurvival()) {
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
            }
        }
    }
}
