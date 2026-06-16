package com.SprintXXL.primitivemultiblocks.multiblocks;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class Multiblock {

    private final String id;
    private final Dimensions dimensions;
    private final Block frameBlock;
    private final Block controllerBlock;
    private final BlockPos controllerOffset;
    private final boolean isHollow;

    public Multiblock(
            String id,
            Dimensions dimensions,
            Block frameBlock,
            Block controllerBlock,
            BlockPos controllerOffset,
            boolean isHollow
    ) {
        this.id = id;
        this.dimensions = dimensions;
        this.frameBlock = frameBlock;
        this.controllerBlock = controllerBlock;
        this.controllerOffset = controllerOffset;
        this.isHollow = isHollow;
    }

    public String getID() {
        return id;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Block getFrameBlock() {
        return frameBlock;
    }

    public Block getControllerBlock() {
        return controllerBlock;
    }

    public BlockPos getControllerOffset() {
        return controllerOffset;
    }

    public boolean isHollow() {
        return isHollow;
    }
}
