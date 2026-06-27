package com.SprintXXL.primitivemultiblocks.multiblocks.definitions;

import com.SprintXXL.primitivemultiblocks.multiblocks.presets.CubePreset;
import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;
import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;

import static com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock.offset;
import static com.SprintXXL.primitivemultiblocks.multiblocks.MultiblockRegistry.register;
import static com.SprintXXL.primitivemultiblocks.shared.BlockIDs.*;
import static com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions.dims;

public final class ModMultiblocks {

    private ModMultiblocks() {}

    public static void initModMultiblocks() {

        // TEST \\
        register(TEST_FACTORY);

        // MULTIBLOCKS \\
        register(COKE_OVEN);
    }

    // TEST \\
    public static final Multiblock TEST_FACTORY =
            new Multiblock(
                    MultiblockIDs.TEST_FACTORY,
                    new Dimensions(7, 7, 7),
                    TEST_FRAMEBLOCK,
                    TEST_CONTROLLER,
                    offset(3, 1, 0),
                    true,
                    new CubePreset(true, dims(5, 5, 5))
            );

    // MULTIBLOCKS \\
    public static final Multiblock COKE_OVEN =
            new Multiblock(
                    MultiblockIDs.COKE_OVEN,
                    new Dimensions(3, 3, 3),
                    COKE_OVEN_BRICK,
                    COKE_OVEN_CONTROLLER,
                    offset(1, 1, 0),
                    true,
                    new CubePreset(true, dims(1, 1, 1))
            );
}