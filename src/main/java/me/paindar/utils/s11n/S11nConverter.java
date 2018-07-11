package me.paindar.utils.s11n;

import com.google.common.base.Preconditions;
import me.paindar.ElementalMagic;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class S11nConverter
{
    interface S11nHelper<T>
    {
        void write(NBTTagCompound tag, T value);
        T   read(NBTTagCompound tag, Class<? extends T> type);
    }

    public static Map<Class, S11nHelper> s11nList = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void write(NBTTagCompound tag, Object obj)
    {
        Preconditions.checkNotNull(obj);

        final Class<?> type = obj.getClass();
        S11nHelper helper = s11nList.get(type);
        if (helper != null)
        {
            helper.write(tag, obj);
        }
        else if(type.isArray())
        {
            NBTTagCompound subTag = new NBTTagCompound();

            int length = Array.getLength(obj);
            subTag.setInteger("size", length);
            for (int i = 0; i < length; ++i)
            {
                NBTTagCompound subArrayNBT = new NBTTagCompound();
                write(subArrayNBT, Array.get(obj, i));
                subTag.setTag(String.valueOf(i), subArrayNBT);
            }
            tag.setTag(type.getName(), subTag);
        }
        else
        { // recursive;
            for (Field f : type.getFields())
            {
                try
                {
                    String fieldName = f.getName();
                    Object value = f.get(obj);
                    if (value != null)
                    {
                        NBTTagCompound subNBT = new NBTTagCompound();
                        write(subNBT, obj);
                        tag.setTag(fieldName, subNBT);
                    }

                }
                catch (IllegalAccessException | IllegalArgumentException ex)
                {
                    ElementalMagic.logger.error("Error writing field " + f + " in object " + obj);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static Object read(NBTTagCompound tag, Class<?> type)
    {
        S11nHelper helper = s11nList.get(type);
        if (helper != null)
        {
            return helper.read(tag, type);
        }
        else if(type.isArray())
        {
            Class baseType = type.getComponentType();

            NBTTagCompound subTag = new NBTTagCompound();

            int length = subTag.getInteger("size");
            Object array = Array.newInstance(baseType, length);
            for (int i = 0; i < length; ++i) {
                Array.set(array, i, read(subTag.getCompoundTag(String.valueOf(i)), baseType));
            }
            return array;
        }
        else
        { // recursive
            Object obj = instantiate(type);
            for (Field f : type.getFields())
            {
                try
                {
                    String fieldName = f.getName();
                    NBTTagCompound subTag = tag.getCompoundTag(fieldName);
                    f.set(obj, read(subTag, f.getType()));
                } catch (IllegalAccessException | RuntimeException ex) {
                    ex.printStackTrace();
                    // LambdaLib.log.error("Error reading field " + f + " in object " + obj, ex);
                }
            }
            return obj;
        }
    }

    private static <T> T instantiate(Class<T> type)  {
        try {
            Constructor<T> ctor = type.getConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    static
    {
        {
            S11nHelper<Byte> ser = new S11nHelper<Byte>(){
                @Override
                public void write(NBTTagCompound tag, Byte value)
                {
                    tag.setByte("data", value);
                }

                @Override
                public Byte read(NBTTagCompound tag, Class<? extends Byte> type)
                {
                    return tag.getByte("data");
                }
            };
            s11nList.put(byte.class, ser);
            s11nList.put(Byte.class, ser);
        }
        {
            S11nHelper<Short> ser = new S11nHelper<Short>(){
                @Override
                public void write(NBTTagCompound tag, Short value)
                {
                    tag.setShort("data", value);
                }

                @Override
                public Short read(NBTTagCompound tag, Class<? extends Short> type)
                {
                    return tag.getShort("data");
                }
            };
            s11nList.put(short.class, ser);
            s11nList.put(Short.class, ser);
        }
        {
            S11nHelper<Integer> ser = new S11nHelper<Integer>(){
                @Override
                public void write(NBTTagCompound tag, Integer value)
                {
                    tag.setInteger("data", value);
                }

                @Override
                public Integer read(NBTTagCompound tag, Class<? extends Integer> type)
                {
                    return tag.getInteger("data");
                }
            };
            s11nList.put(int.class, ser);
            s11nList.put(Integer.class, ser);
        }
        {
            S11nHelper<Float> ser = new S11nHelper<Float>(){
                @Override
                public void write(NBTTagCompound tag, Float value)
                {
                    tag.setFloat("data", value);
                }

                @Override
                public Float read(NBTTagCompound tag, Class<? extends Float> type)
                {
                    return tag.getFloat("data");
                }
            };
            s11nList.put(float.class, ser);
            s11nList.put(Float.class, ser);
        }
        {
            S11nHelper<Double> ser = new S11nHelper<Double>(){
                @Override
                public void write(NBTTagCompound tag, Double value)
                {
                    tag.setDouble("data", value);
                }

                @Override
                public Double read(NBTTagCompound tag, Class<? extends Double> type)
                {
                    return tag.getDouble("data");
                }
            };
            s11nList.put(double.class, ser);
            s11nList.put(Double.class, ser);
        }
        {
            S11nHelper<Long> ser = new S11nHelper<Long>(){
                @Override
                public void write(NBTTagCompound tag, Long value)
                {
                    tag.setLong("data", value);
                }

                @Override
                public Long read(NBTTagCompound tag, Class<? extends Long> type)
                {
                    return tag.getLong("data");
                }
            };
            s11nList.put(long.class, ser);
            s11nList.put(Long.class, ser);
        }
        {
            S11nHelper<Boolean> ser = new S11nHelper<Boolean>(){
                @Override
                public void write(NBTTagCompound tag, Boolean value)
                {
                    tag.setBoolean("data", value);
                }

                @Override
                public Boolean read(NBTTagCompound tag, Class<? extends Boolean> type)
                {
                    return tag.getBoolean("data");
                }
            };
            s11nList.put(boolean.class, ser);
            s11nList.put(Boolean.class, ser);
        }
        {
            S11nHelper<String> ser = new S11nHelper<String>(){
                @Override
                public void write(NBTTagCompound tag, String value)
                {
                    tag.setString("data", value);
                }

                @Override
                public String read(NBTTagCompound tag, Class<? extends String> type)
                {
                    return tag.getString("data");
                }
            };
            s11nList.put(String.class, ser);
        }
    }
}
