package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.lib.BlockColorMachine;
import com.lordmau5.wirelessutils.lib.ItemColorMachine;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ModInfo.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClient {

    @SubscribeEvent
    public static void onBlockColors(final RegisterColorHandlersEvent.Block event) {
        event.register(new BlockColorMachine(), BlockColorMachine.getBlocks());
    }

    @SubscribeEvent
    public static void onItemColors(final RegisterColorHandlersEvent.Item event) {
        event.register(new ItemColorMachine(), ItemColorMachine.getItems());
    }
}
