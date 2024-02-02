package org.example.examplefabricmod.fluid.CustomFluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.example.examplefabricmod.block.ModBlocks;
import org.example.examplefabricmod.fluid.ModFluids;
import org.example.examplefabricmod.item.ModItems;

public abstract class BloodFluid extends FlowableFluid { // 抽象类

    // 给定的流体是否为该流体的实例
    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    // 获得动态流体
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_BLOOD;
    }

    // 获得静态流体
    @Override
    public Fluid getStill() {
        return ModFluids.STILL_BLOOD;
    }

    // 该流体是否能够像水一样无限生成
    @Override
    protected boolean isInfinite() {
        return true;
    }

    // 流体流入一个可替换的方块时的行为
    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        // 水会掉落方块的战利品表
        // 岩浆会播放“block.lava.extinguish”音效
        Block.dropStacks(state, world, pos, blockEntity);
    }

    // 流体的流动速度
    @Override
    protected int getFlowSpeed(WorldView world) {
        // 水返回4
        // 岩浆在主世界返回2，在下界返回4
        return 3;
    }

    // 流体每流动一格，其等级减少的数值
    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        // 水返回1
        // 岩浆在主世界返回2，在下界返回1
        return 2;
    }

    // 获得流体的桶物品
    @Override
    public Item getBucketItem() {
        return ModItems.BLOOD_BUCKET;
    }

    // 流体能否被其他方块直接替换
    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return true;
    }

    // 流体每流一格需要花费的时间
    @Override
    public int getTickRate(WorldView world) {
        // 水返回5
        // 岩浆在主世界返回30，在下界返回10
        return 10;
    }

    // 流体的爆炸抗性
    @Override
    protected float getBlastResistance() {
        // 水与岩浆均返回100.0F
        return 100F;
    }

    // 方块状态
    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.BLOOD.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    // 动态流体内部类的创建，需要对所在的抽象类进行继承，是该流体的动态流体
    public static class Flowing extends BloodFluid {

        // 附加属性
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        // 是否是静止的
        @Override
        public boolean isStill(FluidState state) {
            // 该内部类为动态流体类，因此是false
            return false;
        }

        // 流体获取等级
        @Override
        public int getLevel(FluidState state) {
            // 动态流体不能被获取
            return state.get(LEVEL);
        }
    }

    // 静态流体内部类的创建，需要对所在的抽象类进行继承，是该流体的静态流体
    public static class Still extends BloodFluid {

        // 是否是静止的
        @Override
        public boolean isStill(FluidState state) {
            // 该内部类为静态流体类，因此是true
            return true;
        }

        // 流体获取等级
        @Override
        public int getLevel(FluidState state) {
            // 流体被获取的等级
            return 8;
        }
    }
}
