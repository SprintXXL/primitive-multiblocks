package com.SprintXXL.primitivemultiblocks.multiblocks;

import com.SprintXXL.primitivemultiblocks.multiblocks.presets.ShapePreset;
import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class Multiblock {

    private final String id;
    private final Dimensions dimensions;
    private final Block frameBlock;
    private final Block controllerBlock;
    private final BlockPos controllerOffset;
    private final ShapePreset preset;

    public Multiblock(
            String id,
            Dimensions dimensions,
            Block frameBlock,
            Block controllerBlock,
            BlockPos controllerOffset,
            ShapePreset preset
    ) {
        this.id = id;
        this.dimensions = dimensions;
        this.frameBlock = frameBlock;
        this.controllerBlock = controllerBlock;
        this.controllerOffset = controllerOffset;
        this.preset = preset;
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

    public ShapePreset getPreset() {
        return preset;
    }
}
