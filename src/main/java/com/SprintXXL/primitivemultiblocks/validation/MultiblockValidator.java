package com.SprintXXL.primitivemultiblocks.validation;

import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblockManager;
import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;
import com.SprintXXL.primitivemultiblocks.shared.MultiblockHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.SprintXXL.primitivemultiblocks.shared.MultiblockHelper.rotateLocalPos;

public class MultiblockValidator {

    public static ValidationResult validate(
            World world,
            BlockPos controllerWorldPos,
            Multiblock multiblock,
            EnumFacing facing
    ) {

        List<ValidationError> errors = new ArrayList<>();
        Set<BlockPos> occupiedPositions = new HashSet<>();

        Dimensions dimensions = multiblock.getDimensions();

        BlockPos origin = MultiblockHelper.getOrigin(controllerWorldPos, multiblock, facing);

        for (int x = 0; x < dimensions.getWidth(); x++) {
            for (int y = 0; y < dimensions.getHeight(); y++) {
                for (int z = 0; z < dimensions.getDepth(); z++) {

                    BlockPos localPos = new BlockPos(x,y,z);

                    BlockPos rotatedLocalPos = rotateLocalPos(localPos, facing, dimensions);

                    BlockPos worldPos = origin.add(rotatedLocalPos);

                    occupiedPositions.add(worldPos);

                    if (!multiblock.allowsWallSharing() && FormedMultiblockManager.getFormedMultiblock(worldPos) != null) {

                        return new ValidationResult(
                                false,
                                errors,
                                "Position already belongs to another formed multiblock",
                                origin,
                                facing,
                                occupiedPositions
                        );
                    }

                    Block expected;

                    if (localPos.equals(multiblock.getControllerOffset())) {
                        expected = multiblock.getControllerBlock();
                    }

                    else if (isOuterFrame(x, y, z, dimensions)) {
                        expected = multiblock.getFrameBlock();
                    }

                    else {
                        expected = Blocks.AIR;
                    }

                    Block actual = world.getBlockState(worldPos).getBlock();

                    if (actual != expected) {
                        errors.add(new ValidationError(
                                localPos,
                                worldPos,
                                expected,
                                actual
                        ));
                    }
                }
            }
        }

        if (!errors.isEmpty()) {
            return new ValidationResult(false, errors, "Structure does not match definition", origin, facing, occupiedPositions);
        }

        return new ValidationResult(true, errors, "", origin, facing, occupiedPositions);
    }

    private static boolean isOuterFrame(
            int x,
            int y,
            int z,
            Dimensions dimensions
    ) {
        return x == 0 || x == dimensions.getWidth() - 1 ||
                y == 0 || y == dimensions.getHeight() - 1 ||
                z == 0 || z == dimensions.getDepth() - 1;
    }
}
