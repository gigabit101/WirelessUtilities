package com.lordmau5.wirelessutils.blocks.base;

import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;

public class BlockMachineBase extends Block
{
    public static final EnumProperty<DirectionRotatable> FACING = EnumProperty.create("facing", DirectionRotatable.class);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 10);

    public BlockMachineBase(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, DirectionRotatable.NORTH).setValue(ACTIVE, false).setValue(LEVEL, 0));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        DirectionRotatable direction = DirectionRotatable.fromFacing(
                blockPlaceContext.getNearestLookingDirection().getOpposite(),
                blockPlaceContext.getHorizontalDirection().getAxis().ordinal() != 0
        );
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, ACTIVE, LEVEL);
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
