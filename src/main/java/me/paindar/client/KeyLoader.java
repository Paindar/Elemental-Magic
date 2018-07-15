package me.paindar.client;

import me.paindar.ElementalMagic;
import me.paindar.client.gui.GuiLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import static me.paindar.ElementalMagic.logger;

public class KeyLoader
{
    public static KeyBinding showElementGUI;

    public KeyLoader()
    {
        KeyLoader.showElementGUI = new KeyBinding("key.elementalmagic.showElementGUI",
                Keyboard.KEY_H, "key.categories.elementalmagic");
        ClientRegistry.registerKeyBinding(showElementGUI);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyLoader.showElementGUI.isPressed())
        {
            logger.info("rua");

            EntityPlayer player = Minecraft.getMinecraft().player;
            player.openGui(ElementalMagic.instance, GuiLoader.GUI_ELEMENT_TREE, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }
    }
}
