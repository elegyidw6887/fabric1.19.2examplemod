package org.example.examplefabricmod.screen.RelicsBenchBlock;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class RelicsBenchBlockScreen extends HandledScreen<RelicsBenchBlockScreenHandler> {

    public RelicsBenchBlockScreen(RelicsBenchBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

    }
}
