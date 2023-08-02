package com.lordmau5.wirelessutils.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockAngledSlime extends BlockMachineBase
{
    private static final VoxelShape INTERNAL_BOX = Block.box(-0.4375, -0.4375, -0.4375, 0.4375, 0.4375, 0.4375);

    private static final VoxelShape BOUNDING_BOX = Block.box(0.0625, 0.0625, 0.0625, 0.9375, 0.9375, 0.9375);

    public BlockAngledSlime(Properties properties)
    {
        super(properties);
    }
}
