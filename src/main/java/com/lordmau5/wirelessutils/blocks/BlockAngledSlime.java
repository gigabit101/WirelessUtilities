package com.lordmau5.wirelessutils.blocks;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BlockAngledSlime extends BlockMachineBase
{
    private static final VoxelShape INTERNAL_BOX = Block.box(-0.4375, -0.4375, -0.4375, 0.4375, 0.4375, 0.4375);

    private static final VoxelShape BOUNDING_BOX = Block.box(0.0625, 0.0625, 0.0625, 0.9375, 0.9375, 0.9375);

    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 3);

    public BlockAngledSlime(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, DirectionRotatable.NORTH).setValue(ACTIVE, false).setValue(ROTATION, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(ROTATION);
    }

    //Debug code
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult)
    {
        int rot = blockState.getValue(ROTATION);
        int newRot = rot + 1;
        if(rot == 3) {
            newRot = 0;
        }
        level.setBlockAndUpdate(blockPos, blockState.setValue(ROTATION, newRot));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter blockGetter, Entity entity)
    {
//        super.updateEntityAfterFallOn(blockGetter, entity);
        Vec3 vec3 = entity.getDeltaMovement();
        if (vec3.y < 0.0D) {
            double d0 = 1.0D;
            entity.setDeltaMovement(0, d0, 0);
        }
    }
}
