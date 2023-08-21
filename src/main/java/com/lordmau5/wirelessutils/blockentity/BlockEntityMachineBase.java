package com.lordmau5.wirelessutils.blockentity;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.lib.MachineLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityMachineBase extends BlockEntity {
    private MachineLevel machineLevel = MachineLevel.getMinLevel();

    public BlockEntityMachineBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public MachineLevel getMachineLevel() {
        return machineLevel;
    }
    
    public void setMachineLevel(MachineLevel machineLevel) {
        this.machineLevel = machineLevel;

        getLevel().setBlockAndUpdate(
                getBlockPos(),
                getBlockState().setValue(BlockMachineBase.LEVEL, machineLevel.ordinal())
        );
    }
}
