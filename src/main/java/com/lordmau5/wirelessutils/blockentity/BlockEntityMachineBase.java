package com.lordmau5.wirelessutils.blockentity;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.lib.MachineLevel;
import net.minecraft.core.BlockPos;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BlockEntityMachineBase extends BlockEntity {
    private MachineLevel machineLevel = MachineLevel.getMinLevel();

    public BlockEntityMachineBase(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
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
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains("machineLevel", Tag.TAG_INT)) {
            machineLevel = MachineLevel.fromInt(tag.getInt("machineLevel"));
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();

        tag.putInt("machineLevel", getMachineLevel().ordinal());

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
    }
}
