package com.SprintXXL.primitivemultiblocks.formation.world;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class SavedFormedMultiblock {

    private final String id;
    private final BlockPos origin;
    private final EnumFacing facing;

    public SavedFormedMultiblock(
            String id,
            BlockPos origin,
            EnumFacing facing
    ) {
        this.id = id;
        this.origin = origin;
        this.facing = facing;
    }

    public String getID() {
        return id;
    }

    public BlockPos getOrigin() {
        return origin;
    }

    public EnumFacing getFacing() {
        return facing;
    }
}
