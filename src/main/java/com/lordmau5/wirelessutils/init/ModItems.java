package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.items.ItemGlasses;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.MODID);

//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(ModBlocks.EXAMPLE_BLOCK.get(), new Item.Properties()));

    //Pearls
    public static final RegistryObject<Item> ITEM_FLUXED_PEARL = ITEMS.register("fluxed_pearl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_CHARGED_PEARL = ITEMS.register("charged_pearl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_QUENCHED_PEARL = ITEMS.register("quenched_pearl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_SCORCHED_PEARL = ITEMS.register("scorched_pearl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_STABILIZED_PEARL = ITEMS.register("stabilized_ender_pearl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_VOID_PEARL = ITEMS.register("void_pearl", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_CRYSTALLIZED_PEARL = ITEMS.register("crystallized_void_pearl", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> ITEM_GLASSES = ITEMS.register("glasses", () -> new ItemGlasses(new Item.Properties()));


    //Crafting components
    public static final RegistryObject<Item> ITEM_ENDER_COIL = ITEMS.register("ender_coil", () -> new Item(new Item.Properties()));


}
