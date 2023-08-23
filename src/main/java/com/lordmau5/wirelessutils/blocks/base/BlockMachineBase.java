package com.lordmau5.wirelessutils.blocks.base;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import com.lordmau5.wirelessutils.lib.MachineLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class BlockMachineBase extends Block implements EntityBlock
{
    public static final EnumProperty<DirectionRotatable> FACING = EnumProperty.create("facing", DirectionRotatable.class);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public BlockMachineBase(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, DirectionRotatable.NORTH).setValue(ACTIVE, false));
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
        builder.add(FACING, ACTIVE);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND && entity instanceof BlockEntityMachineBase machineBase) {
            MachineLevel machineLevel = machineBase.getMachineLevel();
            int nextLevel = machineLevel.ordinal() + 1;
            if (nextLevel > MachineLevel.getMaxLevel().ordinal()) {
                nextLevel = 0;
            }

//            machineBase.setMachineLevel(MachineLevel.fromInt(nextLevel));
//            machineBase.shuffleStates();
            machineBase.advanceIOOnSide(pHit.getDirection());
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

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return super.rotate(pState, pRotation);
    }

    //        @Override
//    public BlockState rotate(BlockState pState, Rotation pRot) {
//        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
//    }
//
//    @Override
//    public BlockState mirror(BlockState pState, Mirror pMirror) {
//        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
//    }
}
