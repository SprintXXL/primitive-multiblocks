package com.SprintXXL.primitivemultiblocks.events;

import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblock;
import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblockManager;
import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import com.SprintXXL.primitivemultiblocks.multiblocks.MultiblockRegistry;
import com.SprintXXL.primitivemultiblocks.validation.MultiblockValidator;
import com.SprintXXL.primitivemultiblocks.validation.ValidationError;
import com.SprintXXL.primitivemultiblocks.validation.ValidationResult;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MultiblockInteractionHandler {

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {

        World world = event.getWorld();

        if (world.isRemote) {
            return;
        }

        BlockPos clickedPos = event.getPos();
        Block clickedBlock = world.getBlockState(clickedPos).getBlock();

        EntityPlayer player = event.getEntityPlayer();

        for (Multiblock multiblock : MultiblockRegistry.getAllMultiblocks()) {

            if (clickedBlock == multiblock.getControllerBlock()) {

                ValidationResult bestResult = null;

                for (EnumFacing facing : EnumFacing.HORIZONTALS) {

                    ValidationResult result = MultiblockValidator.validate(world, clickedPos, multiblock, facing);

                    if (result.isValid()) {

                        FormedMultiblock formedMultiblock =
                                new FormedMultiblock(
                                        multiblock.getID(),
                                        result.getOrigin(),
                                        result.getFacing()
                                );

                        FormedMultiblockManager.registerFormedMultiblock(formedMultiblock);

                        sendMessage(player, "Formed Count: " + FormedMultiblockManager.getAllFormedMultiblocks().size());

                        return;
                    }

                    if (bestResult == null || result.getErrors().size() < bestResult.getErrors().size()) {
                        bestResult = result;
                    }
                }

                sendMessage(player, "Invalid Multiblock: " + bestResult.getErrors().size() + " errors");

                for (int i = 0; i < bestResult.getErrors().size(); i++) {
                    sendErrorMessage(player, bestResult.getErrors().get(i), i);
                }

                return;
            }
        }
    }

    private static void sendErrorMessage(
            EntityPlayer player,
            ValidationError error,
            int index
    ) {

        int localX = error.getLocalPos().getX();
        int localY = error.getLocalPos().getY();
        int localZ = error.getLocalPos().getZ();

        int worldX = error.getWorldPos().getX();
        int worldY = error.getWorldPos().getY();
        int worldZ = error.getWorldPos().getZ();

        sendMessage(player, "");
        sendMessage(player, "[Error " + (index + 1) + "]");
        sendMessage(player, "LocalPos: " + localX + ", " + localY + ", " + localZ);
        sendMessage(player, "WorldPos: " + worldX + ", " + worldY + ", " + worldZ);
        sendMessage(player, "Expected: " + error.getExpected().getRegistryName());
        sendMessage(player, "Found: " + error.getActual().getRegistryName());

    }

    private static void sendMessage(
            EntityPlayer player,
            String message
    ) {
        player.sendMessage(new TextComponentString(message));
    }
}
