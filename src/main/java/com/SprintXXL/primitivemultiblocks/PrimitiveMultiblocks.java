package com.SprintXXL.primitivemultiblocks;

import com.SprintXXL.primitivemultiblocks.events.MultiblockBreakHandler;
import com.SprintXXL.primitivemultiblocks.events.MultiblockInteractionHandler;
import com.SprintXXL.primitivemultiblocks.multiblocks.MultiblockRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.SprintXXL.primitivemultiblocks.Reference.*;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class PrimitiveMultiblocks {

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new MultiblockBreakHandler());
        MinecraftForge.EVENT_BUS.register(new MultiblockInteractionHandler());

        MultiblockRegistry.init();
    }
}
