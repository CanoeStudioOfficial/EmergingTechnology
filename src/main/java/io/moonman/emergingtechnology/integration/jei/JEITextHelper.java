package io.moonman.emergingtechnology.integration.jei;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public final class JEITextHelper {
    private JEITextHelper() { }

    public static void drawDescription(Minecraft minecraft, String key) {
        List<String> lines = minecraft.fontRenderer.listFormattedStringToWidth(I18n.format(key), 165);
        int y = 2;
        for (String line : lines) {
            minecraft.fontRenderer.drawString(line, 2, y, 0x404040);
            y += 9;
        }
    }
}
