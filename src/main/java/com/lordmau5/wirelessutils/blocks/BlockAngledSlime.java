package com.lordmau5.wirelessutils.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockAngledSlime extends BlockMachineBase
{
    private static final VoxelShape INTERNAL_BOX = Block.box(-0.4375, -0.4375, -0.4375, 0.4375, 0.4375, 0.4375);

    private static final VoxelShape BOUNDING_BOX = Block.box(0.0625, 0.0625, 0.0625, 0.9375, 0.9375, 0.9375);

    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 3);

    public BlockAngledSlime(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ACTIVE, false).setValue(ROTATION, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(ROTATION);
    }

    //Debug code
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_)
    {
        int rot = blockState.getValue(ROTATION);
        int newRot = rot + 1;
        if(rot == 3) {
            newRot = 0;
        }
        level.setBlockAndUpdate(blockPos, blockState.setValue(ROTATION, newRot));
        return InteractionResult.SUCCESS;
    }
}
