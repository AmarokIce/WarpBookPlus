package club.someoneice.warpbook.client;

import club.someoneice.warpbook.Main;
import club.someoneice.warpbook.network.PackageButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public final class WarpBookGUI extends Screen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/book.png");

    private final int imageWidth = 176;
    private final int imageHeight = 166;

    private final ItemStack warpBook;

    public WarpBookGUI(ItemStack warpBook) {
        super(Component.literal(""));
        this.warpBook = warpBook;
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderText(guiGraphics);
        this.renderWithTooltip(guiGraphics, mouseX, mouseY, delta);
    }

    public void renderText(GuiGraphics guiGraphics) {
        int textY = 166 - 15;
        var nbt = warpBook.getOrCreateTag();
        for (int i = 0; i < 5; i ++) {
            String name = "posData" + i;
            if (!nbt.contains(name)) continue;

            var nbtData = nbt.getCompound(name);
            final var x = nbtData.getDouble("posX");
            final var y = nbtData.getDouble("posY");
            final var z = nbtData.getDouble("posZ");
            final var level = nbtData.getString("posWorld");

            guiGraphics.drawString(Minecraft.getInstance().font, "PosX: " + x, Color.WHITE.getRed(), 50, textY -i * 60);
            guiGraphics.drawString(Minecraft.getInstance().font, "PosY: " + y, Color.WHITE.getRed(), 50, textY - 20 - i * 60);
            guiGraphics.drawString(Minecraft.getInstance().font, "PosZ: " + z, Color.WHITE.getRed(), 50, textY - 40 - i * 60);
            guiGraphics.drawString(Minecraft.getInstance().font, "World: " + level, Color.WHITE.getRed(), 50, textY - 60 - i * 60);

        }
    }

    @Override
    public void init() {
        super.init();
        var nbt = warpBook.getOrCreateTag();
        for (int i = 0; i < 5; i ++) {
            final int finalI = i;
            Button button_empty = Button.builder(Component.literal("GO!"), it -> {
                Main.PACKET_HANDLER.sendToServer(new PackageButton(finalI));
            }).bounds(176 - 10, 166 - 10 - 60 * i, 20, 20).build();
            String name = "posData" + finalI;
            if (!nbt.contains(name)) {
                button_empty.visible = button_empty.active = false;
            }
            this.addRenderableWidget(button_empty);
        }
    }
}
