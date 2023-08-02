package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.MODID);

//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(ModBlocks.EXAMPLE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> ITEM_VOID_PEARL = ITEMS.register("void_pearl", () -> new Item(new Item.Properties()));

}
