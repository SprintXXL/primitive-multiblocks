package com.SprintXXL.primitivemultiblocks.events;

import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblock;
import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblockManager;
import com.SprintXXL.primitivemultiblocks.formation.world.MultiblockWorldData;
import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import com.SprintXXL.primitivemultiblocks.multiblocks.registry.MultiblockRegistry;
import com.SprintXXL.primitivemultiblocks.validation.MultiblockValidator;
import com.SprintXXL.primitivemultiblocks.validation.ValidationError;
import com.SprintXXL.primitivemultiblocks.validation.ValidationResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.SprintXXL.primitivemultiblocks.API.MultiblockAPI.isFormationHammer;

public class MultiblockInteractionHandler {

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        World world = event.getWorld();
        EntityPlayer player = event.getEntityPlayer();

        if (world.isRemote) {
            return;
        }

        if (event.getHand() != EnumHand.MAIN_HAND) {
            return;
        }

        if (!isFormationHammer(player.getHeldItemMainhand())) {
            return;
        }

        BlockPos clickedPos = event.getPos();
        ResourceLocation clickedBlock = world.getBlockState(clickedPos).getBlock().getRegistryName();

        for (Multiblock multiblock : MultiblockRegistry.getAllMultiblocks()) {

            if (clickedBlock.equals(multiblock.getControllerBlock())) {

                ValidationResult bestResult = null;

                for (EnumFacing facing : EnumFacing.HORIZONTALS) {

                    ValidationResult result = MultiblockValidator.validate(world, clickedPos, multiblock, facing);

                    if (result.isValid()) {

                        FormedMultiblock formedMultiblock =
                                new FormedMultiblock(
                                        multiblock.getID(),
                                        result.getOrigin(),
                                        result.getFacing(),
                                        result.getOccupiedPositions()
                                );

                        FormedMultiblockManager.registerFormedMultiblock(formedMultiblock);

                        MultiblockWorldData.get(world).markDirty();

                        sendMessage(player, "World data loaded/world");

                        sendMessage(player, "Formed Count: " + FormedMultiblockManager.getAllFormedMultiblocks().size());

                        return;
                    }

                    if (bestResult == null || result.getErrors().size() < bestResult.getErrors().size()) {
                        bestResult = result;
                    }
                }

                if (bestResult.getErrors().isEmpty()) {

                    sendMessage(player, bestResult.getFailureReason());
                } else {

                    sendMessage(player, "Invalid Multiblock: " + bestResult.getErrors().size() + " errors");

                    for (int i = 0; i < bestResult.getErrors().size(); i++) {
                        sendErrorMessage(player, bestResult.getErrors().get(i), i);
                    }
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
        sendMessage(player, "Expected: " + error.getExpected());
        sendMessage(player, "Found: " + error.getActual());

    }

    private static void sendMessage(
            EntityPlayer player,
            String message
    ) {
        player.sendMessage(new TextComponentString(message));
    }
}
