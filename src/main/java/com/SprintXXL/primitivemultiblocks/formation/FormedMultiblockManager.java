package com.SprintXXL.primitivemultiblocks.formation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class FormedMultiblockManager {

    private FormedMultiblockManager() {}

    private static final Set<FormedMultiblock> FORMED_MULTIBLOCKS =
            new HashSet<>();

    public static Set<FormedMultiblock> getAllFormedMultiblocks() {
        return Collections.unmodifiableSet(FORMED_MULTIBLOCKS);
    }

    public static void registerFormedMultiblock(FormedMultiblock formedMultiblock) {
        FORMED_MULTIBLOCKS.add(formedMultiblock);
    }

    public static void removeFormedMultiblock(FormedMultiblock formedMultiblock) {
        FORMED_MULTIBLOCKS.remove(formedMultiblock);
    }

    public static void clear() {
        FORMED_MULTIBLOCKS.clear();
    }
}
