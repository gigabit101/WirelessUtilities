package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.blockentity.charger.BlockEntityDirectionalCharger;
import com.lordmau5.wirelessutils.blockentity.charger.BlockEntityPositionalCharger;
import com.lordmau5.wirelessutils.blockentity.condenser.BlockEntityDirectionalCondenser;
import com.lordmau5.wirelessutils.blockentity.condenser.BlockEntityPositionalCondenser;
import com.lordmau5.wirelessutils.blockentity.desublimator.BlockEntityDirectionalDesublimator;
import com.lordmau5.wirelessutils.blockentity.desublimator.BlockEntityPositionalDesublimator;
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
    public static final RegistryObject<BlockEntityType<BlockEntityDirectionalCondenser>> directional_condenser = register("directional_condenser",
            () -> BlockEntityType.Builder.of(BlockEntityDirectionalCondenser::new, ModBlocks.DIRECTIONAL_CONDENSER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityDirectionalDesublimator>> directional_desublimator = register("directional_desublimator",
            () -> BlockEntityType.Builder.of(BlockEntityDirectionalDesublimator::new, ModBlocks.DIRECTIONAL_DESUBLIMATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlockEntityPositionalCharger>> positional_charger = register("positional_charger",
            () -> BlockEntityType.Builder.of(BlockEntityPositionalCharger::new, ModBlocks.POSITIONAL_CHARGER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityPositionalCondenser>> positional_condenser = register("positional_condenser",
            () -> BlockEntityType.Builder.of(BlockEntityPositionalCondenser::new, ModBlocks.POSITIONAL_CONDENSER.get()).build(null));
    public static final RegistryObject<BlockEntityType<BlockEntityPositionalDesublimator>> positional_desublimator = register("positional_desublimator",
            () -> BlockEntityType.Builder.of(BlockEntityPositionalDesublimator::new, ModBlocks.POSITIONAL_DESUBLIMATOR.get()).build(null));

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(final String name, final Supplier<BlockEntityType<T>> tile) {
        return BLOCK_ENTITIES.register(name, tile);
    }
}
