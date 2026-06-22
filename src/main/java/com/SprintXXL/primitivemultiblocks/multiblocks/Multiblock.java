package com.SprintXXL.primitivemultiblocks.multiblocks;

import com.SprintXXL.primitivemultiblocks.multiblocks.presets.ShapePreset;
import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class Multiblock {

    private final String id;
    private final Dimensions dimensions;
    private final ResourceLocation frameBlock;
    private final ResourceLocation controllerBlock;
    private final BlockPos controllerOffset;
    private final boolean allowsWallSharing;
    private final ShapePreset preset;

    public Multiblock(
            String id,
            Dimensions dimensions,
            ResourceLocation frameBlock,
            ResourceLocation controllerBlock,
            BlockPos controllerOffset,
            boolean allowsWallSharing,
            ShapePreset preset
    ) {
        this.id = id;
        this.dimensions = dimensions;
        this.frameBlock = frameBlock;
        this.controllerBlock = controllerBlock;
        this.controllerOffset = controllerOffset;
        this.allowsWallSharing = allowsWallSharing;
        this.preset = preset;
    }

    public String getID() {
        return id;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public ResourceLocation getFrameBlock() {
        return frameBlock;
    }

    public ResourceLocation getControllerBlock() {
        return controllerBlock;
    }

    public BlockPos getControllerOffset() {
        return controllerOffset;
    }

    public boolean allowsWallSharing() {
        return allowsWallSharing;
    }

    public ShapePreset getPreset() {
        return preset;
    }

    public static BlockPos offset(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }
}
