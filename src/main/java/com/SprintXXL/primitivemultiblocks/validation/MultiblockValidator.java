package com.SprintXXL.primitivemultiblocks.validation;

import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MultiblockValidator {

    public static ValidationResult validate(
            World world,
            BlockPos controllerWorldPos,
            Multiblock multiblock,
            EnumFacing facing
    ) {

        List<ValidationError> errors = new ArrayList<>();

        Dimensions dimensions = multiblock.getDimensions();

        BlockPos rotatedControllerOffset = rotateLocalPos(multiblock.getControllerOffset(), facing, dimensions);

        BlockPos origin = controllerWorldPos.subtract(rotatedControllerOffset);

        for (int x = 0; x < dimensions.getWidth(); x++) {
            for (int y = 0; y < dimensions.getHeight(); y++) {
                for (int z = 0; z < dimensions.getDepth(); z++) {

                    BlockPos localPos = new BlockPos(x,y,z);

                    BlockPos rotatedLocalPos = rotateLocalPos(localPos, facing, dimensions);
                    BlockPos worldPos = origin.add(rotatedLocalPos);

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
            return new ValidationResult(false, errors, facing, origin);
        }

        return new ValidationResult(true, errors, facing, origin);
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

    private static BlockPos rotateLocalPos(
            BlockPos localPos,
            EnumFacing facing,
            Dimensions dimensions
    ) {

        int x = localPos.getX();
        int y = localPos.getY();
        int z = localPos.getZ();

        int maxX = dimensions.getWidth() - 1;

        switch (facing) {

            case NORTH:
                return new BlockPos(x, y, -z);
            case SOUTH:
                return new BlockPos(maxX - x, y, z);
            case EAST:
                return new BlockPos(z, y, x);
            case WEST:
                return new BlockPos(-z, y, maxX - x);

            default:
                return new BlockPos(x, y, -z);
        }
    }
}
