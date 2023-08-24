package com.lordmau5.wirelessutils.lib.block;

import com.lordmau5.wirelessutils.lib.block.SidedIO.SidedIOState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public interface ISidedMachine {

    SidedIO getSidedIO();

    default void loadSidedIONBT(CompoundTag tag) {
        if (!tag.contains("sidedIO", Tag.TAG_COMPOUND)) return;

        CompoundTag sidedIOTag = tag.getCompound("sidedIO");
        for (SidedIO.SidedIOFace face : SidedIO.SidedIOFace.values()) {
            SidedIOState state = SidedIOState.valueOf(sidedIOTag.getString(face.getName()));
            getSidedIO().setState(face, state);
        }
    }

    default void saveSidedIONBT(CompoundTag tag) {
        CompoundTag sidedIOTag = new CompoundTag();
        for (SidedIO.SidedIOFace face : SidedIO.SidedIOFace.values()) {
            sidedIOTag.putString(face.getName(), getSidedIO().getState(face).name());
        }

        tag.put("sidedIO", sidedIOTag);
    }

    default boolean isSideValid(SidedIO.SidedIOFace face) {
        return true;
    }

}
