package org.example.examplefabricmod.world.gen;

public class ModWorldGeneration {

    public static void worldGeneration() {

        ModTreeGeneration.generationTree();
        ModFlowerGeneration.generateFlower();
        ModOreGeneration.generateOres();
    }
}
