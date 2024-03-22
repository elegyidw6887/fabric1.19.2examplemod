package org.example.examplefabricmod.network.BloodyThirstPackets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import org.example.examplefabricmod.util.ModEntityDataSaver;

public class BloodySyncDataS2CPackets {

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf byteBuf, PacketSender sender) {
        ((ModEntityDataSaver) client.player).getPersistentData().putInt("bloody_thirst", byteBuf.readInt());
    }
}
