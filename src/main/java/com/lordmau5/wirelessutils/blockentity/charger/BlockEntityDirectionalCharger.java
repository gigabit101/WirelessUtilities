package com.lordmau5.wirelessutils.blockentity.charger;

import com.lordmau5.wirelessutils.blockentity.BlockEntityDirectionalMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDirectionalCharger extends BlockEntityDirectionalMachineBase {
    public BlockEntityDirectionalCharger(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.directional_charger.get(), pPos, pBlockState);
    }
}
