package org.example.examplefabricmod.block.CustomBlock;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import org.example.examplefabricmod.item.ModItems;

public class GrapeVineBlock extends CropBlock {

    public GrapeVineBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.GRAPE_SEEDS;
    }
}
