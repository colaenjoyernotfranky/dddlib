package com.chestbrah.dddlib.engine;
// 06.02.2024
// GameObject
// By Mirza Makarevic
// Version: 1.0
import com.chestbrah.dddlib.engine.file.FReader;
import com.chestbrah.dddlib.engine.math.Triangle;

import java.util.ArrayList;
/**
 * Class for Handling Objects to render ingame
 */
public class GameObject {
    private float x, y, z, rotX, rotY, rotZ;
    private String name = "GameObject";
    private ArrayList<Triangle> polygons = new ArrayList<Triangle>();
    /**
     * Initializes an empty GameObject. Has no 3D Model attached.
     */
    public GameObject() {
        this.x = this.y = this.z = this.rotX = this.rotY = this.rotZ = 0.0f;
    }
    /**
     * Initializes an GameObject with specific x, y and z coordinates. Has no 3D Model attached.
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     * @param z is the z coordinate.
     */
    public GameObject(float x, float y, float z) {
        this.x = x; this.y = y; this.z = z;
    }
    /**
     * Initializes an GameObject with specific x, y and z coordinates and ArrayList of Triangles.
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     * @param z is the z coordinate.
     * @param tList is the {@code ArrayList} with the Triangles representing the 3D Model.
     * 
     */
    public GameObject(float x, float y, float z, ArrayList<Triangle> tList) {
        this.x = x; this.y = y; this.z = z; polygons.addAll(tList);
    }
    /**
     * Initializes an GameObject with an 3D Model attached.
     * @param path is the path to the .obj File including the 3D Model
     */
    public GameObject(String path) {
        x = y = z = rotX = rotY = rotZ = 0.0f;
        polygons = FReader.readObj(path);
    }
    
    /** Returns an ArrayList of Triangles of the 3D Model */
    public ArrayList<Triangle> getModel() {return polygons;}
    /** Set the model of the GameObject */
    public GameObject setModel(String path) {polygons = FReader.readObj(path); return this;}
    public GameObject setModel(ArrayList<Triangle> tList) {polygons.clear(); polygons.addAll(tList); return this;}
    /** Returns the x position of the GameObject */
    public float getX() {return x;}
    /** Returns the y position of the GameObject */
    public float getY() {return y;}
    /** Returns the z position of the GameObject */
    public float getZ() {return z;}
    /** Set the x position of the GameObject */
    public void setX(float x) {this.x = x;}
    /** Set the y position of the GameObject */
    public void setY(float y) {this.y = y;}
    /** Set the z position of the GameObject */
    public void setZ(float z) {this.z = z;}
    /** Returns the x rotation of the GameObject */
    public float getRotX() {return rotX;}
    /** Returns the y rotation of the GameObject */
    public float getRotY() {return rotY;}
    /** Returns the z rotation of the GameObject */
    public float getRotZ() {return rotZ;}
    /** Set the x rotation of the GameObject */
    public void setRotX(float rotX) {this.rotX = rotX;}
    /** Set the y rotation of the GameObject */
    public void setRotY(float rotY) {this.rotY = rotY;}
    /** Set the z rotation of the GameObject */
    public void setRotZ(float rotZ) {this.rotZ = rotZ;}
    /** Get the name of the GameObject */
    public String getName() {return this.name;};
    /** Set the name of the GameObject */
    public void setName(String name) {this.name = name;};
}
