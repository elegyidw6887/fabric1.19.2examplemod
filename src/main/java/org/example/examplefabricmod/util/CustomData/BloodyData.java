package org.example.examplefabricmod.util.CustomData;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.example.examplefabricmod.network.ModPacketHandler;
import org.example.examplefabricmod.util.ModEntityDataSaver;

public class BloodyData {

    public static int addBloodyThirst(ModEntityDataSaver player) {
        NbtCompound nbtCompound = player.getPersistentData();
        int bloodyThirst = nbtCompound.getInt("bloody_thirst");
        if (bloodyThirst < 10) {
            bloodyThirst++;
        } else {
            bloodyThirst = 10;
        }
        nbtCompound.putInt("bloody_thirst", bloodyThirst);
        syncThirst(bloodyThirst, (ServerPlayerEntity) player);
        return bloodyThirst;
    }

    public static void reduceBloodyThirst(ModEntityDataSaver player) {
        NbtCompound nbtCompound = player.getPersistentData();
        int bloodyThirst = nbtCompound.getInt("bloody_thirst");
        if (bloodyThirst  > 0) {
            bloodyThirst--;
        } else {
            bloodyThirst = 0;
        }
        nbtCompound.putInt("bloody_thirst", bloodyThirst);
        syncThirst(bloodyThirst, (ServerPlayerEntity) player);
    }

    public static void emptyBloodyThirst(ModEntityDataSaver player) {
        NbtCompound nbtCompound = player.getPersistentData();
        int bloodyThirst = nbtCompound.getInt("bloody_thirst");
        if (bloodyThirst !=0) {
            bloodyThirst =  0;
            nbtCompound.putInt("bloody_thirst", bloodyThirst);
            // 发送数据包调用“DrinkingBloodC2SPackets”数据包
            ClientPlayNetworking.send(ModPacketHandler.DRINKING_BLOOD_ID, PacketByteBufs.create());
            syncThirst(bloodyThirst, (ServerPlayerEntity) player);
        } else {
            return;
        }
    }

    public static void syncThirst(int bloodyThirst, ServerPlayerEntity playerEntity) {
        PacketByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeInt(bloodyThirst);
        ServerPlayNetworking.send(playerEntity, ModPacketHandler.BLOODY_THIRST_ID, byteBuf);
    }
}
