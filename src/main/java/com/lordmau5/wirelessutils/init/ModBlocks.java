package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.blocks.BlockAngledSlime;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModInfo.MODID);

    public static final RegistryObject<Block> ANGLED_SLIME = BLOCKS.register("angled_slime", () -> new BlockAngledSlime());
    public static final RegistryObject<Block> DIRECTIONAL_CHARGER = BLOCKS.register("directional_charger", () -> new BlockAngledSlime());


}
