package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.blockentity.BlockEntityDirectionalCharger;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModInfo.MODID);

    public static final RegistryObject<BlockEntityType<BlockEntityDirectionalCharger>> directional_charger = register("directional_charger",
            () -> BlockEntityType.Builder.of(BlockEntityDirectionalCharger::new, ModBlocks.DIRECTIONAL_CHARGER.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockEntityDirectionalCharger>> positional_charger = register("positional_charger",
            () -> BlockEntityType.Builder.of(BlockEntityDirectionalCharger::new, ModBlocks.POSITIONAL_CHARGER.get()).build(null));

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(final String name, final Supplier<BlockEntityType<T>> tile) {
        return BLOCK_ENTITIES.register(name, tile);
    }
}
