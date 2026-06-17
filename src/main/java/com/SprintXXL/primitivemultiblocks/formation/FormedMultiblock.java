package com.SprintXXL.primitivemultiblocks.formation;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class FormedMultiblock {

    private final String id;
    private final BlockPos origin;
    private final EnumFacing facing;

    public FormedMultiblock(
            String id,
            BlockPos origin,
            EnumFacing facing
    ) {
        this.id = id;
        this.origin = origin;
        this.facing = facing;
    }

    public String getFormedID() {
        return id;
    }

    public BlockPos getOrigin() {
        return origin;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FormedMultiblock)) {
            return false;
        }

        FormedMultiblock other = (FormedMultiblock) obj;

        return id.equals(other.id) && origin.equals(other.origin) && facing.equals(other.facing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                origin,
                facing
        );
    }
}
