package org.example.examplefabricmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.enchantment.CustomEnchantmen.FreezeEnchantment;

public class ModEnchantments {

    public static final Enchantment FREEZE = registerEnchantment("freeze",
            new FreezeEnchantment());

    public static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(ExampleFabricMod.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        ExampleFabricMod.LOGGER.info("Registering ModEnchantments for " + ExampleFabricMod.MOD_ID);
    }
}
