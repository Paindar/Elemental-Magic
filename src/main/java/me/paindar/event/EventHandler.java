package me.paindar.event;

import me.paindar.ElementalMagic;
import me.paindar.data.ExternalDataProvider;
import me.paindar.data.IExternalData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import static me.paindar.ElementalMagic.logger;

public class EventHandler
{
    public static final ResourceLocation ED_CAP = new ResourceLocation(ElementalMagic.MODID, "external_data");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;

        event.addCapability(ED_CAP, new ExternalDataProvider());
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        EntityPlayer player = event.getEntityPlayer();
        IExternalData mana = player.getCapability(ExternalDataProvider.EDATA_CAP, null);
        IExternalData oldMana = event.getOriginal().getCapability(ExternalDataProvider.EDATA_CAP, null);

        mana.copy(oldMana);
    }

    @SubscribeEvent
    public void onPlayerFalldown(LivingFallEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            IExternalData ed = player.getCapability(ExternalDataProvider.EDATA_CAP, null);
            if(ed == null)
            {
                logger.info("ED is missing!");
                return ;
            }
            double dist = ed.getData("test.falldist", 0.0);
            dist += event.getDistance();
            ElementalMagic.logger.info("current distance is " + dist);
            ed.setData("test.falldist", dist);
        }
    }
}
