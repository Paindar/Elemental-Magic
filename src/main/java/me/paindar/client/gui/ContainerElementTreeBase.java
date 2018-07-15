package me.paindar.client.gui;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerElementTreeBase extends Container
{
    public ContainerElementTreeBase()
    {
        super();
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }
}
