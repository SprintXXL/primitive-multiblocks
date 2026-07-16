package com.SprintXXL.primitivemultiblocks;

import com.SprintXXL.primitivemultiblocks.events.MultiblockBreakHandler;
import com.SprintXXL.primitivemultiblocks.events.MultiblockInteractionHandler;
import com.SprintXXL.primitivemultiblocks.events.WorldLoadHandler;
import com.SprintXXL.primitivemultiblocks.multiblocks.MultiblockRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.SprintXXL.primitivemultiblocks.Reference.*;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class PrimitiveMultiblocks {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        MultiblockRegistry.init();

        MinecraftForge.EVENT_BUS.register(new WorldLoadHandler());
        MinecraftForge.EVENT_BUS.register(new MultiblockBreakHandler());
        MinecraftForge.EVENT_BUS.register(new MultiblockInteractionHandler());
    }
}
