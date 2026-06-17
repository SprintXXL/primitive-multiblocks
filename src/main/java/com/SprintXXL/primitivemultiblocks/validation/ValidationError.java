package com.SprintXXL.primitivemultiblocks.validation;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class ValidationError {

    private final BlockPos localPos;
    private final BlockPos worldPos;
    private final Block expected;
    private final Block actual;

    public ValidationError(
            BlockPos localPos,
            BlockPos worldPos,
            Block expected,
            Block actual
    ) {
        this.localPos = localPos;
        this.worldPos = worldPos;
        this.expected = expected;
        this.actual = actual;
    }

    public BlockPos getLocalPos() {
        return localPos;
    }

    public BlockPos getWorldPos() {
        return worldPos;
    }

    public Block getExpected() {
        return expected;
    }

    public Block getActual() {
        return actual;
    }
}
