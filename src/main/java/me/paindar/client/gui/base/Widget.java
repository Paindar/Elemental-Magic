package me.paindar.client.gui.base;

import static me.paindar.ElementalMagic.logger;

import java.util.ArrayList;
import java.util.List;

public class Widget
{
    private String name = "";
    public int x,y;
    public int width, height;
    List<Component> components = new ArrayList<>();
    List<Widget> widgets = new ArrayList<>();

    public Widget() { }
    public Widget(String name){this.name = name;}

    public void setXY(int x, int y){this.x=x;this.y=y;}

    public void setWidthAndHeight(int width, int height){this.width = width;this.height=height;}

    public void addComponent(Component com)
    {
        for(Component c:components)
        {
            if(c.getName().equals(com.getName()))
            {
                logger.info("Cannot add component with the same name!");
                return;
            }
        }
        components.add(com);
    }

    public void addWidget(Widget wid)
    {
        widgets.add(wid);
    }

    public String getName(){return name;}

    public void draw(float partialTicks, int dx, int dy, int mouseX, int mouseY)
    {
        for(Component c:components)
        {
            c.draw(partialTicks, dx+ x,dy+y);
        }
    }
}
