package me.paindar.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExternalDataProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IExternalData.class)
    public static final Capability<IExternalData> EDATA_CAP = null;

    private IExternalData instance = EDATA_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing)
    {
        return capability == EDATA_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing)
    {
        return capability == EDATA_CAP ? EDATA_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return EDATA_CAP.getStorage().writeNBT(EDATA_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase base)
    {
        EDATA_CAP.getStorage().readNBT(EDATA_CAP, this.instance, null, base);
    }
}
