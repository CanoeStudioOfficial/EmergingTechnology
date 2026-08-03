## TemplateDevEnv
_For Kotlin see [TemplateDevEnvKt](https://github.com/CleanroomMC/TemplateDevEnvKt)_

Template workspace for modding Minecraft 1.12.2. Licensed under MIT, it is made for public use.

This template runs on **Java 25**, **Gradle 9.2.1** + **[RetroFuturaGradle](https://github.com/GTNewHorizons/RetroFuturaGradle) 2.0.2** + **Forge 14.23.5.2847**.

With **coremod and mixin support** that is easy to configure.

### Instructions:

1. Click `use this template` at the top.
2. Clone the repository that you have created with this template to your local machine.
3. Make sure IDEA is using Java 25 for Gradle before you sync the project. Verify this by going to IDEA's `Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`.
4. Open the project folder in IDEA. When prompted, click "Load Gradle Project" as it detects the `build.gradle`, if you weren't prompted, right-click the project's `build.gradle` in IDEA, select `Link Gradle Project`, after completion, hit `Refresh All` in the gradle tab on the right.
5. Run gradle tasks such as `runClient` and `runServer` in the IDEA gradle tab, or use the auto-imported run configurations like `1. Run Client`.

### Notes:
- JEI support: all recipe-based machines have their own JEI recipe category and machine catalyst, including Optimiser. Machines without a conventional recipe list—Harvester, Filler, Diffuser, Piezoelectric, Tidal Generator, Solar, Solar Glass, Wind, and Battery—are registered as JEI information entries so their purpose is visible when looking up the block.
- JEI also includes a `Functional Machine Guide` category with GUI workflow entries for agricultural machines and machines without normal item recipes. These entries show representative inputs, outputs where applicable, and the machine's actual operating conditions.
- CraftTweaker CRT support is provided through separate ZenScript classes for each machine with an independent recipe table: `Biomass`, `Bioreactor`, `AlgaeBioreactor`, `Cooker`, `Fabricator`, `Processor`, `Scaffolder`, `Shredder`, `Injector`, `Optimiser`, `Collector`, `Hydroponic`, `Light`, and `Scrubber`.
- The APIs are intentionally split by machine. Do not treat them as one shared functional-block interface; each class can define machine-specific methods and parameters as its recipe model evolves. For example: `mods.emergingtechnology.Collector.addRecipe(<minecraft:paper>, <minecraft:iron_ingot>);`
- Every currently exposed recipe-table class supports `addRecipe(output, input)`, `removeRecipe(output)`, and `removeAll()`. Machine-specific extensions should be added to the corresponding class only.
- `Harvester`, `Filler`, and `Diffuser` do not currently expose recipe CRT methods because they have no independent recipe table; their behavior is controlled by block logic or another machine's recipes.

#### CraftTweaker API reference

All classes are registered only when CraftTweaker is installed. Recipe changes are applied as CraftTweaker actions, so they can be reviewed in the CraftTweaker log.

| ZenScript class | Add method | Remove method | Special methods |
| --- | --- | --- | --- |
| `mods.emergingtechnology.Biomass` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Bioreactor` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.AlgaeBioreactor` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | `addGas(name)`, `addFluid(name)` |
| `mods.emergingtechnology.Cooker` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Fabricator` | `addRecipe(output, input, count)` | `removeRecipe(output)` / `removeAll()` | `count` controls the fabricator selection count |
| `mods.emergingtechnology.Processor` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Scaffolder` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Shredder` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Injector` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Optimiser` | `addRecipe(input, cores)` | `removeRecipe(output)` / `removeAll()` | `cores` is the optimisation core count |
| `mods.emergingtechnology.Collector` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Hydroponic` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Light` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |
| `mods.emergingtechnology.Scrubber` | `addRecipe(output, input)` | `removeRecipe(output)` / `removeAll()` | — |

`input` accepts either an item stack or a standard CraftTweaker ore-dictionary entry (`<ore:...>`) for every machine recipe API, including the Optimiser. Use an empty output only for machines whose built-in recipe model intentionally uses no item output, such as the default grow-bed and grow-light input lists.

Examples:

```zenscript
mods.emergingtechnology.Processor.addRecipe(<minecraft:iron_ingot>, <minecraft:iron_ore>);
mods.emergingtechnology.Fabricator.addRecipe(<minecraft:paper>, <ore:paper>, 1);
mods.emergingtechnology.Optimiser.addRecipe(<ore:gemDiamond>, 4);
mods.emergingtechnology.AlgaeBioreactor.addGas("co2");
mods.emergingtechnology.AlgaeBioreactor.addFluid("water");
mods.emergingtechnology.Shredder.removeRecipe(<minecraft:paper>);
mods.emergingtechnology.Collector.removeAll();
```

#### Per-machine recipe examples

The following examples are independent. Put them in a `.zs` file under the CraftTweaker `scripts` directory.

##### Biomass Generator

```zenscript
// Convert an item into a biomass generator fuel result.
mods.emergingtechnology.Biomass.addRecipe(<emergingtechnology:biomass>, <minecraft:wheat>);
mods.emergingtechnology.Biomass.removeRecipe(<emergingtechnology:biomass>);
mods.emergingtechnology.Biomass.removeAll();
```

##### Bioreactor

```zenscript
mods.emergingtechnology.Bioreactor.addRecipe(<emergingtechnology:biomass>, <minecraft:kelp>);
mods.emergingtechnology.Bioreactor.removeRecipe(<emergingtechnology:biomass>);
mods.emergingtechnology.Bioreactor.removeAll();
```

##### Algae Bioreactor

```zenscript
mods.emergingtechnology.AlgaeBioreactor.addRecipe(<emergingtechnology:algae>, <minecraft:kelp>);
mods.emergingtechnology.AlgaeBioreactor.addGas("co2");
mods.emergingtechnology.AlgaeBioreactor.addFluid("water");
mods.emergingtechnology.AlgaeBioreactor.removeRecipe(<emergingtechnology:algae>);
mods.emergingtechnology.AlgaeBioreactor.removeAll();
```

`addGas` and `addFluid` add valid gas and fluid inputs to the machine; they are separate from item recipes.

##### Cooker

```zenscript
mods.emergingtechnology.Cooker.addRecipe(<minecraft:cooked_beef>, <minecraft:beef>);
mods.emergingtechnology.Cooker.removeRecipe(<minecraft:cooked_beef>);
mods.emergingtechnology.Cooker.removeAll();
```

##### Fabricator

```zenscript
// The third argument is the recipe count/selection value used by the Fabricator.
mods.emergingtechnology.Fabricator.addRecipe(<minecraft:paper>, <minecraft:sugar_cane>, 1);
mods.emergingtechnology.Fabricator.removeRecipe(<minecraft:paper>);
mods.emergingtechnology.Fabricator.removeAll();
```

##### Processor

```zenscript
mods.emergingtechnology.Processor.addRecipe(<minecraft:iron_ingot>, <minecraft:iron_ore>);
mods.emergingtechnology.Processor.removeRecipe(<minecraft:iron_ingot>);
mods.emergingtechnology.Processor.removeAll();
```

##### Scaffolder

```zenscript
mods.emergingtechnology.Scaffolder.addRecipe(<minecraft:oak_planks>, <minecraft:log>);
mods.emergingtechnology.Scaffolder.removeRecipe(<minecraft:oak_planks>);
mods.emergingtechnology.Scaffolder.removeAll();
```

The Scaffolder also requires a valid scaffold item in its scaffold slot. The CRT recipe controls the sample-to-output conversion.

##### Shredder

```zenscript
mods.emergingtechnology.Shredder.addRecipe(<emergingtechnology:shreddedplant>, <minecraft:wheat>);
mods.emergingtechnology.Shredder.removeRecipe(<emergingtechnology:shreddedplant>);
mods.emergingtechnology.Shredder.removeAll();
```

##### Injector

```zenscript
mods.emergingtechnology.Injector.addRecipe(<minecraft:gold_ingot>, <minecraft:gold_nugget>);
mods.emergingtechnology.Injector.removeRecipe(<minecraft:gold_ingot>);
```

##### Optimiser

```zenscript
// Optimiser has a different signature: input item followed by required core count.
mods.emergingtechnology.Optimiser.addRecipe(<ore:gemDiamond>, 4);
mods.emergingtechnology.Optimiser.removeRecipe(<minecraft:diamond>);
mods.emergingtechnology.Optimiser.removeAll();
```

##### Collector

```zenscript
mods.emergingtechnology.Collector.addRecipe(<minecraft:paper>, <minecraft:iron_ingot>);
mods.emergingtechnology.Collector.removeRecipe(<minecraft:paper>);
mods.emergingtechnology.Collector.removeAll();
```

The Collector chooses a recovered output from its registered collection entries. The exact runtime result may be randomized by the machine.

##### Hydroponic Grow Bed

```zenscript
// Grow-bed entries use an empty output and define a valid growing medium.
mods.emergingtechnology.Hydroponic.addRecipe(null, <minecraft:dirt>);
mods.emergingtechnology.Hydroponic.removeRecipe(null);
mods.emergingtechnology.Hydroponic.removeAll();
```

##### Grow Light

```zenscript
// Grow-light entries use an empty output and define a valid bulb item.
mods.emergingtechnology.Light.addRecipe(null, <minecraft:torch>);
mods.emergingtechnology.Light.removeRecipe(null);
mods.emergingtechnology.Light.removeAll();
```

##### Scrubber

```zenscript
mods.emergingtechnology.Scrubber.addRecipe(<emergingtechnology:biochar>, <minecraft:coal>);
mods.emergingtechnology.Scrubber.removeRecipe(<emergingtechnology:biochar>);
mods.emergingtechnology.Scrubber.removeAll();
```

The Scrubber's gas acceptance is controlled separately by its machine logic. Its item recipe controls the solid input/output conversion.

##### Removing all recipes

Each recipe-table class has its own `removeAll()` action, shown in that machine's example. Removing recipes from one machine does not affect any other machine.

The machine classes are deliberately separate implementations. Several machines currently share the same basic item-recipe shape, but they write to different recipe registries and some have different signatures or extra inputs. Keeping them separate prevents a change to one machine's CRT API from silently changing another machine's behavior.

#### Agricultural machine configuration

Harvester, Filler, and Diffuser use independent behavior APIs rather than item recipe APIs:

```zenscript
// Register vanilla crops by their seed item.
mods.emergingtechnology.Harvester.registerCrop(<minecraft:wheat_seeds>);
mods.emergingtechnology.Harvester.registerCrop(<minecraft:carrot>);
mods.emergingtechnology.Harvester.registerCrop(<minecraft:potato>);

// A mod crop can be registered with its seed item or block item.
mods.emergingtechnology.Harvester.registerCrop(<modid:custom_crop_seed>);
mods.emergingtechnology.Harvester.removeCrop(<minecraft:carrot>);
mods.emergingtechnology.Harvester.removeAllCrops();

// Allow Filler to generate and transfer a fluid at the requested rate.
mods.emergingtechnology.Filler.allowFluid("nutrient", 250);
mods.emergingtechnology.Filler.removeFluid("nutrient");
mods.emergingtechnology.Filler.removeAllFluids();

// Allow Diffuser to accept a gas and configure its agricultural effect.
mods.emergingtechnology.Diffuser.allowGas("co2", 1.5, 8);
mods.emergingtechnology.Diffuser.removeGas("co2");
mods.emergingtechnology.Diffuser.removeAllGases();
```

`registerCrop` accepts a seed item or block item. For vanilla crops, use `<minecraft:wheat_seeds>`, `<minecraft:carrot>`, or `<minecraft:potato>`; for a modded crop, use that mod's seed item or crop block item. The crop's normal Minecraft drop logic is still used for maturity and harvesting. `allowFluid` adds a fluid source to the Filler and controls its transfer amount. `allowGas` adds a gas to the Diffuser's accepted gas list; the existing nozzle and growth logic remain active.

Examples for other farming mods:

```zenscript
// Agricraft: register the Agricraft crop block.
mods.emergingtechnology.Harvester.registerCrop(<agricraft:crop>);

// Mystical Agriculture: register a seed item.
mods.emergingtechnology.Harvester.registerCrop(<mysticalagriculture:inferium_seeds>);

// Actually Additions: use the crop block or seed item for the installed version.
mods.emergingtechnology.Harvester.registerCrop(<actuallyadditions:block_canola>);
```

Registry names can vary between mod versions. Check the item or block ID shown by JEI before adding it. A harvested item such as wheat, tomato, or essence is not automatically a crop block; pass the crop block item or a seed item instead.
- Dependencies script in [gradle/scripts/dependencies.gradle](gradle/scripts/dependencies.gradle), explanations are commented in the file.
- Publishing script in [gradle/scripts/publishing.gradle](gradle/scripts/publishing.gradle).
- When writing Mixins on IntelliJ, it is advisable to use latest [MinecraftDev Fork for RetroFuturaGradle](https://github.com/eigenraven/MinecraftDev/releases).
