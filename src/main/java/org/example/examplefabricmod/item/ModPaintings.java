package org.example.examplefabricmod.item;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;

public class ModPaintings {

    public static final PaintingVariant MARATHON = registerPaintings("marathon", new PaintingVariant(16, 16));
    public static final PaintingVariant FAMILY = registerPaintings("family", new PaintingVariant(16, 32));

    private static PaintingVariant registerPaintings(String name, PaintingVariant paintingMotive) {
        return Registry.register(Registry.PAINTING_VARIANT, new Identifier(ExampleFabricMod.MOD_ID, name), paintingMotive);
    }

    public static void registerModPaintings() {
        ExampleFabricMod.LOGGER.info("Registering Paintings for " + ExampleFabricMod.MOD_ID);
    }
}
