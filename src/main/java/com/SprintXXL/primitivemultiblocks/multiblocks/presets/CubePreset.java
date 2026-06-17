package com.SprintXXL.primitivemultiblocks.multiblocks.presets;

import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;

public class CubePreset implements ShapePreset {

    private final boolean isHollow;
    private final Dimensions hollowDims;

    public CubePreset(
            boolean isHollow,
            Dimensions hollowDims
    ) {
        this.isHollow = isHollow;
        this.hollowDims = hollowDims;
    }

    public boolean isHollow() {
        return isHollow;
    }

    public Dimensions getHollowDims() {
        return hollowDims;
    }
}
