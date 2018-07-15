package me.paindar.client.gui.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class TextureComponent extends Component
{
    ResourceLocation texture;
    int width,height;
    double u,v;
    float zLevel =0;
    public TextureComponent()
    {
        this(null, 0 ,0);
    }
    public TextureComponent(ResourceLocation texture)
    {
        this(texture, 0, 0);
    }
    public TextureComponent(int width, int height)
    {
        this(null, width, height);
    }
    public TextureComponent(ResourceLocation texture, int width, int height)
    {
        super("textures");
        this.texture=texture;
        this.width=width;
        this.height=height;
    }
    @Override
    public void draw(float partialTicks, int x, int y)
    {
        if(texture != null && !texture.getResourcePath().equals("<null>")) {
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            Tessellator t = Tessellator.getInstance();
            BufferBuilder bb = t.getBuffer();

            bb.begin(7, DefaultVertexFormats.POSITION_TEX);
            bb.pos((x), (y + height), zLevel)
                    .tex(0, 1)
                    .endVertex();
            bb.pos((x + width), (y + height), zLevel)
                    .tex(1, 1)
                    .endVertex();
            bb.pos((x + width), (y), zLevel)
                    .tex(1, 0)
                    .endVertex();
            bb.pos(x, y, zLevel)
                    .tex(0, 0)
                    .endVertex();
            t.draw();
        }
    }
}
