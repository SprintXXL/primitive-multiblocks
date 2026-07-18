package com.SprintXXL.primitivemultiblocks.ascent;

import com.SprintXXL.primitivemultiblocks.multiblocks.definitions.ModMultiblocks;
import com.sprintxxl.ascenthub.definitions.AscentDefinition;
import com.sprintxxl.ascenthub.definitions.AscentDefinitionProvider;
import com.sprintxxl.ascenthub.definitions.DefinitionRegistrar;

import static com.SprintXXL.primitivemultiblocks.Reference.MODID;
import static com.sprintxxl.ascenthub.definitions.registry.DefinitionProviderRegistry.registerProvider;

public final class MultiblocksDefinitionProvider implements AscentDefinitionProvider {

    private MultiblocksDefinitionProvider() {}

    public static void initMultiblocksDefinitionProvider() {

        registerProvider(MODID, new MultiblocksDefinitionProvider());
    }

    public void registerDefinitions(DefinitionRegistrar<AscentDefinition> registrar) {

        ModMultiblocks.initMultiblockDefinitions(registrar::register);
    }
}
