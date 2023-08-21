package com.lordmau5.wirelessutils.blocks.desublimator;

import com.lordmau5.wirelessutils.blocks.base.BlockPositionalMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockPositionalDesublimator extends BlockPositionalMachineBase
{
    public BlockPositionalDesublimator(Properties properties)
    {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.positional_desublimator.get().create(pos, state);
    }
}
