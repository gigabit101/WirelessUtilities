package com.lordmau5.wirelessutils.lib;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;

public class MachineLevel {
    private static final ArrayList<MachineLevel> levels = new ArrayList<>();

    static {
        addLevel(new MachineLevel(1, Rarity.COMMON, 0xFFFFFF, 5000, 100000, 10, 5, (byte) 30, 5000, 25, 4000, 4, 4, 1, 5, 1000, 3, 120));
        addLevel(new MachineLevel(2, Rarity.COMMON, 0xFF0000, 10000, 200000, 15, 10, (byte) 20, 10000, 100, 16000, 8, 8, 1, 10, 5000, 6, 240));
        addLevel(new MachineLevel(3, Rarity.UNCOMMON, 0xFFFF00, 25000, 500000, 30, 20, (byte) 15, 25000, 250, 40000, 16, 16, 1, 20, 15000, 12, 480));
        addLevel(new MachineLevel(4, Rarity.UNCOMMON, 0x00FF00, 100000, 2000000, 60, 50, (byte) 10, 50000, 1000, 160000, 32, 32, 1, 40, 25000, 24, 960));
        addLevel(new MachineLevel(6, Rarity.RARE, 0x00FFFF, 1000000, 20000000, 120, 100, (byte) 5, 200000, 16000, 2560000, 64, 64, 1, 80, 50000, 48, 1920));
        addLevel(new MachineLevel(9, Rarity.EPIC, 0xFF00FF, Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE, 5, (byte) 0, Long.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true));
    }

    public static MachineLevel getMinLevel() {
        return levels.get(0);
    }

    public static MachineLevel getMaxLevel() {
        return levels.get(levels.size() - 1);
    }

    public static boolean addLevel(MachineLevel level) {
        if ( levels.size() >= Byte.MAX_VALUE )
            return false;

        return levels.add(level);
    }

    public static void clearLevels() {
        levels.clear();
    }

    public static boolean removeLevel(MachineLevel level) {
        return levels.remove(level);
    }

    public static boolean insertLevel(int index, MachineLevel level) {
        if ( levels.size() >= Byte.MAX_VALUE )
            return false;

        levels.add(index, level);
        return true;
    }

    public static MachineLevel getLevel(int index) {
        return levels.get(index);
    }

    public static MachineLevel fromAugment(ItemStack stack) {
//        if ( !stack.isEmpty() && stack.getItem() instanceof ItemAugment )
//            return fromInt(stack.getMetadata());

        return MachineLevel.getMinLevel();
    }

    public static MachineLevel fromItemStack(ItemStack stack) {
        if ( stack == null || stack.isEmpty() )
            return MachineLevel.getMinLevel();

//        // Preparation for 1.13
//        if ( stack.hasTagCompound() ) {
//            NBTTagCompound tag = stack.getTagCompound();
//            if ( tag != null && tag.hasKey("Level") ) {
//                return Level.getLevel(tag.getByte("Level"));
//            }
//        }
        return MachineLevel.fromInt(stack.getDamageValue());
    }

    public static MachineLevel[] values() {
        return levels.toArray(new MachineLevel[0]);
    }

    public static MachineLevel fromInt(int level) {
        if ( level < 0 )
            level = 0;

        if ( level >= levels.size() )
            level = levels.size() - 1;

        return levels.get(level);
    }

    public static int toInt(MachineLevel level) {
        return levels.indexOf(level);
    }

    // The Class Now
    public String name;

    public int augmentSlots;
    public Rarity rarity;
    public boolean isCreative;
    public int color;

    // Chargers
    public long maxChargerCapacity;
    public long maxChargerTransfer;
    public int craftingTPT;

    // Other Machines
    public long maxEnergyCapacity;
    public int baseEnergyPerOperation;
    public byte gatherTicks;

    // Vaporizer
    public int maxVaporizerEntities;
    public int maxVaporizerFluid;
    public int vaporizerBudgetPerTick;
    public int vaporizerMaxBudget;

    // Condensers
    public int maxCondenserCapacity;
    public int maxCondenserTransfer;

    // Desublimators
    public int budgetPerTick;
    public int maxBudget;
    public int costPerItem;

    public MachineLevel(int augmentSlots, Rarity rarity, int color, long maxChargerTransfer,
                 long maxChargerCapacity, int craftingTPT, int baseEnergyPerOperation, byte gatherTicks,
                 long maxEnergyCapacity, int maxCondenserTransfer, int maxCondenserCapacity, int budgetPerTick,
                 int maxBudget, int costPerItem, int maxVaporizerEntities, int maxVaporizerFluid,
                 int vaporizerBudgetPerTick, int vaporizerMaxBudget) {
        this(null, augmentSlots, rarity, color, maxChargerTransfer, maxChargerCapacity, craftingTPT, baseEnergyPerOperation, gatherTicks, maxEnergyCapacity, maxCondenserTransfer, maxCondenserCapacity, budgetPerTick, maxBudget, costPerItem, maxVaporizerEntities, maxVaporizerFluid, vaporizerBudgetPerTick, vaporizerMaxBudget, false);
    }

    public MachineLevel(String name, int augmentSlots, Rarity rarity, int color, long maxChargerTransfer,
                 long maxChargerCapacity, int craftingTPT, int baseEnergyPerOperation, byte gatherTicks,
                 long maxEnergyCapacity, int maxCondenserTransfer, int maxCondenserCapacity, int budgetPerTick,
                 int maxBudget, int costPerItem, int maxVaporizerEntities, int maxVaporizerFluid,
                 int vaporizerBudgetPerTick, int vaporizerMaxBudget) {
        this(name, augmentSlots, rarity, color, maxChargerTransfer, maxChargerCapacity, craftingTPT, baseEnergyPerOperation, gatherTicks, maxEnergyCapacity, maxCondenserTransfer, maxCondenserCapacity, budgetPerTick, maxBudget, costPerItem, maxVaporizerEntities, maxVaporizerFluid, vaporizerBudgetPerTick, vaporizerMaxBudget, false);
    }

    public MachineLevel(int augmentSlots, Rarity rarity, int color, long maxChargerTransfer,
                 long maxChargerCapacity, int craftingTPT, int baseEnergyPerOperation, byte gatherTicks, long maxEnergyCapacity,
                 int maxCondenserTransfer, int maxCondenserCapacity, int budgetPerTick, int maxBudget, int costPerItem,
                 int maxVaporizerEntities, int maxVaporizerFluid, int vaporizerBudgetPerTick, int vaporizerMaxBudget,
                 boolean isCreative) {
        this(null, augmentSlots, rarity, color, maxChargerTransfer, maxChargerCapacity, craftingTPT, baseEnergyPerOperation, gatherTicks, maxEnergyCapacity, maxCondenserTransfer, maxCondenserCapacity, budgetPerTick, maxBudget, costPerItem, maxVaporizerEntities, maxVaporizerFluid, vaporizerBudgetPerTick, vaporizerMaxBudget, isCreative);
    }

    public MachineLevel(String name, int augmentSlots, Rarity rarity, int color, long maxChargerTransfer,
                 long maxChargerCapacity, int craftingTPT, int baseEnergyPerOperation, byte gatherTicks,
                 long maxEnergyCapacity, int maxCondenserTransfer, int maxCondenserCapacity,
                 int budgetPerTick, int maxBudget, int costPerItem,
                 int maxVaporizerEntities, int maxVaporizerFluid, int vaporizerBudgetPerTick, int vaporizerMaxBudget,
                 boolean isCreative) {
        this.name = name;
        this.augmentSlots = augmentSlots;
        this.rarity = rarity;
        this.isCreative = isCreative;
        this.color = color;
        this.maxChargerCapacity = maxChargerCapacity;
        this.maxChargerTransfer = maxChargerTransfer;
        this.craftingTPT = craftingTPT;
        this.maxEnergyCapacity = maxEnergyCapacity;
        this.baseEnergyPerOperation = baseEnergyPerOperation;
        this.gatherTicks = gatherTicks;
        this.maxCondenserTransfer = maxCondenserTransfer;
        this.maxCondenserCapacity = maxCondenserCapacity;
        this.budgetPerTick = budgetPerTick;
        this.maxBudget = maxBudget;
        this.costPerItem = costPerItem;
        this.maxVaporizerEntities = maxVaporizerEntities;
        this.maxVaporizerFluid = maxVaporizerFluid;
        this.vaporizerBudgetPerTick = vaporizerBudgetPerTick;
        this.vaporizerMaxBudget = vaporizerMaxBudget;
    }

    public int ordinal() {
        return toInt();
    }

    public int toInt() {
        return toInt(this);
    }

    public Component getTextComponent() {
        return Component.literal(getName()).withStyle(rarity.getStyleModifier());
    }

    public String getName() {
        if ( name != null )
            return name;

//        String key = "info." + ModInfo.MODID + ".tiered.level." + (isCreative ? "creative" : toInt());
//        if ( StringHelper.canLocalize(key) )
//            return StringHelper.localize(key);

        return Component.translatable(
                "info." + ModInfo.MODID + ".tiered.tier",
                toInt() + 1
        ).toString();
    }
}
