package com.chestbrah.dddlib.engine.math;

import java.util.ArrayList;

import com.chestbrah.dddlib.engine.GameObject;

/**
 * Class Transform providing different Calculations for transforming polygons of type {@code Triangle} 
 * and Vectors of type {@code Vector}
 */
public class Transform {
    /**
     * Constructor for initializing {@code Transform}.
     */
    public Transform() {}
    /**
     * Method including all world space transformations
     * Creates appropriate world Matrix and multiplies it 
     * with the Vectors of the specified Triangle
     * @param t {@code Triangle} that gets transformed
     * @param x Translation in x direction
     * @param y Translation in y direction
     * @param z Translation in z direction
     * @param rotX Rotation on x axis
     * @param rotY Rotation on y axis
     * @param rotZ Rotation on z axis
     * @param s scale factor
     * @return the transformed {@code Triangle}
     */
    public static Triangle transform(Triangle t, float x, float y, float z, float rotX, float rotY, float rotZ, float s) {
        // Calculating world Matrix
        Matrix4x4 xMat, yMat, zMat, tMat, sMat, wMat;
        xMat = new Matrix4x4('x', (float) Math.toRadians(rotX));
        yMat = new Matrix4x4('y', (float) Math.toRadians(rotY));
        zMat = new Matrix4x4('z', (float) Math.toRadians(rotZ));
        tMat = new Matrix4x4(x/s, y/s, z/s);
        sMat = new Matrix4x4(s);
        wMat = Matrix4x4.matMult(sMat, xMat);
        wMat = Matrix4x4.matMult(wMat, yMat);
        wMat = Matrix4x4.matMult(wMat, zMat);
        wMat = Matrix4x4.matMult(wMat, tMat);
        // Creating triangle
        Triangle o = new Triangle(Matrix4x4.vecMult(t.getA(), wMat), Matrix4x4.vecMult(t.getB(), wMat), 
        Matrix4x4.vecMult(t.getC(), wMat), t.getFillColor(), t.getFrameColor());
        return o;
    }
    /**
     * Transforms an GameObject in Worldspace
     * Method including all world space transformations
     * Creates appropriate world Matrix and multiplies it 
     * with all Vectors of the specified Triangle-ArrayList
     * @param gO {@code GameObject} that gets Transformed
     * @param x Translation in x direction
     * @param y Translation in y direction
     * @param z Translation in z direction
     * @param rotX Rotation on x axis
     * @param rotY Rotation on y axis
     * @param rotZ Rotation on z axis
     * @param s scale factor
     * @return the transformed {@code GameObject}
     */
    public static GameObject transform(GameObject gO, float x, float y, float z, float rotX, float rotY, float rotZ, float s) {
        ArrayList<Triangle> i = gO.getModel();
        ArrayList<Triangle> oList = new ArrayList<Triangle>();
        GameObject o;
        Matrix4x4 xMat, yMat, zMat, tMat, sMat, wMat;
        xMat = new Matrix4x4('x', (float) Math.toRadians(rotX));
        yMat = new Matrix4x4('y', (float) Math.toRadians(rotY));
        zMat = new Matrix4x4('z', (float) Math.toRadians(rotZ));
        tMat = new Matrix4x4(x, y, z);
        sMat = new Matrix4x4(s);
        wMat = Matrix4x4.matMult(sMat, xMat);
        wMat = Matrix4x4.matMult(wMat, yMat);
        wMat = Matrix4x4.matMult(wMat, zMat);
        wMat = Matrix4x4.matMult(wMat, tMat);
        for (Triangle t : i) {
            // Creating triangle
            Triangle oT = new Triangle(Matrix4x4.vecMult(t.getA(), wMat), Matrix4x4.vecMult(t.getB(), wMat), 
            Matrix4x4.vecMult(t.getC(), wMat), t.getFillColor(), t.getFrameColor());
            oList.add(oT);
        }
        o = new GameObject(x, y, z, oList);
        o.setName(gO.getName());
        return o;
    }
    /**
     * Projects an Triangle using a Matrix
     * @param t Triangle
     * @param m Projection Matrix
     * @return result
     */
    public static Triangle projMat(Triangle t, Matrix4x4 m) {
        return new Triangle(
            Matrix4x4.vecMult(t.getA(), m), 
            Matrix4x4.vecMult(t.getB(), m), 
            Matrix4x4.vecMult(t.getC(), m),
            t.getFillColor(),
            t.getFrameColor()
        );
    }
}