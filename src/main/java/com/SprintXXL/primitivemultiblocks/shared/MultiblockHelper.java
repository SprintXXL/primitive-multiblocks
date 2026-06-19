package com.SprintXXL.primitivemultiblocks.shared;

import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import com.SprintXXL.primitivemultiblocks.multiblocks.shared.Dimensions;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

public final class MultiblockHelper {

    private MultiblockHelper() {}

    public static BlockPos getOrigin(
            BlockPos controllerWorldPos,
            Multiblock multiblock,
            EnumFacing facing
    ) {
        Dimensions dimensions = multiblock.getDimensions();

        BlockPos rotatedControllerOffset =
                rotateLocalPos(
                        multiblock.getControllerOffset(),
                        facing,
                        dimensions
                );

        return controllerWorldPos.subtract(rotatedControllerOffset);
    }

    public static Set<BlockPos> getOccupiedPositions(
            BlockPos origin,
            Multiblock multiblock,
            EnumFacing facing
    ) {
        Set<BlockPos> occupiedPositions = new HashSet<>();

        Dimensions dimensions = multiblock.getDimensions();

        for (int x = 0; x < dimensions.getWidth(); x++) {
            for (int y = 0; y < dimensions.getHeight(); y++) {
                for (int z = 0; z < dimensions.getDepth(); z++) {

                    BlockPos localPos = new BlockPos(x, y, z);

                    BlockPos rotatedLocalPos = rotateLocalPos(localPos, facing, dimensions);

                    occupiedPositions.add(origin.add(rotatedLocalPos));

                }
            }
        }

        return occupiedPositions;
    }

    public static BlockPos rotateLocalPos(
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
