package com.SprintXXL.primitivemultiblocks.validation;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ValidationResult {

    private final boolean valid;
    private final List<ValidationError> errors;
    private final EnumFacing facing;
    private final BlockPos origin;

    public ValidationResult(
            boolean valid,
            List<ValidationError> errors,
            EnumFacing facing,
            BlockPos origin
    ) {
        this.valid = valid;
        this.errors = errors;
        this.facing = facing;
        this.origin = origin;
    }

    public boolean isValid() {
        return valid;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    public BlockPos getOrigin() {
        return origin;
    }
}
