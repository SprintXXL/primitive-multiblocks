package com.SprintXXL.primitivemultiblocks.formation;

import net.minecraft.util.math.BlockPos;

import java.util.*;

public final class FormedMultiblockManager {

    private FormedMultiblockManager() {}

    private static final Map<BlockPos, FormedMultiblock> FORMED_MULTIBLOCKS =
            new HashMap<>();

    private static final Set<FormedMultiblock> ALL_FORMED_MULTIBLOCKS =
            new HashSet<>();

    public static FormedMultiblock getFormedMultiblock(BlockPos pos) {
        return FORMED_MULTIBLOCKS.get(pos);
    }

    public static Set<FormedMultiblock> getAllFormedMultiblocks() {
        return Collections.unmodifiableSet(ALL_FORMED_MULTIBLOCKS);
    }

    public static void registerFormedMultiblock(FormedMultiblock formedMultiblock) {
        ALL_FORMED_MULTIBLOCKS.add(formedMultiblock);

        for (BlockPos pos : formedMultiblock.getOccupiedPositions()) {
            FORMED_MULTIBLOCKS.put(pos, formedMultiblock);
        }
    }

    public static void removeFormedMultiblock(FormedMultiblock formedMultiblock) {
        ALL_FORMED_MULTIBLOCKS.remove(formedMultiblock);

        for (BlockPos pos : formedMultiblock.getOccupiedPositions()) {
            FORMED_MULTIBLOCKS.remove(pos, formedMultiblock);
        }
    }

    public static void clear() {
        ALL_FORMED_MULTIBLOCKS.clear();
        FORMED_MULTIBLOCKS.clear();
    }
}
