package com.lordmau5.wirelessutils.blocks.base;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import com.lordmau5.wirelessutils.lib.DirectionRotationHelper;
import com.lordmau5.wirelessutils.lib.MachineLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class BlockMachineBase extends Block implements EntityBlock
{
    public static final EnumProperty<DirectionRotatable> FACING = EnumProperty.create("facing", DirectionRotatable.class);
    public static final EnumProperty<Rotation> ROTATION = EnumProperty.create("rotation", Rotation.class);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public BlockMachineBase(Properties properties)
    {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, DirectionRotatable.NORTH)
                        .setValue(ROTATION, Rotation.NONE)
                        .setValue(ACTIVE, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        DirectionRotatable direction = DirectionRotatable.fromDirection(
            blockPlaceContext.getNearestLookingDirection().getOpposite()
        );
        Rotation rotation = DirectionRotationHelper.getRotationBasedOnYFacing(direction.direction, blockPlaceContext.getRotation());

        return this.defaultBlockState()
                .setValue(FACING, direction)
                .setValue(ROTATION, rotation);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, ROTATION, ACTIVE);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND && entity instanceof BlockEntityMachineBase machineBase) {
            ItemStack heldItem = pPlayer.getItemInHand(pHand);
            // TODO: Replace this with a wrench icon
            if (heldItem.is(Items.NAME_TAG)) {
                Direction.Axis axis = pHit.getDirection().getAxis();

                DirectionRotatable oldFacing = pState.getValue(FACING);
                DirectionRotatable newFacing = oldFacing;

                Rotation rotation = pState.getValue(ROTATION);
                if (machineBase.canRotateAroundAxis(axis)) {
                    newFacing = newFacing.rotateAround(axis);
                    rotation = DirectionRotationHelper.getRotationBasedOnPreviousAndNewFacing(oldFacing.direction, newFacing.direction, rotation);
                }

                pLevel.setBlockAndUpdate(pPos, pState.setValue(FACING, newFacing).setValue(ROTATION, rotation));
            }
            else {
                // TODO: Do this in the machine and maybe a custom item, too?
                machineBase.advanceIOOnSide(pHit.getDirection());
            }
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (!(entity instanceof BlockEntityMachineBase machineBase)) return;
        if (pLevel.isClientSide) return;

        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains("machineLevel")) {
            MachineLevel level = MachineLevel.fromInt(tag.getInt("machineLevel"));
            machineBase.setMachineLevel(level);
        }

        if (tag.contains("sidedIO", Tag.TAG_COMPOUND)) {
            machineBase.loadSidedIONBT(tag.getCompound("sidedIO"));
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof BlockEntityMachineBase machineBase) {
            CompoundTag tag = stack.getOrCreateTag();

            tag.putInt("machineLevel", machineBase.getMachineLevel().ordinal());
            machineBase.saveSidedIONBT(tag);
        }

        return stack;
    }

    // Used for structure generation and mods like Building Gadgets or similar that can rotate blocks
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        DirectionRotatable direction = state.getValue(FACING);
        Rotation rotationState = state.getValue(ROTATION);

//        DirectionRotatable rotatedDirection = DirectionRotatable.fromDirection(
//            rotation.rotate(state.getValue(FACING).direction)
//        );

        return state.setValue(FACING, direction).setValue(ROTATION, rotationState);
    }

    // Used for structure generation and mods like Building Gadgets or similar that can mirror blocks
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        DirectionRotatable direction = state.getValue(FACING);
        Rotation rotationState = state.getValue(ROTATION);

//        DirectionRotatable mirroredDirection = DirectionRotatable.fromDirection(
////                mirror.mirror(state.getValue(FACING).direction)
////        );

        return state.setValue(FACING, direction).setValue(ROTATION, rotationState);
    }
}
