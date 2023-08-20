package com.lordmau5.wirelessutils.data;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.init.ModBlocks;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class GeneratorBlockStates extends BlockStateProvider {

    private BlockModelBuilder DIRECTIONAL_MACHINE_BASE;
    private BlockModelBuilder LEVEL_OVERLAY;

    private BlockModelBuilder CHARGER_ACTIVE;
    private BlockModelBuilder CHARGER_INACTIVE;

    public GeneratorBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModInfo.MODID, exFileHelper);
    }

    @Override
    public @NotNull String getName() {
        return "Wireless Utilities - Block States";
    }

    private int getRotationX(Direction dir) {
        return switch (dir) {
            case UP -> -90;
            case DOWN -> 90;
            default -> 0;
        };
    }

    @Override
    protected void registerStatesAndModels() {
        // Level Overlay
        LEVEL_OVERLAY = models().cubeAll("block/machine_level_overlay", modLoc("block/overlay"))
                .renderType("cutout");

        registerMarks();

        registerDirectionalMachines();
    }

    private void registerMarks() {
        CHARGER_ACTIVE = models().getBuilder("block/charger_active")
                .element().face(Direction.NORTH).texture("#single").end().end()
                .texture("single", modLoc("block/charger_mark_active"))
                .renderType("cutout");

        CHARGER_INACTIVE = models().getBuilder("block/charger_inactive")
                .element().face(Direction.NORTH).texture("#single").end().end()
                .texture("single", modLoc("block/charger_mark"))
                .renderType("cutout");
    }

    private void registerDirectionalMachines() {
        DIRECTIONAL_MACHINE_BASE = models().getBuilder("block/directional_machine_base")
                .element().allFaces((dir, builder) -> {
                    if (dir == Direction.NORTH || dir == Direction.SOUTH)
                        builder.texture("#front_back");
                    else
                        builder.texture("#side");

                    builder.cullface(dir);

                    switch (dir) {
                        case UP -> builder.rotation(ModelBuilder.FaceRotation.CLOCKWISE_90);
                        case DOWN -> builder.rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90);
                        case EAST -> builder.rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN);
                        default -> {
                        }
                    }
                }).end()
                .texture("front_back", modLoc("block/machine_base"))
                .texture("side", modLoc("block/machine_side"))
                .texture("particle", modLoc("block/machine_base"))
                .renderType("cutout");

        // Charger
        registerDirectionalCharger();
    }

    private void registerDirectionalCharger() {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(ModBlocks.DIRECTIONAL_CHARGER.get());

        BlockModelBuilder[] models = new BlockModelBuilder[]{CHARGER_ACTIVE, CHARGER_INACTIVE};
        for (int i = 0; i < 2; i++) {
            boolean isActive = i == 0;

            for (Direction dir : Direction.values()) {
                // Base textures
                builder.part().modelFile(DIRECTIONAL_MACHINE_BASE)
                        .rotationX(getRotationX(dir))
                        .rotationY((int) dir.getOpposite().toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir);

                // Mark
                builder.part().modelFile(models[i])
                        .rotationX(getRotationX(dir))
                        .rotationY((int) dir.getOpposite().toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir)
                        .condition(BlockMachineBase.ACTIVE, isActive);
            }
        }

        builder.part().modelFile(LEVEL_OVERLAY).addModel();
    }
}
