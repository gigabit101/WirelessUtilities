package com.lordmau5.wirelessutils.blocks.desublimator;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockDirectionalDesublimator extends BlockMachineBase
{
    public BlockDirectionalDesublimator(Properties properties)
    {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.directional_desublimator.get().create(pos, state);
    }
}
