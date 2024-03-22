package org.example.examplefabricmod.network.BloodyThirstPackets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.example.examplefabricmod.util.ModEntityDataSaver;

public class DrinkingBloodC2SPackets {

    public static void receive(MinecraftServer server, ServerPlayerEntity playerEntity, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        if (((ModEntityDataSaver) playerEntity).getPersistentData().getInt("bloody_thirst") == 0) {
            playerEntity.sendMessage(Text.literal("You're feeling calm down"));
        }
    }
}
