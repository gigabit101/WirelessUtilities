package com.lordmau5.wirelessutils.init;

import com.lordmau5.wirelessutils.lib.MachineLevel;
import com.lordmau5.wirelessutils.lib.ModInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModInfo.MODID);

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("wirelessutils", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.ITEM_VOID_PEARL.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.wirelessutils"))
            .displayItems((parameters, output) -> {
                ModItems.ITEMS.getEntries().forEach(itemRegistryObject -> output.accept(itemRegistryObject.get()));

                addLevelVariants(output, ModItems.DIRECTIONAL_CHARGER.get());
            }).build());

    private static void addLevelVariants(CreativeModeTab.Output output, Item item) {
        for (int i = 1; i <= MachineLevel.getMaxLevel().ordinal(); i++) {
            ItemStack stack = new ItemStack(item);
            stack.getOrCreateTag().putInt("machineLevel", i);
            output.accept(stack);
        }
    }
}
