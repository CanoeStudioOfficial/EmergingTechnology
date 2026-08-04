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

    public static List<MachineGuideRecipe> getRecipes(String machineId) {
        List<MachineGuideRecipe> recipes = new ArrayList<MachineGuideRecipe>();
        if (machineId.equals("harvester")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(Items.WHEAT_SEEDS), new ItemStack(Items.WHEAT), "jei.guide.harvester"));
        if (machineId.equals("filler")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(Items.WATER_BUCKET), ItemStack.EMPTY, "jei.guide.filler"));
        if (machineId.equals("diffuser")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModItems.biochar), ItemStack.EMPTY, "jei.guide.diffuser"));
        if (machineId.equals("hydroponic")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(Blocks.DIRT), ItemStack.EMPTY, "jei.guide.hydroponic"));
        if (machineId.equals("light")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(Blocks.TORCH), ItemStack.EMPTY, "jei.guide.light"));
        if (machineId.equals("scrubber")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModBlocks.scrubber), new ItemStack(ModItems.biochar), "jei.guide.scrubber"));
        if (machineId.equals("injector")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(Items.WATER_BUCKET), new ItemStack(ModItems.biomass), "jei.guide.injector"));
        if (machineId.equals("biomass")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModItems.biomass), ItemStack.EMPTY, "jei.guide.biomass"));
        if (machineId.equals("solar")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModBlocks.solar), ItemStack.EMPTY, "jei.guide.solar"));
        if (machineId.equals("solarglass")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModBlocks.solarglass), ItemStack.EMPTY, "jei.guide.solarglass"));
        if (machineId.equals("wind")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModBlocks.wind), ItemStack.EMPTY, "jei.guide.wind"));
        if (machineId.equals("tidal")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModBlocks.tidalgenerator), ItemStack.EMPTY, "jei.guide.tidal"));
        if (machineId.equals("piezoelectric")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModBlocks.piezoelectric), ItemStack.EMPTY, "jei.guide.piezoelectric"));
        if (machineId.equals("battery")) recipes.add(new MachineGuideRecipe(machineId, new ItemStack(ModBlocks.battery), ItemStack.EMPTY, "jei.guide.battery"));
        return recipes;
    }
}
