package me.paindar.data;

import me.paindar.ElementalMagic;
import me.paindar.utils.s11n.S11nConverter;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExternalData implements IExternalData
{
    public static final String KEY = "externalData";
    Map<String, Object> attrs = new LinkedHashMap<>();
    Map<String, Class> attrsType = new HashMap<>();
    @Override
    public <T> T getData(String adj, T defValue)
    {
        T obj = defValue;
        try
        {
            if(attrs.containsKey(adj))
                obj = (T)attrs.get(adj);
        }
        catch (Exception e)
        {
            ElementalMagic.logger.warn(e);
        }
        return obj;
    }

    @Override
    public <T> void setData(String adj, T value)
    {
        attrs.put(adj, value);
        attrsType.put(adj, value.getClass());
    }

    @Override
    public void writeToNBT(NBTTagCompound base)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        StringBuilder sb = new StringBuilder();
        for(String str : attrs.keySet())
        {
            sb.append(str).append(' ');
            NBTTagCompound subNBT = new NBTTagCompound();
            S11nConverter.write(subNBT, attrs.get(str));
            subNBT.setString(str+".type", attrsType.get(str).getName());
            nbt.setTag(str, subNBT);
        }
        nbt.setString(".root", sb.toString());
        base.setTag(KEY, nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound base)
    {
        NBTTagCompound nbt = base.getCompoundTag(KEY);
        String root = nbt.getString(".root");
        if(!root.isEmpty())
        {
            String[] fields = root.split(" ");
            for(String str:fields)
            {
                NBTTagCompound subNBT = nbt.getCompoundTag(str);
                String clsName = subNBT.getString(str+".type");
                try
                {
                    Class<?> klass = Class.forName(clsName);
                    Object obj = S11nConverter.read(subNBT, klass);
                    attrs.put(str, obj);
                    attrsType.put(str, klass);
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void copy(IExternalData oldData)
    {
        if(oldData instanceof ExternalData)
        {
            ExternalData oed = ((ExternalData) oldData);
            for(String key : oed.attrs.keySet())
            {
                attrs.put(key, oed.attrs.get(key));
                attrsType.put(key, oed.attrsType.get(key));
            }
        }
    }
}
