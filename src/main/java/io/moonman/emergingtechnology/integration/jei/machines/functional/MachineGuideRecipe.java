package io.moonman.emergingtechnology.integration.jei.machines.functional;

import net.minecraft.item.ItemStack;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class MachineGuideRecipe implements IRecipeWrapper {
    private final ItemStack input;
    private final ItemStack output;
    private final String descriptionKey;
    private final String machineId;

    public MachineGuideRecipe(String machineId, ItemStack input, ItemStack output, String descriptionKey) {
        this.machineId = machineId;
        this.input = input;
        this.output = output;
        this.descriptionKey = descriptionKey;
    }

    public ItemStack getInput() { return input; }
    public ItemStack getOutput() { return output; }
    public String getDescriptionKey() { return descriptionKey; }
    public String getMachineId() { return machineId; }

    @Override
    public void getIngredients(IIngredients ingredients) {
        if (!input.isEmpty()) ingredients.setInput(VanillaTypes.ITEM, input);
        if (!output.isEmpty()) ingredients.setOutput(VanillaTypes.ITEM, output);
    }

}
