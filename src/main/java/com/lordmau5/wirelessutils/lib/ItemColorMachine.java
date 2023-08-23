package com.lordmau5.wirelessutils.lib;

import com.lordmau5.wirelessutils.init.ModItems;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemColorMachine implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex != 2) return 0xFFFFFF;

        CompoundTag tag = stack.getOrCreateTag();

        int level = tag.contains("machineLevel") ? tag.getInt("machineLevel") : 0;

        return MachineLevel.fromInt(level).color;
    }

    public static Item[] getItems() {
        return new Item[] {
                ModItems.DIRECTIONAL_CHARGER.get(),
                ModItems.DIRECTIONAL_CONDENSER.get(),
                ModItems.DIRECTIONAL_DESUBLIMATOR.get(),

                ModItems.POSITIONAL_CHARGER.get(),
                ModItems.POSITIONAL_CONDENSER.get(),
                ModItems.POSITIONAL_DESUBLIMATOR.get(),
        };
    }
}
