package io.moonman.emergingtechnology.integration.jei.machines.functional;

import java.util.ArrayList;
import java.util.List;

import io.moonman.emergingtechnology.init.ModBlocks;
import io.moonman.emergingtechnology.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class MachineGuideRecipes {
    private MachineGuideRecipes() { }

    public static List<MachineGuideRecipe> getRecipes() {
        List<MachineGuideRecipe> recipes = new ArrayList<MachineGuideRecipe>();
        recipes.add(new MachineGuideRecipe(new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.WHEAT), "jei.guide.harvester"));
        recipes.add(new MachineGuideRecipe(new ItemStack(Items.WATER_BUCKET), ItemStack.EMPTY, "jei.guide.filler"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModItems.biochar), ItemStack.EMPTY, "jei.guide.diffuser"));
        recipes.add(new MachineGuideRecipe(new ItemStack(Blocks.DIRT), ItemStack.EMPTY, "jei.guide.hydroponic"));
        recipes.add(new MachineGuideRecipe(new ItemStack(Blocks.TORCH), ItemStack.EMPTY, "jei.guide.light"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModBlocks.scrubber), new ItemStack(ModItems.biochar), "jei.guide.scrubber"));
        recipes.add(new MachineGuideRecipe(new ItemStack(Items.WATER_BUCKET), new ItemStack(ModItems.biomass), "jei.guide.injector"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModItems.biomass), ItemStack.EMPTY, "jei.guide.biomass"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModBlocks.solar), ItemStack.EMPTY, "jei.guide.solar"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModBlocks.solarglass), ItemStack.EMPTY, "jei.guide.solarglass"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModBlocks.wind), ItemStack.EMPTY, "jei.guide.wind"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModBlocks.tidalgenerator), ItemStack.EMPTY, "jei.guide.tidal"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModBlocks.piezoelectric), ItemStack.EMPTY, "jei.guide.piezoelectric"));
        recipes.add(new MachineGuideRecipe(new ItemStack(ModBlocks.battery), ItemStack.EMPTY, "jei.guide.battery"));
        return recipes;
    }
}
