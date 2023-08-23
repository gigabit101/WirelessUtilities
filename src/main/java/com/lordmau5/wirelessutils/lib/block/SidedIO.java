package com.lordmau5.wirelessutils.lib.block;

import net.minecraft.core.Direction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

public class SidedIO {
    public enum SidedIOStates {
        NONE, // Allow input and output at will
        DISABLED, // Don't allow any input or output
        INPUT, // Only allow input
        OUTPUT; // Only allow output

        private final String name = toString().toLowerCase(Locale.ROOT);

        public String getName()
        {
            return name;
        }
    }

    private final HashMap<Direction, SidedIOStates> ioStates = new HashMap<>();

    public void setState(Direction direction, SidedIOStates state) {
        getStates().put(direction, state);
    }

    public SidedIOStates getState(Direction direction) {
        return getStates().getOrDefault(direction, SidedIOStates.NONE);
    }

    public Map<Direction, SidedIOStates> getStates() {
        return ioStates;
    }

    public Map<Direction, SidedIOStates> getStates(Predicate<Direction> sidePredicate) {
        HashMap<Direction, SidedIOStates> filteredMap = new HashMap<>();

        for(Direction dir : Direction.values()) {
            if (sidePredicate.test(dir)) {
                filteredMap.put(dir, getStates().get(dir));
            }
            else {
                filteredMap.put(dir, SidedIOStates.NONE);
            }
        }

        return filteredMap;
    }

    public Map<Direction, SidedIOStates> getValidStates(Direction... excludedDirections) {
        var excluded = Arrays.stream(excludedDirections).toList();

        Map<Direction, SidedIOStates> tempMap = new HashMap<>();
        for (Direction dir : Direction.values()) {
            if (excluded.contains(dir))
                tempMap.put(dir, SidedIOStates.NONE);
            else
                tempMap.put(dir, getStates().get(dir));
        }
        return tempMap;
    }
}
