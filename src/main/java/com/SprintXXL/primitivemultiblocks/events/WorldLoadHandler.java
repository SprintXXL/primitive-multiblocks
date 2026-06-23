package com.SprintXXL.primitivemultiblocks.events;

import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblockManager;
import com.SprintXXL.primitivemultiblocks.formation.world.MultiblockWorldData;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldLoadHandler {

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {

        World world = event.getWorld();

        if (world.provider.getDimension() != 0) {
            return;
        }

        if (world.isRemote) {
            return;
        }

        FormedMultiblockManager.clear();
        MultiblockWorldData.get(world).restore(world);
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {

        World world = event.getWorld();

        if (world.provider.getDimension() != 0) {
            return;
        }

        if (world.isRemote) {
            return;
        }

        FormedMultiblockManager.clear();
    }
}
