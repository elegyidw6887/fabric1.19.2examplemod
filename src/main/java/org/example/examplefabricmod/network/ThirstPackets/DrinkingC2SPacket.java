package org.example.examplefabricmod.network.ThirstPackets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Blocks;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import org.example.examplefabricmod.util.CustomData.ThirstData;
import org.example.examplefabricmod.util.ModEntityDataSaver;

public class DrinkingC2SPacket {

    // 该方法中的全部代码均在服务端进行运行
    public static void receive(MinecraftServer server, ServerPlayerEntity playerEntity, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        // 获取实体所在的世界
        ServerWorld world = playerEntity.getWorld();
        if (isWaterAroundPlayer(playerEntity, world, 1)) {
            // 成功喝水时发送文字提示
            playerEntity.sendMessage(Text.literal("Drinking water").fillStyle(Style.EMPTY), false);
            // 如果判定到玩家实体范围内存在水则播放声音
            world.playSound(null, playerEntity.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 0.5F, world.random.nextFloat());
            // 调用移除口渴值的方法
            ThirstData.removeThirst((ModEntityDataSaver) playerEntity, 1);
            // 发送消息提示
            playerEntity.sendMessage(Text.literal(" Thirst Value " + ((ModEntityDataSaver) playerEntity).getPersistentData().getInt("thirst"))
                    .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
        } else {
            // 如果判定到玩家实体范围内不存在水则发送一条信息提示
            playerEntity.sendMessage(
                    Text.literal(" There is no water around").fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
            ThirstData.syncThirst(((ModEntityDataSaver) playerEntity).getPersistentData().getInt("thirst"), playerEntity);
        }
        // 判断口渴值是否大于8，如果大于8则发送信息提示
        if (((ModEntityDataSaver) playerEntity).getPersistentData().getInt("thirst") >= 8) {
            playerEntity.sendMessage(Text.literal("You absolutely need drink water!").fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
        }
    }

    private static boolean isWaterAroundPlayer(ServerPlayerEntity playerEntity, ServerWorld world, int size) {
        return BlockPos
                // .getVisibilityBoundingBox()获取可见的边间框并进行扩张，扩张大小为传入的参数
                .stream(playerEntity.getVisibilityBoundingBox().expand(size))
                // 获得周遭方块的转改
                .map(world::getBlockState)
                // 调用滤波器对水方块进行检索
                .filter(blockState -> blockState.isOf(Blocks.WATER))
                // 数据转化为数组
                .toArray()
                // 判断长度是否大于0
                .length > 0;
    }
}
