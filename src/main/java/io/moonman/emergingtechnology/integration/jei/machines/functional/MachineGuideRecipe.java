package io.moonman.emergingtechnology.integration.jei.machines.functional;

import net.minecraft.item.ItemStack;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import java.util.List;

public class MachineGuideRecipe implements IRecipeWrapper {
    private final ItemStack input;
    private final ItemStack output;
    private final String descriptionKey;

    public MachineGuideRecipe(ItemStack input, ItemStack output, String descriptionKey) {
        this.input = input;
        this.output = output;
        this.descriptionKey = descriptionKey;
    }

    public ItemStack getInput() { return input; }
    public ItemStack getOutput() { return output; }
    public String getDescriptionKey() { return descriptionKey; }

    @Override
    public void getIngredients(IIngredients ingredients) {
        if (!input.isEmpty()) ingredients.setInput(VanillaTypes.ITEM, input);
        if (!output.isEmpty()) ingredients.setOutput(VanillaTypes.ITEM, output);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        List<String> lines = minecraft.fontRenderer.listFormattedStringToWidth(
                I18n.format(descriptionKey), 165);
        int y = 2;
        for (String line : lines) {
            minecraft.fontRenderer.drawString(line, 2, y, 0x404040);
            y += 9;
        }
    }
}
