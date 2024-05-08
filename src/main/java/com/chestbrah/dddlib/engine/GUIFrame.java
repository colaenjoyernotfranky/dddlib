package com.chestbrah.dddlib.engine;
// 02.02.2024
// GUI for 3D Engine - WIP
// By Mirza Makarevic
// Version: 1.0
import java.util.ArrayList;
import javax.swing.*;
import com.chestbrah.dddlib.engine.math.*;

import java.awt.*;
public class GUIFrame extends JFrame{
    // Runtime related
    private static float fTheta = Float.MIN_NORMAL;
    // FPS-Counter Variables
    private static long startSec = System.currentTimeMillis();
    private static int fps = 0;
    // Init of objectList, renderBuffer and Renderer
    private static ArrayList<GameObject> objectList;
    private static ArrayList<Triangle> renderBuffer;
    // Window related Attributes
    private static final int s_width = 1024;
    private static final int s_height = 960;
    private static final float s_aspect_ratio = ((float)s_height/(float)s_width);
    // Init of projection Matrix based on specified variables (fNear, fFar, fFov should be changeable)
    private static final Matrix4x4 projM = new Matrix4x4(0.1f, 1000.0f, 90f, s_aspect_ratio);
    public GUIFrame() {
        objectList = new ArrayList<>();
        renderBuffer = new ArrayList<>();
        setSize(s_width, s_height);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("3D Engine - WIP");
    }
    /**
     * Counts FPS and changes the Title accordingly
     * @param secStartTime Time when the first measurement is taken.
     */
    private void setTitleFPS(long secStartTime) {
        long secEndTime = System.currentTimeMillis();
        float sec = (float) ((secEndTime - secStartTime));
        if(sec >= 1000.0f) {
            setTitle("3D Engine - WIP - FPS: " + fps);
            startSec = secEndTime;
            fps = 0;
        } else
            fps += 1;
    }

    private Triangle offsetIntoView(Triangle t) {
        Vector a, b, c;
        a = t.getA(); 
        a.setX(a.getX() + 1.0f); a.setY(a.getY() + 1.0f);
        a.setX(a.getX() * 0.5f * s_width); a.setY(a.getY() * 0.5f * s_height);
        // Vector B
        b = t.getB();
        b.setX(b.getX() + 1.0f); b.setY(b.getY() + 1.0f);
        b.setX(b.getX() * 0.5f * s_width); b.setY(b.getY() * 0.5f * s_height);
        // Vector C
        c = t.getC();
        c.setX(c.getX() + 1.0f); c.setY(c.getY() + 1.0f);
        c.setX(c.getX() * 0.5f * s_width); c.setY(c.getY() * 0.5f * s_height);
        return t;
    }
    /** Adds an {@code GameObject} to the Scene */
    private void addObject(GameObject g) {objectList.add(g);}
    /** Adds an {@code GameObject} to the Scene */
    private void addObject(GameObject g, String name) {g.setName(name); objectList.add(g);}

    public static void main(String[] args) {
        long progStartTime = System.currentTimeMillis();
        // GUI Initialization
        GUIFrame frame = new GUIFrame();
        Renderer r = new Renderer(renderBuffer);
        frame.add(r, BorderLayout.CENTER);
        r.repaint();
        // Camera Vector
        Vector camV = new Vector(0.0f, 0.0f, 0.0f);
        // Dir Light Vector
        Vector dirLiV = new Vector(0.0f, 0.0f, -1.0f);
        float liLen = dirLiV.length();
        dirLiV = Vector.scalDiv(dirLiV, liLen); // NORMALIZE dirLiV
        // Create and Add Objects
        GameObject chaizard = new GameObject().setModel("src/main/java/com/chestbrah/dddlib/engine/models/Chaizard.obj");
        /*
        GameObject scene = new GameObject("src/main/java/com/chestbrah/dddlib/engine/models/scene.obj");
        scene = Transform.transform(s, 0.0f, 0.3f, -51.0f, 0.0f, 0.0f, 180.0f, 1f);
        chaizard = Transform.transform(c, -1.0f, 0.3f, -20.0f, 0.0f, 90.0f, 180.0f, 0.3f);
        GameObject link = new GameObject().setModel("src/main/java/com/chestbrah/dddlib/engine/models/Link.obj");
        link = Transform.transform(l, 1.0f, 0.3f, -20.0f, 0.0f, 90.0f, 180.0f, 0.3f);
        frame.addObject(link);
        frame.addObject(chaizard);
        frame.addObject(scene);
        */
        frame.addObject(chaizard);
        while(true) {
            // Calculate elapsed Time since program start in ms.
            long loopStartTime = System.currentTimeMillis();
            float progElapsedTime = (float) ((loopStartTime - progStartTime));
            fTheta = 0.05f * progElapsedTime; // Change Angle based on elapsedTime
            // Iterating through renderList and transforming the Triangles
            for (GameObject g : objectList) {
                GameObject tranG;
                tranG = Transform.transform(g, 0.0f, 1.5f, -50.0f, 0.0f, fTheta, 180.0f, 1f);
                for (Triangle tri : tranG.getModel()) {   // NEED TO CHANGE TO RENDER GAMEOBJECTS
                                                    // NOT "ONLY" TRIANGLES
                    // Different Matrices (MULTIPLICATION: first to last - right to left)
                    Triangle offsetTri, projTri, shadTri, transTri;
                    Vector lineA, lineB, triN, triNNorm;
                    /* MAYBE IMPORTANT LATER
                        // TRANSLATION AND ROTATION
                        transTri = Transform.transform(tri,
                            0.0f, 0.0f, 0.0f,
                            0.0f, 0.0f, 0.0f,
                            1f);
                    */
                    // CALCULATING NORMAL OF TRIANGLE
                    lineA = new Vector(Vector.sub(tri.getB(), tri.getA()));
                    lineB = new Vector(Vector.sub(tri.getC(), tri.getA()));
                    triN = Vector.getNormal(lineA, lineB);
                    // NORMALIZING NORMAL VECTOR LENGTH
                    triNNorm = triN.normalize();
                    // DO NOT RENDER THINGS FACING AWAY FROM THE CAMERA
                    // (dont Draw if triN*(transTri.getA()-camV) >= 0)
                    if(Vector.dotProd(triNNorm, Vector.sub(tri.getA(), camV)) < 0.0f) {
                        shadTri = tri;  // So the object doesn't fade away
                                        // (THIS TOTALLY DID NOT HAPPEN TO ME)
                        if(tri.getFillColor() != null) {
                            // LIGHTING/SHADING
                            // Get Shading factor
                            float lDotP = Math.abs(Vector.dotProd(triNNorm, dirLiV));
                            // calculate r, g and b values
                            int red = (int) (tri.getFillColor().getRed() * lDotP);
                            int blue = (int) (tri.getFillColor().getBlue() * lDotP);
                            int green = (int) (tri.getFillColor().getGreen() * lDotP);
                            // change color accordingly
                            shadTri = Triangle.setFillColor(tri, new Color(red, green, blue));
                        }
                        // PROJECTING WORLDSPACE TO SCREENSPACE
                        projTri = Transform.projMat(shadTri, projM);
                        // OFFSET INTO VIEW
                        offsetTri = frame.offsetIntoView(projTri);
                        // Add transformed and projected Triangle to renderBuffer for
                        // Renderer
                        renderBuffer.add(offsetTri);
                    }
                }
            }
            renderBuffer.sort((t1, t2) -> Float.compare(
                    ((t1.getA().getZ() + t1.getB().getZ() + t1.getC().getZ()) / 3.0f),
                    ((t2.getA().getZ() + t2.getB().getZ() + t2.getC().getZ()) / 3.0f)
            ));
            // Render next frame
            r.pushFrame();
            // Clear the Buffer for next frame
            renderBuffer.clear();
            frame.setTitleFPS(startSec);
        }
    }
}