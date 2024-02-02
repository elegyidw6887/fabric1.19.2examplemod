package org.example.examplefabricmod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;

public class ModTags {

    public static class Items {

        public static final TagKey<Item> AMETHYST = createCommonTag("amethyst");

        private static TagKey<Item> createTag(String name) { // 一个存放于resources/data/MOD_ID/tags文件夹中的标签
            return TagKey.of(Registry.ITEM_KEY, new Identifier(ExampleFabricMod.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name) { // 一个存放于resources/data/efm/tags文件夹中的标签
            return TagKey.of(Registry.ITEM_KEY, new Identifier("efm", name));
        }
    }

    public static class Blocks {
        private static TagKey<Block> createTag(String name) { // 一个存放于resources/data/MOD_ID/tags文件夹中的标签
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(ExampleFabricMod.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name) { // 一个存放于resources/data/efm/tags文件夹中的标签
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("efm", name));
        }
    }
}
