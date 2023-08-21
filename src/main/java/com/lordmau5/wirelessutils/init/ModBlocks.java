package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.blocks.BlockAngledSlime;
import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.blocks.charger.BlockDirectionalCharger;
import com.lordmau5.wirelessutils.blocks.charger.BlockPositionalCharger;
import com.lordmau5.wirelessutils.blocks.condenser.BlockDirectionalCondenser;
import com.lordmau5.wirelessutils.blocks.condenser.BlockPositionalCondenser;
import com.lordmau5.wirelessutils.blocks.desublimator.BlockDirectionalDesublimator;
import com.lordmau5.wirelessutils.blocks.desublimator.BlockPositionalDesublimator;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModInfo.MODID);

    public static final RegistryObject<Block> ANGLED_SLIME = BLOCKS.register("angled_slime", () -> new BlockAngledSlime(BlockBehaviour.Properties.of()));

    public static final RegistryObject<Block> DIRECTIONAL_CHARGER = BLOCKS.register("directional_charger", () -> new BlockDirectionalCharger(BlockBehaviour.Properties.of()));
    public static final RegistryObject<Block> DIRECTIONAL_CONDENSER = BLOCKS.register("directional_condenser", () -> new BlockDirectionalCondenser(BlockBehaviour.Properties.of()));
    public static final RegistryObject<Block> DIRECTIONAL_DESUBLIMATOR = BLOCKS.register("directional_desublimator", () -> new BlockDirectionalDesublimator(BlockBehaviour.Properties.of()));
    public static final RegistryObject<Block> POSITIONAL_CHARGER = BLOCKS.register("positional_charger", () -> new BlockPositionalCharger(BlockBehaviour.Properties.of()));
    public static final RegistryObject<Block> POSITIONAL_CONDENSER = BLOCKS.register("positional_condenser", () -> new BlockPositionalCondenser(BlockBehaviour.Properties.of()));
    public static final RegistryObject<Block> POSITIONAL_DESUBLIMATOR = BLOCKS.register("positional_desublimator", () -> new BlockPositionalDesublimator(BlockBehaviour.Properties.of()));

}
