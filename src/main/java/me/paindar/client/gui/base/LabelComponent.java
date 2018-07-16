package me.paindar.client.gui.base;

import me.paindar.utils.client.Font.FontOption;
import me.paindar.utils.client.Font.TrueTypeFont;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

public class LabelComponent extends Component
{
    public String value = "";
    public int width,height;
    public int zLevel = 0;
    private TrueTypeFont font = TrueTypeFont.defaultFont;
    private FontOption option;
    public LabelComponent()
    {
        super("label");
    }
    public void setFont(int style, int size, String fontName)
    {
        font=TrueTypeFont.withFallback(style, size, fontName);
    }
    public void setFontOption(double size, FontOption.FontAlign align, Color color)
    {
        if(option==null)
        {
            option = new FontOption();
        }
        option.fontSize=size;
        option.align=align;
        option.color=color;
    }

    @Override
    public void draw(float partialTicks, int x, int y)
    {

        GL11.glPushMatrix();
        GL11.glTranslated(0, 0, zLevel);
        double px=width * option.align.lenOffset + x,
                py = Math.max(0, height - option.fontSize) + y;
        font.draw(value, px, py, option);
        GL11.glPopMatrix();
    }
}
