package org.example.examplefabricmod.screen.InjectionBenchBlock;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.example.examplefabricmod.ExampleFabricMod;

public class InjectionBenchBlockScreen extends HandledScreen<InjectionBenchBlockScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(
            ExampleFabricMod.MOD_ID,
            "textures/gui/injection_bench_block_gui.png");

    public InjectionBenchBlockScreen(InjectionBenchBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    // 用于初始化的方法
    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    // 用于UI校正的方法
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        // 新增
        if (handler.isCrafting()) {
            drawTexture(matrices, x + 84, y + 22, 176, 14, handler.getScaledProgress(), 36);
            // x默认为-1，y默认为-1；
            // UI默认左上角为（0，0）
            // x的84与y的22就是在GUI中进度条最左上角的像素点位置（我们想要进度条开始进行的位置）
            // u的176与v的14就是我们在GUI中为其准备的进度条的最左上角的像素点位置（我们准备好的进度条）
            // height的36为进度GUI的高度。
        }
        // 新增
        if (handler.hasFuel()) {
            drawTexture(matrices, x + 18, y + 23 + 14 - handler.getScaledFuelProgress(), 176,
                    14 - handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
            // x默认为-1，y默认为-1；
            // UI默认左上角为（0，0）
            // x的18与y的23+14就是在GUI中燃料燃烧进度最左下角的像素点位置，其中14为燃料燃烧进度的y值像素，23为燃料燃烧进度左上角的位置
            // y + 23 + 14 - handler.getScaledFuelProgress()中的handler.getScaledFuelProgress()就是燃料的燃烧进度
            // 因为燃料燃烧的进度与制作进度相反，为渐渐消失，因此需要handler.getScaledFuelProgress()来进行控制
            // u的176与v的14 - handler.getScaledFuelProgress()就是我们在GUI中为其准备的进度条的最左上角的像素点位置
        }
    }

    // 用于渲染的方法
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // 方法实现参考“AbstractFurnaceScreen”中的实现
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
