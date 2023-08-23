package com.lordmau5.wirelessutils.blockentity;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.client.IOIndicatorLoader;
import com.lordmau5.wirelessutils.lib.MachineLevel;
import com.lordmau5.wirelessutils.lib.block.IFacingBlock;
import com.lordmau5.wirelessutils.lib.block.ISidedMachine;
import com.lordmau5.wirelessutils.lib.block.SidedIO;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BlockEntityMachineBase extends BlockEntity implements ISidedMachine, IFacingBlock {
    private MachineLevel machineLevel = MachineLevel.getMinLevel();

    private final SidedIO sidedIO = new SidedIO();

    public BlockEntityMachineBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    public SidedIO getSidedIO() {
        return sidedIO;
    }

    private void updateBlock() {
        getLevel().sendBlockUpdated(
                getBlockPos(),
                getBlockState(),
                getBlockState(),
                Block.UPDATE_ALL
        );
    }

    public MachineLevel getMachineLevel() {
        return machineLevel;
    }
    
    public void setMachineLevel(MachineLevel machineLevel) {
        this.machineLevel = machineLevel;

        updateBlock();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putInt("machineLevel", getMachineLevel().ordinal());
        saveSidedIONBT(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains("machineLevel", Tag.TAG_INT)) {
            machineLevel = MachineLevel.fromInt(tag.getInt("machineLevel"));
        }

        loadSidedIONBT(tag);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();

        saveAdditional(tag);

        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);

        updateBlock();
        requestModelDataUpdate();
    }

    @Override
    public @NotNull ModelData getModelData() {
        ModelData.Builder builder = ModelData.builder();

        builder.with(IOIndicatorLoader.IOIndicatorModel.PROPERTY, sidedIO.getStates(this::isSideValid));

        return builder.build();
    }

    @Override
    public Direction getFacing() {
        return getBlockState().getValue(BlockMachineBase.FACING).direction;
    }

    public void shuffleStates() {
        for (Direction dir : Direction.values()) {
            if (dir == getFacing()) {
                sidedIO.setState(dir, SidedIO.SidedIOStates.NONE);
                continue;
            }

            sidedIO.setState(dir, SidedIO.SidedIOStates.values()[level.random.nextInt(4)]);
        }

        updateBlock();
    }

    public void advanceIOOnSide(Direction side) {
        if (!isSideValid(side)) return;

        SidedIO.SidedIOStates state = getSidedIO().getState(side);

        int nextState = state.ordinal() + 1;
        if (nextState >= SidedIO.SidedIOStates.values().length)
            nextState = 0;

        getSidedIO().setState(side, SidedIO.SidedIOStates.values()[nextState]);

        updateBlock();
    }
}
