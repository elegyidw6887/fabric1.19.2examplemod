package org.example.examplefabricmod.block.CustomBlock;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.example.examplefabricmod.entity.BlockEntity.InjectionBenchBlockEntity;
import org.example.examplefabricmod.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class InjectionBenchBlock extends BlockWithEntity implements BlockEntityProvider {

    // 对象与方法参考“AbstractFurnaceBlock”抽象类
    // 与原版相同
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public InjectionBenchBlock(Settings settings) {
        super(settings);
    }

    // 与原版相同
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    // 与原版相同
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    // 与原版相同
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // 我们不需要Boolean对象，因此只需要传入一个参数即可
        builder.add(FACING);
    }

    // 创建方块实体方法
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        // 返回值是一个方块实体
        return new InjectionBenchBlockEntity(pos, state);
    }

    // 获取渲染类型方法
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // 实现参考“AbstractFurnaceBlock”
        return BlockRenderType.MODEL;
    }

    // 状态替换方法
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof InjectionBenchBlockEntity) {
                ItemScatterer.spawn(world, pos, (InjectionBenchBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super .onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // 实体使用方法
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    // 获取“刻”方法
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.INJECTION_BENCH_BLOCK_ENTITY, InjectionBenchBlockEntity::tick);
    }
}
