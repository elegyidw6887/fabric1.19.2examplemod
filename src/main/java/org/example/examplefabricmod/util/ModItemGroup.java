package org.example.examplefabricmod.util;


import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.item.ModItems;

public class ModItemGroup {

    public static final ItemGroup LOSTsMOD = FabricItemGroupBuilder.build(
            new Identifier(ExampleFabricMod.MOD_ID, "group1"),
            () -> new ItemStack(ModItems.AMETHYST));
}
