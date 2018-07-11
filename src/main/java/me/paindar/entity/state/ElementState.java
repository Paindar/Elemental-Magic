package me.paindar.entity.state;

import me.paindar.data.ExternalDataProvider;
import me.paindar.data.IExternalData;
import net.minecraft.entity.EntityLiving;

public class ElementState
{
    public final String MANA_PATH  = "external.mana";
    public final String AER_PATH   = "external.aer";
    public final String AQUA_PATH  = "external.aqua";
    public final String ETHER_PATH = "external.ether";
    public final String IGNIS_PATH = "external.ignis";
    public final String TERRA_PATH = "external.terra";

    private EntityLiving entity;
    private IExternalData data;
    private float maxMana = 100f;
    public ElementState(EntityLiving entity)
    {
        this.entity = entity;
        this.data =  entity.getCapability(ExternalDataProvider.EDATA_CAP, null);
    }

    public float addMana(float value)
    {
        float oldData = data.getData(MANA_PATH, 0f);
        float newData = oldData + value;
        newData = (newData > maxMana ? maxMana : newData);
        data.setData(MANA_PATH, newData);
        return newData;
    }

    public float getMana()
    {
        return data.getData(MANA_PATH,0.0f);
    }
    public void setMana(float value)
    {
        data.setData(MANA_PATH, value > maxMana ? maxMana : value);
    }

    public float addAer(float value)
    {
        float oldData = data.getData(AER_PATH, 0f);
        float newData = oldData + value;
        data.setData(AER_PATH, newData);
        return newData;
    }

    public float getAer()
    {
        return data.getData(AER_PATH, 0f);
    }

    public void setAer(float value)
    {
        data.setData(AER_PATH, value);
    }

    public float addAqua(float value)
    {
        float oldData = data.getData(AQUA_PATH, 0f);
        float newData = oldData + value;
        data.setData(AQUA_PATH, newData);
        return newData;
    }

    public float getAqua()
    {
        return data.getData(AQUA_PATH, 0f);
    }

    public void setAqua(float value)
    {
        data.setData(AQUA_PATH, value);
    }

    public float addIgnis(float value)
    {
        float oldData = data.getData(IGNIS_PATH, 0f);
        float newData = oldData + value;
        data.setData(IGNIS_PATH, newData);
        return newData;
    }

    public float getIgnis()
    {
        return data.getData(IGNIS_PATH, 0f);
    }

    public void setIgnis(float value)
    {
        data.setData(IGNIS_PATH, value);
    }

    public float addTerra(float value)
    {
        float oldData = data.getData(TERRA_PATH, 0f);
        float newData = oldData + value;
        data.setData(TERRA_PATH, newData);
        return newData;
    }

    public float getTerra()
    {
        return data.getData(TERRA_PATH, 0f);
    }

    public void setTerra(float value)
    {
        data.setData(TERRA_PATH, value);
    }

    public float addEther(float value)
    {
        float oldData = data.getData(ETHER_PATH, 0f);
        float newData = oldData + value;
        data.setData(ETHER_PATH, newData);
        return newData;
    }

    public float getEther()
    {
        return data.getData(ETHER_PATH, 0f);
    }

    public void setEther(float value)
    {
        data.setData(ETHER_PATH, value);
    }

}
