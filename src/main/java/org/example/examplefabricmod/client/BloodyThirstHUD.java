package org.example.examplefabricmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.tutorial.TutorialManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.util.ModArmorMaterials;
import org.example.examplefabricmod.util.ModEntityDataSaver;
import org.example.examplefabricmod.util.ModFullArmorEffect;

public class BloodyThirstHUD implements HudRenderCallback {

    public static final Identifier FILLED_BOTTLE = new Identifier(ExampleFabricMod.MOD_ID, "textures/hud/bloody_thirst_filled_bottle.png");
    public static final Identifier EMPTY_BOTTLE = new Identifier(ExampleFabricMod.MOD_ID, "textures/hud/bloody_thirst_empty_bottle.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {

        int x = 0;
        int y = 0;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            x = width / 2;
            y = height;
        }

        if (new TutorialManager(client, null).isInSurvival()) {
            // 根据玩家身上的护甲情况来绘制HUD
            if(ModFullArmorEffect.getArmorEffect(MinecraftClient.getInstance().player, ModArmorMaterials.AMETHYST)) {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

                RenderSystem.setShaderTexture(0, EMPTY_BOTTLE);
                for (int i = 0; i < 10; i++) {
                    DrawableHelper.drawTexture(matrixStack, x + 82 - (i * 8), y - 65, 0, 0, 9, 12,
                            9, 12);
                }

                RenderSystem.setShaderTexture(0, FILLED_BOTTLE);
                for (int i = 0; i < 10; i++) {
                    if (((ModEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("bloody_thirst") > i) {
                        DrawableHelper.drawTexture(matrixStack, x + 82 - (i * 8), y - 65, 0, 0, 9, 12,
                                9, 12);
                        // x - 19
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
