package com.lordmau5.wirelessutils.data;

import com.lordmau5.wirelessutils.init.ModBlocks;
import com.lordmau5.wirelessutils.init.ModItems;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneratorItemModels extends ItemModelProvider
{
    public GeneratorItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator.getPackOutput(), ModInfo.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        registerSimpleItem(ModItems.ITEM_ENDER_COIL.get());
        registerSimpleItem(ModItems.ITEM_GLASSES.get());
        registerSimpleItem(ModItems.ITEM_FLUXED_PEARL.get());
        registerSimpleItem(ModItems.ITEM_CHARGED_PEARL.get());
        registerSimpleItem(ModItems.ITEM_QUENCHED_PEARL.get());
//        registerSimpleItem(ModItems.ITEM_STABILIZED_PEARL.get());


        registerBlockModel(ModBlocks.CHUNK_CHARGER.get());
        registerBlockModel(ModBlocks.DIRECTIONAL_CHARGER.get());
        registerBlockModel(ModBlocks.POSITIONAL_CHARGER.get());
    }

    public void registerSimpleItem(Item item)
    {
        singleTexture(getPath(item), mcLoc("item/generated"), "layer0", modLoc("item/" + getPath(item)));
    }

    public String getPath(Item item)
    {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private void registerBlockModel(Block block)
    {
        String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
    }
}
