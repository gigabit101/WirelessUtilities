package com.lordmau5.wirelessutils.data;

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

    @Override
    protected void addTranslations()
    {
        add("itemGroup.wirelessutils", "Wireless Utilities");
        add(ModItems.ITEM_VOID_PEARL.get(), "Void Pearl");
    }
}
