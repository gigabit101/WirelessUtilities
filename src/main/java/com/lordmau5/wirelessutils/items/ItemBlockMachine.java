package com.lordmau5.wirelessutils.items;

import com.lordmau5.wirelessutils.lib.MachineLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class ItemBlockMachine extends BlockItem {
    public ItemBlockMachine(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();

        int level = tag.contains("machineLevel") ? tag.getInt("machineLevel") : 0;
        MachineLevel machineLevel = MachineLevel.fromInt(level);

        return machineLevel.getTextComponent(super.getName(stack));
    }
}
