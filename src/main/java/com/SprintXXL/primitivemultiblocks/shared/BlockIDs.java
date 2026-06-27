package com.SprintXXL.primitivemultiblocks.shared;

import net.minecraft.util.ResourceLocation;

public final class BlockIDs {

    private static final String ID = "primitiveindustry";

    private BlockIDs() {}

    // TEST BLOCKS \\
    public static final ResourceLocation TEST_FRAMEBLOCK =
            new ResourceLocation("minecraft", "cobblestone");

    public static final ResourceLocation TEST_CONTROLLER =
            new ResourceLocation(ID, "test_factory_controller");

    // BLOCKS \\
    public static final ResourceLocation COKE_OVEN_BRICK =
            new ResourceLocation(ID, "coke_oven_brick");

    public static final ResourceLocation COKE_OVEN_CONTROLLER =
            new ResourceLocation(ID, "coke_oven_controller");
}
