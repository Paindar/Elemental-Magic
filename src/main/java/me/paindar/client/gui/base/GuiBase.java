package me.paindar.client.gui.base;

import java.util.ArrayList;
import java.util.List;

import static me.paindar.ElementalMagic.logger;

public class GuiBase
{
    List<Widget> widgets = new ArrayList<>();

    public void addWidget(Widget wid)
    {
        widgets.add(wid);
    }

    public void draw(float partialTicks, int x, int y, int mouseX, int mouseY)
    {
        for(Widget w:widgets)
        {
            w.draw(partialTicks, x, y, mouseX, mouseY);
        }
    }
}
