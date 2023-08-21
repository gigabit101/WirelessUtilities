package com.lordmau5.wirelessutils.blocks.base;

import com.lordmau5.wirelessutils.blockentity.BlockEntityMachineBase;
import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import com.lordmau5.wirelessutils.lib.MachineLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockMachineBase extends Block implements EntityBlock
{
    public static final EnumProperty<DirectionRotatable> FACING = EnumProperty.create("facing", DirectionRotatable.class);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 6);

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

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND && entity instanceof BlockEntityMachineBase machineBase) {
            MachineLevel machineLevel = machineBase.getMachineLevel();
            int nextLevel = machineLevel.ordinal() + 1;
            if (nextLevel > MachineLevel.getMaxLevel().ordinal()) {
                nextLevel = 0;
            }

            machineBase.setMachineLevel(MachineLevel.fromInt(nextLevel));
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
        if (!tag.contains("level")) return;

        MachineLevel level = MachineLevel.fromInt(tag.getInt("level"));
        machineBase.setMachineLevel(level);
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
