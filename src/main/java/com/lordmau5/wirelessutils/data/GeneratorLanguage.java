package com.lordmau5.wirelessutils.data;

import com.lordmau5.wirelessutils.init.ModBlocks;
import com.lordmau5.wirelessutils.init.ModItems;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class GeneratorLanguage extends LanguageProvider
{
    public GeneratorLanguage(DataGenerator gen)
    {
        super(gen.getPackOutput(), ModInfo.MODID, "en_us");
    }

    private void addMod(String key, String value)
    {
        add(ModInfo.MODID + "." + key, value);
    }

    @Override
    protected void addTranslations()
    {
        add("itemGroup.wirelessutils", "Wireless Utilities");
        add(ModItems.ITEM_VOID_PEARL.get(), "Void Pearl");
        add(ModItems.ITEM_ENDER_COIL.get(), "Ender Coil");
        add(ModItems.ITEM_GLASSES.get(), "Work Glasses");

        addMod("info.tiered.tier", "(Tier %s)");
        addMod("info.tiered.creative", "(Creative)");

        add(ModBlocks.DIRECTIONAL_CHARGER.get(), "Directional Charger");
        add(ModBlocks.DIRECTIONAL_CONDENSER.get(), "Directional Condenser");
        add(ModBlocks.DIRECTIONAL_DESUBLIMATOR.get(), "Directional Desublimator");

        add(ModBlocks.POSITIONAL_CHARGER.get(), "Positional Charger");
        add(ModBlocks.POSITIONAL_CONDENSER.get(), "Positional Condenser");
        add(ModBlocks.POSITIONAL_DESUBLIMATOR.get(), "Positional Desublimator");
    }
}
