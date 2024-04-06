package org.example.examplefabricmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.ModBlocks;
import org.example.examplefabricmod.fluid.ModFluids;
import org.example.examplefabricmod.item.CustomItem.BloodBottleItem;
import org.example.examplefabricmod.item.CustomItem.EightBallItem;
import org.example.examplefabricmod.sound.ModSounds;
import org.example.examplefabricmod.util.ModArmorMaterials;
import org.example.examplefabricmod.util.ModItemGroup;
import org.example.examplefabricmod.util.ModNBTWriter;
import org.example.examplefabricmod.util.ModToolMaterials;
import org.example.examplefabricmod.util.PublicConstructor.ModAxeItem;
import org.example.examplefabricmod.util.PublicConstructor.ModHoeItem;
import org.example.examplefabricmod.util.PublicConstructor.ModPickaxeItem;

import java.util.List;

public class ModItems {

    // 紫水晶
    public static final Item AMETHYST = registerItem("amethyst",
            new Item(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(64)) {
                public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
                    tooltip.add(Text.literal("item.examplefabricmod.amethyst.tooltip"));
                }
            });
    // 紫水晶工具
    public static final Item AMETHYST_SHOVEL = registerItem("amethyst_shovel",
            new ShovelItem(ModToolMaterials.AMETHYST, 1, -3F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_SWORD = registerItem("amethyst_sword",
            new SwordItem(ModToolMaterials.AMETHYST, 3, -2.4F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_AXE = registerItem("amethyst_axe",
            new ModAxeItem(ModToolMaterials.AMETHYST, 4, -3F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_HOE = registerItem("amethyst_hoe",
            new ModHoeItem(ModToolMaterials.AMETHYST, 1, 0F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_PICKAXE = registerItem("amethyst_pickaxe",
            new ModPickaxeItem(ModToolMaterials.AMETHYST, 2, -2.8F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    // 紫水晶碎片燃料物品
    public static final Item FRAGMENT_OF_AMETHYST = registerItem("fragment_of_amethyst",
            new Item(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(64)));
    // 紫水晶盔甲
    public static final Item AMETHYST_HELMET = registerItem("amethyst_helmet",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_CHESTLAPTE = registerItem("amethyst_chestplate",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_LEGGINGS = registerItem("amethyst_leggings",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_BOOTS = registerItem("amethyst_boots",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.FEET, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    //葡萄食物物品
    public static final Item GRAPE = registerItem("grape",
            new Item(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).food(ModFoods.GRAPE)));
    //葡萄作物种子物品
    public static final Item GRAPE_SEEDS = registerItem("grape_seeds",
            new AliasedBlockItem(ModBlocks.GRAPE_VINE, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    // 随机生成数字物品
    public static final Item EIGHT_BALL = registerItem("eight_ball",
            new EightBallItem(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(1)));
    // 唱片物品
    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            new MusicDiscItem(7, ModSounds.BAR_BRAWL, new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(1), 122));
    // 紫水晶弓物品
    public static final Item AMETHYST_BOW = registerItem("amethyst_bow",
            new BowItem(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(1).maxDamage(640)));

    //////////////////////////////////////////////////正式版MOD测试内容//////////////////////////////////////////////////
    // 血水晶
    public static final Item BLOODY_CRYSTAL = registerItem("bloody_crystal",
            new Item(new FabricItemSettings().group(ModItemGroup.THE_LOST_LAND).maxCount(64)));
    // 血晶钻
    public static final Item BLOODY_DIAMOND = registerItem("bloody_diamond",
            new Item(new FabricItemSettings().group(ModItemGroup.THE_LOST_LAND).maxCount(64)));
    // 血晶铁
    public static final Item BLOODY_IRON = registerItem("bloody_iron",
            new Item(new FabricItemSettings().group(ModItemGroup.THE_LOST_LAND).maxCount(64)));
    // 血瓶
    public static final Item BLOOD_BOTTLE = registerItem("blood_bottle",
            new BloodBottleItem(new FabricItemSettings().group(ModItemGroup.THE_LOST_LAND).food(ModFoods.BLOODY_BOTTLE).maxCount(16)));
    // 血桶物品
    public static final Item BLOOD_BUCKET = registerItem("blood_bucket",
            new BucketItem(ModFluids.STILL_BLOOD, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1).group(ModItemGroup.LOSTsMOD)));
    public static final Item BLOODY_JEWEL = registerItem("bloody_jewel",
            new Item(new FabricItemSettings().group(ModItemGroup.THE_LOST_LAND).maxCount(1)) {
                public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
                    tooltip.add(Text.literal("item.examplefabricmod.bloody_jewel.tooltip"));
                }
            });

    // 为物品写入NBT数据
    ModNBTWriter modItemNBTWriter;
    {
        modItemNBTWriter.writeItemNBT(BLOODY_JEWEL, "bloody_thirst", "nbt.examplefabricmod.bloody_thirst");
    }

    private static Item registerItem(String name, Item item) { // 注册物品
        return Registry.register(Registry.ITEM, new Identifier(ExampleFabricMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ExampleFabricMod.LOGGER.info("Registering ModItems for " + ExampleFabricMod.MOD_ID);
    }
}
