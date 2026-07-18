package com.SprintXXL.primitivemultiblocks.formation.world;

import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblock;
import com.SprintXXL.primitivemultiblocks.formation.FormedMultiblockManager;
import com.SprintXXL.primitivemultiblocks.multiblocks.Multiblock;
import com.SprintXXL.primitivemultiblocks.multiblocks.registry.MultiblockRegistry;
import com.SprintXXL.primitivemultiblocks.shared.MultiblockHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MultiblockWorldData extends WorldSavedData {

    private static final String DATA_NAME = "primitive_multiblocks";
    private final List<SavedFormedMultiblock> savedMultiblocks = new ArrayList<>();

    public MultiblockWorldData() {
        super(DATA_NAME);
    }

    public MultiblockWorldData(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        savedMultiblocks.clear();

        System.out.println("readFromNBT called");

        NBTTagList list = nbt.getTagList("formed_multiblocks", 10);

        System.out.println("NBT list size: " + list.tagCount());

        for (int i = 0; i < list.tagCount(); i++) {

            NBTTagCompound tag = list.getCompoundTagAt(i);

            int x = tag.getInteger("x");
            int y = tag.getInteger("y");
            int z = tag.getInteger("z");

            String id = tag.getString("id");

            BlockPos origin = new BlockPos(x, y, z);

            EnumFacing facing = EnumFacing.byName(tag.getString("facing"));

            savedMultiblocks.add(new SavedFormedMultiblock(
                    id,
                    origin,
                    facing
            ));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        NBTTagList list = new NBTTagList();

        for (FormedMultiblock formed : FormedMultiblockManager.getAllFormedMultiblocks()) {

            NBTTagCompound tag = new NBTTagCompound();

            tag.setString("id", formed.getFormedID());

            tag.setInteger("x", formed.getOrigin().getX());
            tag.setInteger("y", formed.getOrigin().getY());
            tag.setInteger("z", formed.getOrigin().getZ());

            tag.setString("facing", formed.getFacing().getName());

            list.appendTag(tag);
        }

        compound.setTag("formed_multiblocks", list);

        return compound;
    }

    public static MultiblockWorldData get(World world) {

        MultiblockWorldData data = (MultiblockWorldData) world.getPerWorldStorage()
                .getOrLoadData(MultiblockWorldData.class, DATA_NAME);

        if (data == null) {

            data = new MultiblockWorldData();

            world.getPerWorldStorage().setData(DATA_NAME, data);
        }

        return data;
    }

    public void restore(World world) {

        for (SavedFormedMultiblock saved : savedMultiblocks) {

            Multiblock multiblock = MultiblockRegistry.getMultiblock(saved.getID());

            if (multiblock == null || saved.getFacing() == null) {
                continue;
            }

            Set<BlockPos> occupiedPositions =
                    MultiblockHelper.getOccupiedPositions(saved.getOrigin(), multiblock, saved.getFacing());

            FormedMultiblock formed =
                    new FormedMultiblock(
                            saved.getID(),
                            saved.getOrigin(),
                            saved.getFacing(),
                            occupiedPositions
                    );

            FormedMultiblockManager.registerFormedMultiblock(formed);
        }
    }
}
