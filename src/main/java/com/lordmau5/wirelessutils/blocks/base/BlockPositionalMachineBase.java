package com.lordmau5.wirelessutils.blocks.base;

import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BlockPositionalMachineBase extends BlockMachineBase
{
    public BlockPositionalMachineBase(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        DirectionRotatable direction = DirectionRotatable.fromFacing(
                blockPlaceContext.getHorizontalDirection().getOpposite(),
                false
        );
        return this.defaultBlockState().setValue(FACING, direction);
    }

//    @Override
//    public BlockState rotate(BlockState pState, Rotation pRot) {
//        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
//    }
//
//    @Override
//    public BlockState mirror(BlockState pState, Mirror pMirror) {
//        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
//    }
}
