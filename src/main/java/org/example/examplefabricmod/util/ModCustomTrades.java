package org.example.examplefabricmod.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.item.ModItems;

public class ModCustomTrades {

    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> factories.add((entity, random) -> new TradeOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(ModItems.GRAPE, 12),
                        6, 2, 0.02F)));
        ExampleFabricMod.LOGGER.info("Registering CustomTrades for " + ExampleFabricMod.MOD_ID);
    }
}
