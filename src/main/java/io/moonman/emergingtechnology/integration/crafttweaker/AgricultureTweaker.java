package io.moonman.emergingtechnology.integration.crafttweaker;

import java.util.HashMap;
import java.util.Map;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

public final class AgricultureTweaker {
    private static final Map<String, Boolean> HARVESTER_CROPS = new HashMap<String, Boolean>();
    private static final Map<String, Integer> FILLER_FLUIDS = new HashMap<String, Integer>();
    private static final Map<String, DiffuserConfig> DIFFUSER_GASES = new HashMap<String, DiffuserConfig>();

    private AgricultureTweaker() { }

    public static boolean isRegisteredCrop(Block block) {
        return block != null && block.getRegistryName() != null
                && HARVESTER_CROPS.containsKey(block.getRegistryName().toString());
    }

    public static int getFillerRate(String fluid, int fallback) {
        Integer rate = FILLER_FLUIDS.get(fluid);
        return rate == null ? fallback : rate;
    }

    public static Fluid getFillerFluid() {
        for (String name : FILLER_FLUIDS.keySet()) {
            Fluid fluid = FluidRegistry.getFluid(name);
            if (fluid != null) return fluid;
        }
        return FluidRegistry.WATER;
    }

    public static boolean isRegisteredGas(Fluid fluid) {
        return fluid != null && DIFFUSER_GASES.containsKey(fluid.getName());
    }

    public static double getDiffuserMultiplier(Fluid fluid) {
        DiffuserConfig config = fluid == null ? null : DIFFUSER_GASES.get(fluid.getName());
        return config == null ? 1.0D : config.multiplier;
    }

    public static int getDiffuserRange(Fluid fluid) {
        DiffuserConfig config = fluid == null ? null : DIFFUSER_GASES.get(fluid.getName());
        return config == null ? 0 : config.range;
    }

    @ZenClass("mods.emergingtechnology.Harvester")
    public static class Harvester {
        @ZenMethod
        public static void registerCrop(IItemStack crop) {
            ItemStack stack = toStack(crop);
            Block block = Block.getBlockFromItem(stack.getItem());
            block = cropBlock(stack, block);
            if (block != null) CraftTweakerAPI.apply(new CropAction(block, true));
        }

        @ZenMethod
        public static void removeCrop(IItemStack crop) {
            ItemStack stack = toStack(crop);
            Block block = Block.getBlockFromItem(stack.getItem());
            block = cropBlock(stack, block);
            if (block != null) CraftTweakerAPI.apply(new CropAction(block, false));
        }

        @ZenMethod
        public static void removeAllCrops() {
            CraftTweakerAPI.apply(new IAction() {
                public void apply() { HARVESTER_CROPS.clear(); }
                public String describe() { return "Removing all Harvester crops"; }
            });
        }
    }

    @ZenClass("mods.emergingtechnology.Filler")
    public static class Filler {
        @ZenMethod
        public static void allowFluid(final String name, final int rate) {
            CraftTweakerAPI.apply(new IAction() {
                public void apply() { FILLER_FLUIDS.put(name, rate); }
                public String describe() { return "Allowing Filler fluid " + name; }
            });
        }

        @ZenMethod
        public static void removeFluid(final String name) {
            CraftTweakerAPI.apply(new IAction() {
                public void apply() { FILLER_FLUIDS.remove(name); }
                public String describe() { return "Removing Filler fluid " + name; }
            });
        }

        @ZenMethod
        public static void removeAllFluids() {
            CraftTweakerAPI.apply(new IAction() {
                public void apply() { FILLER_FLUIDS.clear(); }
                public String describe() { return "Removing all Filler fluids"; }
            });
        }
    }

    @ZenClass("mods.emergingtechnology.Diffuser")
    public static class Diffuser {
        @ZenMethod
        public static void allowGas(final String name, final double multiplier, final int range) {
            CraftTweakerAPI.apply(new IAction() {
                public void apply() { DIFFUSER_GASES.put(name, new DiffuserConfig(multiplier, range)); }
                public String describe() { return "Allowing Diffuser gas " + name; }
            });
        }

        @ZenMethod
        public static void removeGas(final String name) {
            CraftTweakerAPI.apply(new IAction() {
                public void apply() { DIFFUSER_GASES.remove(name); }
                public String describe() { return "Removing Diffuser gas " + name; }
            });
        }

        @ZenMethod
        public static void removeAllGases() {
            CraftTweakerAPI.apply(new IAction() {
                public void apply() { DIFFUSER_GASES.clear(); }
                public String describe() { return "Removing all Diffuser gases"; }
            });
        }
    }

    private static class CropAction implements IAction {
        private final Block block;
        private final boolean add;

        CropAction(Block block, boolean add) {
            this.block = block;
            this.add = add;
        }

        public void apply() {
            String name = block.getRegistryName().toString();
            if (add) HARVESTER_CROPS.put(name, true);
            else HARVESTER_CROPS.remove(name);
        }

        public String describe() {
            return (add ? "Adding" : "Removing") + " Harvester crop " + block.getRegistryName();
        }
    }

    private static ItemStack toStack(IItemStack stack) {
        return stack == null ? ItemStack.EMPTY : (ItemStack) stack.getInternal();
    }

    private static Block cropBlock(ItemStack stack, Block block) {
        if (block != null && block != Blocks.AIR) return block;
        String name = stack.getItem().getRegistryName() == null ? "" : stack.getItem().getRegistryName().toString();
        if (name.equals("minecraft:wheat_seeds")) return Blocks.WHEAT;
        if (name.equals("minecraft:carrot")) return Blocks.CARROTS;
        if (name.equals("minecraft:potato")) return Blocks.POTATOES;
        return block;
    }

    private static class DiffuserConfig {
        private final double multiplier;
        private final int range;

        DiffuserConfig(double multiplier, int range) {
            this.multiplier = multiplier;
            this.range = range;
        }
    }
}
