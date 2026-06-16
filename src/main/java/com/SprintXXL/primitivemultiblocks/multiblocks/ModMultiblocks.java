package com.SprintXXL.primitivemultiblocks.multiblocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public final class ModMultiblocks {

    private ModMultiblocks() {}

    public static final Multiblock TEST_MULTIBLOCK =
            new Multiblock(
                    MultiblockIDs.TEST_MULTIBLOCK,
                    new Dimensions(3, 3, 3),
                    Blocks.COBBLESTONE,
                    Blocks.NETHER_BRICK,
                    new BlockPos(1, 1, 0),
                    true
            );
}
