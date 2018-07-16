package me.paindar.utils.client.Font;


import org.lwjgl.util.Color;

public class FontOption {
    public enum FontAlign {
        LEFT(0), CENTER(0.5), RIGHT(1);

        public final double lenOffset;

        FontAlign(double _lenOffset) {
            lenOffset = _lenOffset;
        }
    }

    public double fontSize;
    public FontAlign align;
    public Color color;

    public FontOption() {
        this(10);
    }

    public FontOption(double _fontsz) {
        this(_fontsz, FontAlign.LEFT);
    }

    public FontOption(double _fontsz, Color _color) {
        this(_fontsz, FontAlign.LEFT, _color);
    }

    public FontOption(double _fontsz, int hex) {
        this(_fontsz, new Color(hex&0xff000000,0xff0000,0xff00,0xff));
    }

    public FontOption(double _fontsz, FontAlign _align) {
        this(_fontsz, _align, new Color(255,255,255,0));
    }

    public FontOption(double _fontsz, FontAlign _align, Color _color) {
        fontSize = _fontsz;
        align = _align;
        color = _color;
    }

    public FontOption(double _fontsz, FontAlign _align, int hex) {
        this(_fontsz, _align, new Color(hex&0xff000000,0xff0000,0xff00,0xff));
    }

    @Override
    public FontOption clone() {
        FontOption ret = new FontOption();
        ret.fontSize = fontSize;
        ret.align = align;
        return ret;
    }

}