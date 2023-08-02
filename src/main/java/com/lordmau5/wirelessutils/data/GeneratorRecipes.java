package com.lordmau5.wirelessutils.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GeneratorRecipes extends RecipeProvider
{
    public GeneratorRecipes(PackOutput packOutput)
    {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer)
    {
        //Eg:
//        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ShrinkItems.SHRINKING_DEVICE.get())
//                .define('e', Tags.Items.ENDER_PEARLS)
//                .define('g', Tags.Items.GLASS)
//                .define('i', Tags.Items.INGOTS_IRON)
//                .define('b', Items.STONE_BUTTON)
//                .pattern("iei")
//                .pattern("igi")
//                .pattern("ibi")
//                .unlockedBy("has_enderpearls", has(Tags.Items.ENDER_PEARLS))
//                .save(consumer);

    }
}
