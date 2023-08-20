package com.lordmau5.wirelessutils.data;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.init.ModBlocks;
import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class GeneratorBlockStates extends BlockStateProvider {

    private BlockModelBuilder DIRECTIONAL_MACHINE_BASE;
    private BlockModelBuilder POSITIONAL_MACHINE_BASE;
    private BlockModelBuilder LEVEL_OVERLAY;

    private BlockModelBuilder MARK_DIRECTIONAL;
    private BlockModelBuilder MARK_POSITIONAL;

    private BlockModelBuilder CHARGER_DIRECTIONAL_ACTIVE;
    private BlockModelBuilder CHARGER_DIRECTIONAL_INACTIVE;

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
        registerPositionalMachines();
    }

    private void registerMarks() {
        MARK_DIRECTIONAL = models().getBuilder("block/mark_front")
                .element().face(Direction.NORTH).texture("#texture").end().end()
                .renderType("cutout");

        MARK_POSITIONAL = models().getBuilder("block/mark_top")
                .element().face(Direction.UP).texture("#texture").end().end()
                .renderType("cutout");

        CHARGER_DIRECTIONAL_INACTIVE = models().getBuilder("block/charger/inactive_directional")
                .element().face(Direction.NORTH).texture("#single").end().end()
                .texture("single", modLoc("block/charger_mark"))
                .renderType("cutout");

//        CHARGER_POSITIONAL_ACTIVE = models().getBuilder("block/charger/active")
//                .element().face(Direction.NORTH).texture("#single").end().end()
//                .texture("single", modLoc("block/charger_mark_active"))
//                .renderType("cutout");
//
//        CHARGER_POSITIONAL_INACTIVE = models().getBuilder("block/charger/inactive")
//                .element().face(Direction.NORTH).texture("#single").end().end()
//                .texture("single", modLoc("block/charger_mark"))
//                .renderType("cutout");
    }

    private void registerDirectionalMachines() {
        DIRECTIONAL_MACHINE_BASE = models().getBuilder("block/directional_machine_base")
                .parent(new ModelFile.UncheckedModelFile(mcLoc("minecraft:block/cube_all")))
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

        BlockModelBuilder active = MARK_DIRECTIONAL.texture("texture", modLoc("block/charger_mark_active"));
        BlockModelBuilder inactive = MARK_DIRECTIONAL.texture("texture", modLoc("block/charger_mark"));

        BlockModelBuilder[] models = new BlockModelBuilder[]{active, inactive};
        for (int i = 0; i < 2; i++) {
            boolean isActive = i == 0;

            for (DirectionRotatable dir : DirectionRotatable.values()) {
                // Base textures
                builder.part().modelFile(DIRECTIONAL_MACHINE_BASE)
                        .rotationX(getRotationX(dir.direction))
                        .rotationY((int) dir.getOpposite().direction.toYRot() + (dir.rotation_x ? 90 : 0))
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir);

                // Mark
                builder.part().modelFile(models[i])
                        .rotationX(getRotationX(dir.direction))
                        .rotationY((int) dir.getOpposite().direction.toYRot() + (dir.rotation_x ? 90 : 0))
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir)
                        .condition(BlockMachineBase.ACTIVE, isActive);
            }
        }

        builder.part().modelFile(LEVEL_OVERLAY).addModel();
    }

    private void registerPositionalMachines() {
        POSITIONAL_MACHINE_BASE = models().getBuilder("block/positional_machine_base")
                .parent(new ModelFile.UncheckedModelFile(mcLoc("minecraft:block/cube_all")))
                .element().allFaces((dir, builder) -> {
                    if (dir == Direction.NORTH)
                        builder.texture("#front");
                    else
                        builder.texture("#base");

                    builder.cullface(dir);
                }).end()
                .texture("front", modLoc("block/positional_front"))
                .texture("base", modLoc("block/machine_base"))
                .texture("particle", modLoc("block/machine_base"))
                .renderType("cutout");

        // Charger
        registerPositionalCharger();
    }

    private void registerPositionalCharger() {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(ModBlocks.POSITIONAL_CHARGER.get());

        BlockModelBuilder active = MARK_POSITIONAL.texture("texture", modLoc("block/charger_mark_active"));
        BlockModelBuilder inactive = MARK_POSITIONAL.texture("texture", modLoc("block/charger_mark"));

        BlockModelBuilder[] models = new BlockModelBuilder[]{active, inactive};
        for (int i = 0; i < 2; i++) {
            boolean isActive = i == 0;

            for (DirectionRotatable dir : DirectionRotatable.values()) {
                // Base textures
                builder.part().modelFile(POSITIONAL_MACHINE_BASE)
//                        .rotationX(getRotationX(dir))
                        .rotationY((int) dir.getOpposite().direction.toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir)
                        .condition(BlockMachineBase.ACTIVE, isActive);

                // Mark
                builder.part().modelFile(models[i])
//                        .rotationX(getRotationX(dir))
                        .rotationY((int) dir.getOpposite().direction.toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir)
                        .condition(BlockMachineBase.ACTIVE, isActive);
            }
        }

        builder.part().modelFile(LEVEL_OVERLAY).addModel();
    }
}
