package com.chestbrah.dddlib.engine;
// 02.02.2024
// Rendering System
// By Mirza Makarevic
// Version: 1.0
import javax.swing.JPanel;

import com.chestbrah.dddlib.engine.math.Triangle;

import java.awt.*;
import java.util.ArrayList;
/**
 * The {@code Renderer} Class draws the transformed and projected Polygons to a {@code JPanel}.
 */
public class Renderer extends JPanel { // Needs refinement to stop flicker.
    private ArrayList<Triangle> renderList;
    private Polygon[] polyList;
    private Color[] polyFillList, polyFrameList;
    /**
     * Constructor for initializing {@code Renderer}.
     * @param renderBuffer {@code ArrayList} of {@code Triangles} to render.
     */
    public Renderer(ArrayList<Triangle> renderBuffer) {
        this.renderList = renderBuffer;
    }
    /**
     * The updatePanel Method renders frames by drawing the {@code Graphics} of the {@code JPanel}.
     */
    public void pushFrame() {
        Graphics2D g2 = (Graphics2D) getGraphics();
        polyList = new Polygon[renderList.size()];
        polyFillList = new Color[renderList.size()];
        polyFrameList = new Color[renderList.size()];
        // Creates Polygons for each Triangle
        for (int i = 0; i < renderList.size(); i++) {
            // Draw individual triangles
            polyFillList[i] = renderList.get(i).getFillColor();
            polyFrameList[i] = renderList.get(i).getFrameColor();
            polyList[i] = new Polygon(
                new int[] {
                    (int) renderList.get(i).getA().getX(),
                    (int) renderList.get(i).getB().getX(),
                    (int) renderList.get(i).getC().getX()
                }, 
                new int[] {
                    (int) renderList.get(i).getA().getY(),
                    (int) renderList.get(i).getB().getY(),
                    (int) renderList.get(i).getC().getY()
                }, 3);
        }
        // Draws each Polygon
        g2.setColor(Color.BLACK); //"SKYBOX"
        g2.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < polyList.length; i++) {
            if(polyFrameList[i] != null) {
                g2.setColor(polyFrameList[i]);
                g2.drawPolygon(polyList[i]);
            }
            if(polyFillList[i] != null) { //Will be changed eventualy
                g2.setColor(polyFillList[i]);
                g2.fillPolygon(polyList[i]);
            }
        }
        g2.setColor(Color.WHITE);
        g2.drawString("WIP - ver. 1.0", 0, 10); // nice
        g2.dispose();
    }
}