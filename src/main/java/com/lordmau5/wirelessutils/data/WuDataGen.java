package com.lordmau5.wirelessutils.data;

import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModInfo.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WuDataGen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeClient(), new GeneratorBlockStates(generator.getPackOutput(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new GeneratorRecipes(generator.getPackOutput()));
        generator.addProvider(event.includeClient(), new GeneratorItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new GeneratorLanguage(generator));
    }
}
