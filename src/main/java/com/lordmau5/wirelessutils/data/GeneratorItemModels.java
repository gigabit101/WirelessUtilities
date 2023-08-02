package com.lordmau5.wirelessutils.data;

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
        //eg:
//        singleTexture(getPath(ShrinkItems.SHRINKING_DEVICE.get()), mcLoc("item/handheld"), "layer0", modLoc("item/" + getPath(ShrinkItems.SHRINKING_DEVICE.get())));
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
