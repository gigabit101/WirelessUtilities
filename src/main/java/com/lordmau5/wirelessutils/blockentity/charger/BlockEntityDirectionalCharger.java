package com.lordmau5.wirelessutils.blockentity.charger;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityDirectionalCharger extends BlockEntityMachineBase {
    public BlockEntityDirectionalCharger(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.directional_charger.get(), pPos, pBlockState);
    }
}
