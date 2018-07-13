package me.paindar;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ElementalMagic.MODID, name = ElementalMagic.NAME, version = ElementalMagic.VERSION)
public class ElementalMagic
{
    public static final String MODID = "elementalmagic";
    public static final String NAME = "Elemental Magic";
    public static final String VERSION = "1.0";

    @Mod.Instance(ElementalMagic.MODID)
    public static ElementalMagic instance;

    public static Logger logger;
    @SidedProxy(serverSide = "me.paindar.CommonProxy", clientSide = "me.paindar.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
}
