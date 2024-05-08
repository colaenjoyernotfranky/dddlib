package com.chestbrah.dddlib.engine.math;
// 01.02.2024
// Vector-Implementation 
// By Mirza Makarevic
// Version: 1.0

/**
 * The Class {@code Vector} is an implenetation of Vectors in the third dimension, which contains the attributes x, y and z as the coordinates of 
 * an {@code Vector} Object as well as most commonly used mathematical operations when working with Vectors.
 */
public class Vector {
    private float x, y, z;
    // Constructors
    /** Empty Constuructor - returns an {@code Vector} with x, y, z equal to 0. */
    public Vector() {this.x = this.y = this.z = 0.0f;}
    /**
     * Constructor with x, y and z as parameters - returns an {@code Vector} with the given x, y and z as set attributes.
     * @param x of type {@code float} - parameter for attribute x of {@code Vector}
     * @param y of type {@code float} - parameter for attribute y of {@code Vector}
     * @param z of type {@code float} - parameter for attribute z of {@code Vector}
     */
    public Vector(float x, float y, float z) {this.x = x; this.y = y; this.z = z;}
    /**
     * Copy-Constructor with v as parameter. v is an existing {@code Vector}.
     * @param v existing {@code Vector} that should be copied.
     */
    public Vector(Vector v) {this.x = v.x; this.y = v.y; this.z = v.z;}
    // Getters
    /** Getter-Method of attribute x. */
    public float getX() {return x;}
    /** Getter-Method of attribute y. */
    public float getY() {return y;}
    /** Getter-Method of attribute z. */
    public float getZ() {return z;}
    // Setters
    /** Setter-Method of attribute x. */
    public void setX(float x) {this.x = x;}
    /** Setter-Method of attribute y. */
    public void setY(float y) {this.y = y;}
    /** Setter-Method of attribute z. */
    public void setZ(float z) {this.z = z;}
     /** Returns the {@code Vector} as an {@code String} */
     public String toString() {return "(" + x + ", " + y + ", " + z + ")";}
    // Calculation
    /** Calculates and returns the length of a Vector of type {@code float}. */
    public float length() {return (float) Math.sqrt(((this.x*this.x) + (this.y*this.y) + (this.z*this.z)));}
    /**
     * Method for adding two Vectors.
     * @param v1 of type {@code Vector}. 
     * @param v2 of type {@code Vector}.
     * @return Result of the addition.
     */
    public static Vector add(Vector v1, Vector v2) {return new Vector((v1.x + v2.x), (v1.y + v2.y), (v1.z + v2.z));}
    /**
     * Method for subtracting two Vectors.
     * @param v1 of type {@code Vector}.
     * @param v2 of type {@code Vector}.
     * @return Result of the subtraction.
     */
    public static Vector sub(Vector v1, Vector v2) {return new Vector((v1.x - v2.x), (v1.y - v2.y), (v1.z - v2.z));}
    /**
     * Method for multiplying Vectors with a scalar of type {@code float}
     * @param v of type {@code Vector}.
     * @param s of type {@code float}.
     * @return Result of the multiplication.
     */
    public static Vector scalMult(Vector v, float s) {return new Vector(s*v.x, s*v.y, s*v.z);}
    /**
     * Method for dividing Vectors with a scalar of type {@code float}
     * @param v of type {@code Vector}.
     * @param s of type {@code float}.
     * @return Result of the multiplication.
     */
    public static Vector scalDiv(Vector v, float s) {return new Vector(v.x/s, v.y/s, v.z/s);}
    /**
     * Method for calculating the scalar product of two Vectors.
     * @param v1 of type {@code Vector}.
     * @param v2 of type {@code Vector}.
     * @return The scalar product of of type {@code float}.
     */
    public static float dotProd(Vector v1, Vector v2) {return (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z);}
    /**
     * Method for calculating the cross product of two Vectors.
     * @param v1 of type {@code Vector}.
     * @param v2 of type {@code Vector}. 
     * @return The cross product of two Vectors.
     */
    public static Vector crossProd(Vector v1, Vector v2) {return new Vector(((v1.y*v2.z)-(v1.z*v2.y)), ((v1.z*v2.x)-(v1.x*v2.z)), ((v1.x*v2.y)-(v1.y*v2.x)));}
    /**
     * Returns the Normal-Vector of two Vectors
     * @param a Vector a
     * @param b Vector b
     */
    public static Vector getNormal(Vector a, Vector b) {
        Vector n = Vector.crossProd(a, b);
        return n;
    }
    /**
     * Method for normalizing Vectors.
     * @return normalized form of {@code Vector}.
     */
    public Vector normalize() {float l = this.length(); return new Vector(this.x /= l, this.y /= l, this.z /= l);}
}