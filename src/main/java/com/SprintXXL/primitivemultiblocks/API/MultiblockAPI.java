package com.SprintXXL.primitivemultiblocks.API;

import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import com.SprintXXL.primitivemultiblocks.multiblocks.MultiblockRegistry;
import com.SprintXXL.primitivemultiblocks.validation.MultiblockValidator;
import com.SprintXXL.primitivemultiblocks.validation.ValidationResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class MultiblockAPI {

    private MultiblockAPI() {}

    public static ValidationResult validate(
            World world,
            BlockPos controllerPos,
            String multiblockID,
            EnumFacing facing
    ) {
        Multiblock multiblock = MultiblockRegistry.getMultiblock(multiblockID);

        if (multiblock == null) {
            throw new IllegalArgumentException("Unknown multiblock: " + multiblockID);
        }

        return MultiblockValidator.validate(
                world,
                controllerPos,
                multiblock,
                facing
        );
    }

    public static boolean isFormed(
            World world,
            BlockPos controllerPos,
            String multiblockID,
            EnumFacing facing
    ) {
        return validate(world, controllerPos, multiblockID, facing).isValid();
    }
}
