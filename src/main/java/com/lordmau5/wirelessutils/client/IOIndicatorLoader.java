package com.lordmau5.wirelessutils.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lordmau5.wirelessutils.blocks.base.BlockMachineBase;
import com.lordmau5.wirelessutils.lib.ModInfo;
import com.lordmau5.wirelessutils.lib.block.SidedIO;
import com.lordmau5.wirelessutils.lib.block.SidedIO.SidedIOState;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/*
 * {
 *   "loader": "wirelessutils:io_indicator",
 *   "parent": "minecraft:block/cube_all",
 *   "textures": {
 *     "all": "forge:white"
 *   }
 * }
 */
public final class IOIndicatorLoader implements IGeometryLoader<IOIndicatorLoader.IOIndicatorGeometry>
{
    @Override
    public IOIndicatorGeometry read(JsonObject json, JsonDeserializationContext ctx) throws JsonParseException
    {
        json.remove("loader");
        UnbakedModel wrapped = ctx.deserialize(json, BlockModel.class);
        return new IOIndicatorGeometry(wrapped);
    }

    public record IOIndicatorGeometry(UnbakedModel wrapped) implements IUnbakedGeometry<IOIndicatorGeometry>
    {
        private static final ResourceLocation TEX_FORGE_WHITE = new ResourceLocation("forge", "white");
        private static final ResourceLocation IO_TEX_BASE_PATH = new ResourceLocation(ModInfo.MODID, "block/io/");
        @SuppressWarnings("deprecation")
        private static final Material MISSING_TEX_MATERIAL = new Material(TextureAtlas.LOCATION_BLOCKS, MissingTextureAtlasSprite.getLocation());
        private static final Map<SidedIOState, Material> IO_TEX_MATERIALS = Util.make(new EnumMap<>(SidedIOState.class), map ->
        {
            for (SidedIOState state : SidedIOState.values())
            {
                //noinspection deprecation
                map.put(state, new Material(TextureAtlas.LOCATION_BLOCKS, IO_TEX_BASE_PATH.withSuffix(state.getName())));
            }
        });

        @Override
        public BakedModel bake(IGeometryBakingContext ctx, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation)
        {
            Map<SidedIOState, BakedModel> ioModels = new EnumMap<>(SidedIOState.class);
            for (SidedIOState state : SidedIOState.values())
            {
                TextureAtlasSprite stateSprite = spriteGetter.apply(IO_TEX_MATERIALS.get(state));
                Function<Material, TextureAtlasSprite> stateSpriteGetter = mat ->
                {
                    if (mat.texture().equals(TEX_FORGE_WHITE))
                    {
                        return stateSprite;
                    }
                    return spriteGetter.apply(MISSING_TEX_MATERIAL);
                };
                ResourceLocation stateModelLocation = modelLocation.withSuffix("_" + state.getName());
                BakedModel model = wrapped.bake(baker, stateSpriteGetter, modelState, stateModelLocation);
                ioModels.put(state, model);
            }
            return new IOIndicatorModel(ioModels);
        }

        @Override
        public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context)
        {
            wrapped.resolveParents(modelGetter);
        }
    }

    public static final class IOIndicatorModel extends BakedModelWrapper<BakedModel>
    {
        public static final ModelProperty<Map<SidedIO.SidedIOFace, SidedIOState>> PROPERTY = new ModelProperty<>();

        private final Map<SidedIOState, BakedModel> ioModels;

        public IOIndicatorModel(Map<SidedIOState, BakedModel> ioModels)
        {
            super(ioModels.get(SidedIOState.NONE));
            this.ioModels = ioModels;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand, ModelData extraData, RenderType renderType)
        {
            if (side != null)
            {
                Map<SidedIO.SidedIOFace, SidedIOState> states = extraData.get(PROPERTY);
                if (states != null)
                {
                    Direction facing = state.getValue(BlockMachineBase.FACING);

                    SidedIOState ioState = states.get(SidedIO.getIOBasedOnFacing(facing ,side));
                    BakedModel model = ioModels.get(ioState);
                    if (ioState == SidedIOState.NONE || model == null) {
                        return List.of();
                    }

                    return model.getQuads(state, side, rand, extraData, renderType);
                }
            }
            return super.getQuads(state, side, rand, extraData, renderType);
        }
    }

    @Mod.EventBusSubscriber(modid = ModInfo.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static final class Registration
    {
        @SubscribeEvent
        public static void onRegisterLoader(ModelEvent.RegisterGeometryLoaders event)
        {
            event.register("io_indicator", new IOIndicatorLoader());
        }

        private Registration() { }
    }
}
