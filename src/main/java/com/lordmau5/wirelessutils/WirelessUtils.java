package com.lordmau5.wirelessutils;

import com.lordmau5.wirelessutils.init.ModBlockEntities;
import com.lordmau5.wirelessutils.init.ModBlocks;
import com.lordmau5.wirelessutils.init.ModCreativeTabs;
import com.lordmau5.wirelessutils.init.ModItems;
import com.lordmau5.wirelessutils.lib.ModInfo;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModInfo.MODID)
public class WirelessUtils
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public WirelessUtils()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);
        // Register Block Entities
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
