package com.SprintXXL.primitivemultiblocks.multiblocks;

import java.util.*;

import static com.SprintXXL.primitivemultiblocks.multiblocks.definitions.ModMultiblocks.initModMultiblocks;

public final class MultiblockRegistry {

    private MultiblockRegistry() {}

    private static final Map<String, Multiblock> MULTIBLOCKS =
            new HashMap<>();

    private static final List<Multiblock> ALL_MULTIBLOCKS =
            new ArrayList<>();

    public static Multiblock getMultiblock(String id) {
        return MULTIBLOCKS.get(id);
    }

    public static List<Multiblock> getAllMultiblocks() {
        return Collections.unmodifiableList(ALL_MULTIBLOCKS);
    }

    public static void register(Multiblock multiblock) {

        if (MULTIBLOCKS.containsKey(multiblock.getID())) {
            throw new IllegalArgumentException("Duplicate multiblock ID: " + multiblock.getID());
        }

        MULTIBLOCKS.put(multiblock.getID(), multiblock);
        ALL_MULTIBLOCKS.add(multiblock);
    }

    public static void init() {
        initModMultiblocks();
    }
}
