package com.lordmau5.wirelessutils.lib;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.init.ModBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockColorMachine implements BlockColor {
    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int tintIndex) {
        if (level == null || pos == null || tintIndex != 2) return 0xFFFFFF;

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof BlockEntityMachineBase machineBase)) return 0xFFFFFF;

        return machineBase.getMachineLevel().color;
    }

    public static Block[] getBlocks() {
        return new Block[] {
                ModBlocks.DIRECTIONAL_CHARGER.get(),
                ModBlocks.DIRECTIONAL_CONDENSER.get(),
                ModBlocks.DIRECTIONAL_DESUBLIMATOR.get(),

                ModBlocks.POSITIONAL_CHARGER.get(),
                ModBlocks.POSITIONAL_CONDENSER.get(),
                ModBlocks.POSITIONAL_DESUBLIMATOR.get(),
        };
    }
}
