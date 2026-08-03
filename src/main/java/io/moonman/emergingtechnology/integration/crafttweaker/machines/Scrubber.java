package io.moonman.emergingtechnology.integration.crafttweaker.machines;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import io.moonman.emergingtechnology.integration.crafttweaker.CraftTweakerHelper;
import io.moonman.emergingtechnology.recipes.classes.IMachineRecipe;
import io.moonman.emergingtechnology.recipes.machines.ScrubberRecipes;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.emergingtechnology.Scrubber")
public class Scrubber {

    @ZenMethod
    public static void addRecipe(IItemStack output, Object input) {
        IMachineRecipe recipe = CraftTweakerHelper.getMachineRecipe(output, input);
        CraftTweakerAPI.apply(new Add(recipe));
    }

    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        CraftTweakerAPI.apply(new Remove(CraftTweakerHelper.toStack(output)));
    }

    @ZenMethod
    public static void removeAll() {
        CraftTweakerAPI.apply(new RemoveAll());
    }

    private static class Add implements IAction {
        private final IMachineRecipe recipe;

        private Add(IMachineRecipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public void apply() {
            ScrubberRecipes.add(recipe);
        }

        @Override
        public String describe() {
            return "Adding Scrubber Recipe for " + recipe.getOutput().getDisplayName();
        }
    }

    private static class Remove implements IAction {
        private final ItemStack output;

        private Remove(ItemStack output) {
            this.output = output;
        }

        @Override
        public void apply() {
            ScrubberRecipes.removeByOutput(output);
        }

        @Override
        public String describe() {
            return "Removing Scrubber Recipe for " + output.getDisplayName();
        }
    }

    private static class RemoveAll implements IAction {
        @Override
        public void apply() {
            ScrubberRecipes.removeAll();
        }

        @Override
        public String describe() {
            return "Removing all Scrubber Recipes";
        }
    }
}
