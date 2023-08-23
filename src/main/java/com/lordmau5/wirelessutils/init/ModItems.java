package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.items.ItemBlockMachine;
import com.lordmau5.wirelessutils.items.ItemGlasses;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.MODID);

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


    //Blocks
    public static final RegistryObject<Item> ANGLED_SLIME = ITEMS.register("angled_slime", () -> new BlockItem(ModBlocks.ANGLED_SLIME.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIRECTIONAL_CHARGER = ITEMS.register("directional_charger", () -> new ItemBlockMachine(ModBlocks.DIRECTIONAL_CHARGER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIRECTIONAL_CONDENSER = ITEMS.register("directional_condenser", () -> new ItemBlockMachine(ModBlocks.DIRECTIONAL_CONDENSER.get(), new Item.Properties()));
    public static final RegistryObject<Item> DIRECTIONAL_DESUBLIMATOR = ITEMS.register("directional_desublimator", () -> new ItemBlockMachine(ModBlocks.DIRECTIONAL_DESUBLIMATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> POSITIONAL_CHARGER = ITEMS.register("positional_charger", () -> new ItemBlockMachine(ModBlocks.POSITIONAL_CHARGER.get(), new Item.Properties()));
    public static final RegistryObject<Item> POSITIONAL_CONDENSER = ITEMS.register("positional_condenser", () -> new ItemBlockMachine(ModBlocks.POSITIONAL_CONDENSER.get(), new Item.Properties()));
    public static final RegistryObject<Item> POSITIONAL_DESUBLIMATOR = ITEMS.register("positional_desublimator", () -> new ItemBlockMachine(ModBlocks.POSITIONAL_DESUBLIMATOR.get(), new Item.Properties()));


    public static final List<RegistryObject<Item>> TIERED_MACHINES = Arrays.asList(
            DIRECTIONAL_CHARGER,
            DIRECTIONAL_CONDENSER,
            DIRECTIONAL_DESUBLIMATOR,

            POSITIONAL_CHARGER,
            POSITIONAL_CONDENSER,
            POSITIONAL_DESUBLIMATOR
    );

}
