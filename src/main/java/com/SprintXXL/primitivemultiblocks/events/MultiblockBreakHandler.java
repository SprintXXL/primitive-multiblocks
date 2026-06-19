package com.SprintXXL.primitivemultiblocks.events;

import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblock;
import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblockManager;
import com.SprintXXL.primitivemultiblocks.formation.world.MultiblockWorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MultiblockBreakHandler {

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {

        World world = event.getWorld();
        EntityPlayer player = event.getPlayer();

        FormedMultiblock formed =
                FormedMultiblockManager.getFormedMultiblock(event.getPos());

        if (formed == null) {
            return;
        }

        FormedMultiblockManager.removeFormedMultiblock(formed);

        MultiblockWorldData.get(world).markDirty();

        sendMessage(player, formed.getFormedID() + " is no longer formed");
    }

    private static void sendMessage(
            EntityPlayer player,
            String message
    ) {
        player.sendMessage(new TextComponentString(message));
    }
}
