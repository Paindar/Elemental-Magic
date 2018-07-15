package me.paindar.client.gui;

import me.paindar.ElementalMagic;
import me.paindar.client.gui.base.GuiBase;
import me.paindar.client.gui.base.TextureComponent;
import me.paindar.client.gui.base.Widget;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class ContainerElementTree extends GuiContainer
{
    private static final String TEXTURE_PATH = ElementalMagic.MODID + ":" + "textures/gui/container/gui_demo.jpg";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    private static GuiBase gui;
    static{
        gui = new GuiBase();
        Widget w1 = new Widget("background");
        w1.width=300;
        w1.height=300;
        TextureComponent texture = new TextureComponent(TEXTURE,400,300);
        w1.addComponent(texture);
        gui.addWidget(w1);
    }

    public ContainerElementTree(ContainerElementTreeBase tree)
    {
        super(tree);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        int offsetX = (this.width - 400) / 2, offsetY = (this.height - 300) / 2;
        //this.mc.getTextureManager().bindTexture(TEXTURE);
        //this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        gui.draw(partialTicks, offsetX, offsetY, mouseX, mouseY);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        // TODO
    }
}
