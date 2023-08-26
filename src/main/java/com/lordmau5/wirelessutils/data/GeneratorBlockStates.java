package com.lordmau5.wirelessutils.data;

import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.init.ModBlocks;
import com.lordmau5.wirelessutils.lib.DirectionRotatable;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class GeneratorBlockStates extends BlockStateProvider {

    private BlockModelBuilder DIRECTIONAL_MACHINE_BASE;
    private BlockModelBuilder POSITIONAL_MACHINE_BASE;
    private BlockModelBuilder LEVEL_OVERLAY;
    private BlockModelBuilder ROTATION_OVERLAY_HORIZONTAL;
    private BlockModelBuilder ROTATION_OVERLAY_UP;
    private BlockModelBuilder ROTATION_OVERLAY_DOWN;

    private BlockModelBuilder IO_OVERLAY;

    private BlockModelBuilder MARK_DIRECTIONAL;
    private BlockModelBuilder MARK_POSITIONAL;

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
        registerOverlays();

        registerDirectionalMachines();
        registerPositionalMachines();
    }

    private void registerOverlays() {
        MARK_DIRECTIONAL = models()
                .getBuilder("block/mark_front")
                    .element()
                        .face(Direction.NORTH)
                        .tintindex(1)
                        .texture("#texture")
                    .end()
                .end()
                .renderType("cutout");

        MARK_POSITIONAL = models()
                .getBuilder("block/mark_top")
                    .element()
                        .face(Direction.UP)
                        .tintindex(1)
                        .texture("#texture")
                    .end()
                .end()
                .renderType("cutout");

        LEVEL_OVERLAY = models().withExistingParent("block/machine_level_overlay", "block/cube")
                .texture("texture", modLoc("block/overlay"))
                .element()
                    .cube("#texture")
                    .faces((dir, builder) -> builder.tintindex(2))
                .end()
                .renderType("cutout");

        // TODO: Better model / file names
        ROTATION_OVERLAY_HORIZONTAL = models()
                .getBuilder("block/rotation_horizontal")
                    .element()
                        .face(Direction.EAST)
                        .texture("#texture")
                    .end()
                .end()
                .texture("texture", modLoc("block/top_rotation"))
                .renderType("cutout");

        ROTATION_OVERLAY_UP = models()
                .getBuilder("block/rotation_up")
                    .element()
                        .face(Direction.UP)
                        .texture("#texture")
                    .end()
                .end()
                .texture("texture", modLoc("block/top_rotation"))
                .renderType("cutout");

        ROTATION_OVERLAY_DOWN = models()
                .getBuilder("block/rotation_down")
                    .element()
                        .face(Direction.DOWN)
                        .rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN)
                        .texture("#texture")
                    .end()
                .end()
                .texture("texture", modLoc("block/top_rotation"))
                .renderType("cutout");

        IO_OVERLAY = models().withExistingParent("block/machine_io_overlay", "wirelessutils:io_indicator");
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
                .renderType("solid");

        registerDirectionalMachine(ModBlocks.DIRECTIONAL_CHARGER.get(), "charger");
        registerDirectionalMachine(ModBlocks.DIRECTIONAL_CONDENSER.get(), "condenser");
        registerDirectionalMachine(ModBlocks.DIRECTIONAL_DESUBLIMATOR.get(), "desublimator");
    }

    private void registerDirectionalMachine(Block block, String type) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        BlockModelBuilder active = models()
                .withExistingParent("block/directional_" + type + "_mark_active", MARK_DIRECTIONAL.getLocation())
                .texture("texture", modLoc("block/" + type + "_mark_active"));
        BlockModelBuilder inactive = models()
                .withExistingParent("block/directional_" + type + "_mark", MARK_DIRECTIONAL.getLocation())
                .texture("texture", modLoc("block/" + type + "_mark"));

        BlockModelBuilder[] models = new BlockModelBuilder[]{active, inactive};
        for (DirectionRotatable dir : DirectionRotatable.values()) {
            for (int i = 0; i < 2; i++) {
                boolean isActive = i == 0;
                // Base textures
                builder.part().modelFile(DIRECTIONAL_MACHINE_BASE)
                        .rotationX(getRotationX(dir.direction))
                        .rotationY((int) dir.getOpposite().direction.toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir);

                // Mark
                builder.part().modelFile(models[i])
                        .rotationX(getRotationX(dir.direction))
                        .rotationY((int) dir.getOpposite().direction.toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir)
                        .condition(BlockMachineBase.ACTIVE, isActive);
            }

            for (Rotation rot : Rotation.values()) {
                switch(dir) {
                    case DOWN -> {
                        builder.part().modelFile(ROTATION_OVERLAY_DOWN)
                                .rotationX(0)
                                .rotationY(rot.ordinal() * -90)
                                .addModel()
                                .condition(BlockMachineBase.FACING, dir)
                                .condition(BlockMachineBase.ROTATION, rot);
                    }
                    case UP -> {
                        builder.part().modelFile(ROTATION_OVERLAY_UP)
                                .rotationX(0)
                                .rotationY(rot.ordinal() * 90)
                                .addModel()
                                .condition(BlockMachineBase.FACING, dir)
                                .condition(BlockMachineBase.ROTATION, rot);
                    }
                    default -> {
                        builder.part().modelFile(ROTATION_OVERLAY_HORIZONTAL)
                                .rotationX(rot.ordinal() * 90)
                                .rotationY((int) dir.direction.toYRot() + 90)
                                .addModel()
                                .condition(BlockMachineBase.FACING, dir)
                                .condition(BlockMachineBase.ROTATION, rot);
                    }
                }
            }
        }

        builder.part().modelFile(LEVEL_OVERLAY).addModel();

        builder.part().modelFile(IO_OVERLAY).addModel();

        models()
                .withExistingParent("block/directional_" + type, "minecraft:block/cube_all")
                .customLoader(CompositeModelBuilder::begin)
                .child("block/directional_machine_base", models().nested().parent(DIRECTIONAL_MACHINE_BASE))
                .child("block/directional_" + type + "_mark", inactive)
                .child("block/machine_level_overlay", models().nested().parent(LEVEL_OVERLAY))
                .end()
                .transforms()
                    .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                        .translation(0, 0, 0)
                        .rightRotation(0, 135, 0)
                        .scale(0.4f, 0.4f, 0.4f)
                    .end()
                    .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                        .translation(0, 0, 0)
                        .leftRotation(0, 135, 0)
                        .scale(0.4f, 0.4f, 0.4f)
                    .end()
                .end();
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
                .renderType("solid");

        registerPositionalMachine(ModBlocks.POSITIONAL_CHARGER.get(), "charger");
        registerPositionalMachine(ModBlocks.POSITIONAL_CONDENSER.get(), "condenser");
        registerPositionalMachine(ModBlocks.POSITIONAL_DESUBLIMATOR.get(), "desublimator");
    }

    private void registerPositionalMachine(Block block, String type) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);

        BlockModelBuilder active = models()
                .withExistingParent("block/positional_" + type + "_mark_active", MARK_POSITIONAL.getLocation())
                .texture("texture", modLoc("block/" + type + "_mark_active"));
        BlockModelBuilder inactive = models()
                .withExistingParent("block/positional_" + type + "_mark", MARK_POSITIONAL.getLocation())
                .texture("texture", modLoc("block/" + type + "_mark"));

        BlockModelBuilder[] models = new BlockModelBuilder[]{active, inactive};
        for (int i = 0; i < 2; i++) {
            boolean isActive = i == 0;

            for (DirectionRotatable dir : DirectionRotatable.values()) {
                // Base textures
                builder.part().modelFile(POSITIONAL_MACHINE_BASE)
                        .rotationY((int) dir.getOpposite().direction.toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir)
                        .condition(BlockMachineBase.ACTIVE, isActive);

                // Mark
                builder.part().modelFile(models[i])
                        .rotationY((int) dir.getOpposite().direction.toYRot())
                        .addModel()
                        .condition(BlockMachineBase.FACING, dir)
                        .condition(BlockMachineBase.ACTIVE, isActive);
            }
        }

        builder.part().modelFile(LEVEL_OVERLAY).addModel();
        builder.part().modelFile(IO_OVERLAY).addModel();

        models()
                .withExistingParent("block/positional_" + type, "minecraft:block/cube_all")
                .customLoader(CompositeModelBuilder::begin)
                .child("block/positional_machine_base", models().nested().parent(POSITIONAL_MACHINE_BASE))
                .child("block/positional_" + type + "_mark", inactive)
                .child("block/machine_level_overlay", models().nested().parent(LEVEL_OVERLAY))
                .end()
                .transforms()
                    .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                        .translation(0, 0, 0)
                        .rightRotation(0, 135, 0)
                        .scale(0.4f, 0.4f, 0.4f)
                    .end()
                    .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                        .translation(0, 0, 0)
                        .leftRotation(0, 135, 0)
                        .scale(0.4f, 0.4f, 0.4f)
                    .end()
                .end();
    }
}
