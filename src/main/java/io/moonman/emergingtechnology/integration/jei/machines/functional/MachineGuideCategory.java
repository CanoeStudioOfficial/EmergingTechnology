package io.moonman.emergingtechnology.integration.jei.machines.functional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.moonman.emergingtechnology.EmergingTechnology;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class MachineGuideCategory implements IRecipeCategory<MachineGuideRecipe> {
    public static final String UID = EmergingTechnology.MODID + ":functional_machine_guide";
    private final IDrawable background;

    public MachineGuideCategory(IGuiHelper helper) {
        background = helper.createDrawable(new ResourceLocation(
                EmergingTechnology.MODID + ":textures/jei/processorgui.png"), 0, 0, 175, 80);
    }

    @Nonnull
    @Override
    public String getUid() { return UID; }

    @Nonnull
    @Override
    public String getTitle() { return I18n.format("jei.functional_machine_guide.title"); }

    @Override
    public String getModName() { return EmergingTechnology.NAME; }

    @Nonnull
    @Override
    public IDrawable getBackground() { return background; }

    @Nullable
    @Override
    public IDrawable getIcon() { return null; }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) { }

    @Override
    public void setRecipe(IRecipeLayout layout, MachineGuideRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();
        stacks.init(0, true, 16, 34);
        stacks.init(1, false, 79, 34);
        stacks.set(0, recipe.getInput().isEmpty() ? null : recipe.getInput());
        stacks.set(1, recipe.getOutput().isEmpty() ? null : recipe.getOutput());

    }
}
