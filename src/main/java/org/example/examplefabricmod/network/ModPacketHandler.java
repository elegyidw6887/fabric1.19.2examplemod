package org.example.examplefabricmod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.network.packets.DrinkingC2SPacket;
import org.example.examplefabricmod.network.packets.ThirstSyncDataS2CPacket;

public class ModPacketHandler {

    public static final Identifier THIRST_ID = new Identifier(ExampleFabricMod.MOD_ID, "thirst_id");
    public static final Identifier DRINKING_ID = new Identifier(ExampleFabricMod.MOD_ID, "drinking_id");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(DRINKING_ID, DrinkingC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(THIRST_ID, ThirstSyncDataS2CPacket::receive);
    }
}
