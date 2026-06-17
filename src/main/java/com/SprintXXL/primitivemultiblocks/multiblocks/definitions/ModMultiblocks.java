package com.SprintXXL.primitivemultiblocks.multiblocks.definitions;

import com.SprintXXL.primitivemultiblocks.multiblocks.presets.CubePreset;
import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;
import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import static com.SprintXXL.primitivemultiblocks.multiblocks.MultiblockRegistry.register;

public final class ModMultiblocks {

    private ModMultiblocks() {}

    public static final Multiblock TEST_MULTIBLOCK =
            new Multiblock(
                    MultiblockIDs.TEST_MULTIBLOCK,
                    new Dimensions(3, 3, 3),
                    Blocks.COBBLESTONE,
                    Blocks.NETHER_BRICK,
                    new BlockPos(1, 1, 0),
                    new CubePreset(
                            true,
                            new Dimensions(1, 1, 1)
                    )
            );

    public static void initModMultiblocks() {

        register(TEST_MULTIBLOCK);
    }
}
