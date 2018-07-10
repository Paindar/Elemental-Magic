package me.paindar.data;

import net.minecraft.nbt.NBTTagCompound;

public interface IExternalData
{
    public <T> T getData(String adj, T defValue);
    public <T> void setData(String adj, T value);
    public void writeToNBT(NBTTagCompound base);
    public void readFromNBT(NBTTagCompound base);

    void copy(IExternalData oldData);
}
