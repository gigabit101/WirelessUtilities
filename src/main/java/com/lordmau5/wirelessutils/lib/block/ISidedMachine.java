package com.lordmau5.wirelessutils.lib.block;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import com.lordmau5.wirelessutils.lib.block.SidedIO.SidedIOStates;

public interface ISidedMachine {

    SidedIO sidedIO = new SidedIO();

    default void loadSidedIONBT(CompoundTag tag) {
        if (!tag.contains("sidedIO", Tag.TAG_COMPOUND)) return;

        CompoundTag sidedIOTag = tag.getCompound("sidedIO");
        for (Direction dir : Direction.values()) {
            SidedIOStates state = SidedIOStates.valueOf(sidedIOTag.getString(dir.getName()));
            sidedIO.setState(dir, state);
        }
    }

    default void saveSidedIONBT(CompoundTag tag) {
        CompoundTag sidedIOTag = new CompoundTag();
        for (Direction dir : Direction.values()) {
            sidedIOTag.putString(dir.getName(), sidedIO.getState(dir).name());
        }

        tag.put("sidedIO", sidedIOTag);
    }

    default boolean isDirectionValidForState(Direction dir) {
        return true;
    }

}
