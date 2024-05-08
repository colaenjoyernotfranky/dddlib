package com.chestbrah.dddlib.engine.math;

import java.awt.Color;

/**
 * The Class {@code Triangle} is an implenetation of Polygons using three Objects of type {@code Vector} to form a triangle.
 * The Class also provides getter and setter methods for {@code Triangle} manipulation.
 */
public class Triangle {
    private Vector a, b, c;
    private Color fillColor;
    private Color frameColor;
    /** Empty Constuructor - returns an {@code Triangle} including three Vectors with x, y, z equal to 0 and the Color set to White. */
    public Triangle() {this.a = this.b = this.c = new Vector(); this.fillColor = null; this.frameColor = Color.WHITE;}
    /**
     * Constructor with a, b and c as parameters - returns an {@code Triangle} with the given a, b and c as set attributes and Color set to White.
     * @param a of type {@code Vector}
     * @param b of type {@code Vector}
     * @param c of type {@code Vector}
     */
    public Triangle(Vector a, Vector b, Vector c) {this.a = a; this.b = b; this.c = c; this.fillColor = null; this.frameColor = Color.WHITE;}
    /**
     * Constructor with a, b and c as parameters - returns an {@code Triangle} with the given a, b and c as set attributes.
     * @param a of type {@code Vector}
     * @param b of type {@code Vector}
     * @param c of type {@code Vector}
     * @param frameColor of type {@code Color}
     */
    public Triangle(Vector a, Vector b, Vector c, Color frameColor) {this.a = a; this.b = b; this.c = c; this.frameColor = frameColor; this.fillColor = null;}
    /**
     * Constructor with a, b and c as parameters - returns an {@code Triangle} with the given a, b and c as set attributes.
     * @param a of type {@code Vector}
     * @param b of type {@code Vector}
     * @param c of type {@code Vector}
     * @param fillColor of type {@code Color}
     * @param frameColor of type {@code Color}
     */
    public Triangle(Vector a, Vector b, Vector c, Color fillColor, Color frameColor) {this.a = a; this.b = b; this.c = c; this.fillColor = fillColor; this.frameColor = frameColor;}
    /**
     * Copy-Constructor with t as parameter. t is an existing {@code Triangle}.
     * @param t existing {@code Triangle} that should be copied.
     */
    public Triangle(Triangle t) {this.a = t.a; this.b = t.b; this.c = t.c; fillColor = t.fillColor;}
    /** Getter-Method of {@code Vector} a. */
    public Vector getA() {return a;}
    /** Getter-Method of {@code Vector} b. */
    public Vector getB() {return b;}
    /** Getter-Method of {@code Vector} c. */
    public Vector getC() {return c;}
    /** Getter-Method of {@code Color} fillColor. */
    public Color getFillColor() {return fillColor;}
    /** Getter-Method of {@code Color} frameColor. */
    public Color getFrameColor() {return frameColor;}
    /** Setter-Method of {@code Vector} a. */
    public void setA(Vector a) {this.a = a;}
    /** Setter-Method of {@code Vector} b. */
    public void setB(Vector b) {this.b = b;}
    /** Setter-Method of {@code Vector} b. */
    public void setC(Vector c) {this.c = c;}
    /** Setter-Method of {@code Color} color. */
    public static Triangle setFillColor(Triangle t, Color fillColor) {return new Triangle(t.getA(), t.getB(), t.getC(), fillColor, t.getFrameColor());}
    /** Setter-Method of {@code Color} frameColor. */
    public static Triangle setFrameColor(Triangle t, Color frameColor) {return new Triangle(t.getA(), t.getB(), t.getC(), t.getFillColor(), frameColor);}
}