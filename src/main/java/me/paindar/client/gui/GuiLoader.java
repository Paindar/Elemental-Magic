package me.paindar.client.gui;

import me.paindar.ElementalMagic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GuiLoader implements IGuiHandler
{
    public static final int GUI_ELEMENT_TREE = 1;

    public GuiLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(ElementalMagic.instance, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        switch (i)
        {
            case GUI_ELEMENT_TREE:
                return new ContainerElementTreeBase();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3)
    {
        switch (i)
        {
            case GUI_ELEMENT_TREE:
                return new ContainerElementTree(new ContainerElementTreeBase());
            default:
                return null;
        }
    }
}
