package me.paindar.client.gui.base;

public abstract class Component
{
    private String name = "";
    public int x,y;
    public Component(){}
    protected Component(String name){this.name = name;}
    public String getName(){return name;}

    public abstract void draw(float partialTicks, int x, int y);
}
