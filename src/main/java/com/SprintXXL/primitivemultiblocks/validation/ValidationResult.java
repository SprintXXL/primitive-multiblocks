package com.SprintXXL.primitivemultiblocks.validation;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ValidationResult {

    private final boolean valid;
    private final List<ValidationError> errors;
    private final String failureReason;
    private final BlockPos origin;
    private final EnumFacing facing;
    private final Set<BlockPos> occupiedPositions;

    public ValidationResult(
            boolean valid,
            List<ValidationError> errors,
            String failureReason,
            BlockPos origin,
            EnumFacing facing,
            Set<BlockPos> occupiedPositions
    ) {
        this.valid = valid;
        this.errors = errors;
        this.failureReason = failureReason;
        this.origin = origin;
        this.facing = facing;
        this.occupiedPositions = occupiedPositions;
    }

    public boolean isValid() {
        return valid;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public BlockPos getOrigin() {
        return origin;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    public Set<BlockPos> getOccupiedPositions() {
        return Collections.unmodifiableSet(occupiedPositions);
    }
}
