package me.paindar.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ExternalDataStorage implements Capability.IStorage<IExternalData>
{
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IExternalData> capability, IExternalData data, EnumFacing enumFacing)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        data.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void readNBT(Capability<IExternalData> capability, IExternalData data, EnumFacing enumFacing, NBTBase nbtBase)
    {
        if(nbtBase instanceof  NBTTagCompound)
            data.readFromNBT((NBTTagCompound) nbtBase);
    }
}
