package me.paindar;

import me.paindar.data.ExternalData;
import me.paindar.data.ExternalDataProvider;
import me.paindar.data.ExternalDataStorage;
import me.paindar.data.IExternalData;
import me.paindar.event.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        CapabilityManager.INSTANCE.register(IExternalData.class, new ExternalDataStorage(), ExternalData::new);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

}
