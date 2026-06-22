package com.SprintXXL.primitivemultiblocks.validation;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class ValidationError {

    private final BlockPos localPos;
    private final BlockPos worldPos;
    private final ResourceLocation expected;
    private final ResourceLocation actual;

    public ValidationError(
            BlockPos localPos,
            BlockPos worldPos,
            ResourceLocation expected,
            ResourceLocation actual
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

    public ResourceLocation getExpected() {
        return expected;
    }

    public ResourceLocation getActual() {
        return actual;
    }
}
