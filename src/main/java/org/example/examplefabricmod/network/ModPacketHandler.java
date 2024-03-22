package org.example.examplefabricmod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.network.BloodyThirstPackets.BloodySyncDataS2CPackets;
import org.example.examplefabricmod.network.BloodyThirstPackets.DrinkingBloodC2SPackets;
import org.example.examplefabricmod.network.ThirstPackets.DrinkingC2SPacket;
import org.example.examplefabricmod.network.ThirstPackets.ThirstSyncDataS2CPacket;

public class ModPacketHandler {

    public static final Identifier THIRST_ID = new Identifier(ExampleFabricMod.MOD_ID, "thirst_id");
    public static final Identifier DRINKING_ID = new Identifier(ExampleFabricMod.MOD_ID, "drinking_id");

    public static final Identifier BLOODY_THIRST_ID = new Identifier(ExampleFabricMod.MOD_ID, "bloody_thirst_id");
    public static final Identifier DRINKING_BLOOD_ID = new Identifier(ExampleFabricMod.MOD_ID, "drinking_blood_id");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(DRINKING_ID, DrinkingC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(DRINKING_BLOOD_ID, DrinkingBloodC2SPackets::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(THIRST_ID, ThirstSyncDataS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(BLOODY_THIRST_ID, BloodySyncDataS2CPackets::receive);
    }
}
