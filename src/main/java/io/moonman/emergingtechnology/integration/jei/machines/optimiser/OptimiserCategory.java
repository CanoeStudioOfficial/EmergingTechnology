package io.moonman.emergingtechnology.integration.jei.machines.optimiser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.moonman.emergingtechnology.EmergingTechnology;
import io.moonman.emergingtechnology.integration.jei.machines.MachineReference;
import io.moonman.emergingtechnology.integration.jei.machines.processor.ProcessorRecipeWrapper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class OptimiserCategory implements IRecipeCategory<ProcessorRecipeWrapper> {
    private final IDrawable background;
    private final String title;

    public OptimiserCategory(IGuiHelper helper) {
        background = helper.createDrawable(new ResourceLocation(
                EmergingTechnology.MODID + ":textures/gui/optimisergui.png"), 0, 0, 175, 80);
        title = MachineReference.OPTIMISER_NAME;
    }

    @Nonnull
    @Override
    public String getUid() { return MachineReference.OPTIMISER_UID; }

    @Nonnull
    @Override
    public String getTitle() { return title; }

    @Override
    public String getModName() { return EmergingTechnology.NAME; }

    @Nonnull
    @Override
    public IDrawable getBackground() { return background; }

    @Nullable
    @Override
    public IDrawable getIcon() { return null; }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {
    }

    @Override
    public void setRecipe(IRecipeLayout layout, ProcessorRecipeWrapper wrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();
        stacks.init(0, true, 16, 34);
        stacks.init(1, false, 79, 34);
        stacks.set(ingredients);
    }
}
