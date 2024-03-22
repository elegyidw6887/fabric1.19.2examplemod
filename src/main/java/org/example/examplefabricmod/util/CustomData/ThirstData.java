package org.example.examplefabricmod.util.CustomData;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.example.examplefabricmod.network.ModPacketHandler;
import org.example.examplefabricmod.util.ModEntityDataSaver;

public class ThirstData {

    // 减少口渴值
    public static void addThirst(ModEntityDataSaver player, int amount) {
        NbtCompound nbtCompound = player.getPersistentData();
        // 调用.getInt方法并传入关键词参数，该方法在调用后如果未找到对应关键词则会默认返回0
        int thirst = nbtCompound.getInt("thirst");
        if (thirst + amount >= 10) {
            thirst = 10;
        } else {
            thirst += amount;
        }
        // 参数修改后将其余对应的关键词以键值对的形式传入
        nbtCompound.putInt("thirst", thirst);
        // 口渴值修改时时同步数据
        syncThirst(thirst, (ServerPlayerEntity) player);
    }

    // 增加口渴值
    public static void reduceThirst(ModEntityDataSaver player, int amount) {
        NbtCompound nbtCompound = player.getPersistentData();
        int thirst = nbtCompound.getInt("thirst");
        if (thirst - amount > 0) {
            thirst -= amount;
        } else {
            thirst = 0;
        }
        nbtCompound.putInt("thirst", thirst);
        // 口渴值修改时时同步数据
        syncThirst(thirst, (ServerPlayerEntity) player);
    }

    // 同步口渴值
    public static void syncThirst(int thirst, ServerPlayerEntity playerEntity) {
        PacketByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeInt(thirst);
        ServerPlayNetworking.send(playerEntity, ModPacketHandler.THIRST_ID, byteBuf);
    }
}
