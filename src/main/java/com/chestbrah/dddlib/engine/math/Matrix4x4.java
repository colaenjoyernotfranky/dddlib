package com.chestbrah.dddlib.engine.math;
// 01.02.2024
// Projection-Matrix-Implementation 
// By Mirza Makarevic
// Version: 1.0
/**
 * The Class {@code ProjMatrix} describes an 4x4 projection matrix used for projecting Worldspace to Screenspace.
 * @implNote Math by: One Lone Coder (javidx9 on YouTube)
 */
public class Matrix4x4 {
    private float[][] m = new float[4][4];
    /**
     * Default Constructor of the {@code Matrix4x4} class.
     * The {@code Matrix4x4} is filled with 0 values.
     */
    public Matrix4x4() {
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                m[x][y] = 0.0f; 
            }
        }
    }
    /**
     * Copy Constructor of the {@code Matrix4x4} class.
     */
    public Matrix4x4(Matrix4x4 m) {
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                this.m[x][y] = m.m[x][y];
            }
        }
    }
    // ENGINE SPECIFIC STUFF
    /**
     * Constructor for an 4x4 Translation matrix
     * @param x of type {@code float} as the ofset
     * @param y of type {@code float} as the ofset
     * @param z of type {@code float} as the ofset
     */    
    public Matrix4x4(float x ,float y, float z) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                m[j][i] = 0.0f; 
            }
        }
        m[0][0] = 1;
        m[1][1] = 1;
        m[2][2] = 1;
        m[3][3] = 1;
        m[3][0] = x;
        m[3][1] = y;
        m[3][2] = z;
    }
    /**
     * Constructor for an 4x4 rotation matrix.
     * @param type of type {@code char} for selecting the axis
     * @param fTheta of type {@code float} as the angle
     */
    public Matrix4x4(char type ,float fTheta) {
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                m[y][x] = 0.0f; 
            }
        }
        if(type == 'x') {
            m[0][0] = 1;
            m[1][1] = (float) Math.cos(fTheta);
            m[2][1] = (float) Math.sin(fTheta);
            m[1][2] = (float) -Math.sin(fTheta);
            m[2][2] = (float) Math.cos(fTheta);
            m[3][3] = 1;
        }
        if(type == 'z') {
            m[0][0] = (float) Math.cos(fTheta);
            m[1][0] = (float) Math.sin(fTheta);
            m[0][1] = (float) -Math.sin(fTheta);
            m[1][1] = (float) Math.cos(fTheta);
            m[2][2] = 1;
            m[3][3] = 1;
        }
        if(type == 'y') {
            m[0][0] = (float) Math.cos(fTheta);
            m[2][0] = (float) -Math.sin(fTheta);
            m[1][1] = 1;
            m[0][2] = (float) Math.sin(fTheta);
            m[2][2] = (float) Math.cos(fTheta);
            m[3][3] = 1;
        }
    }
    /**
     * Constructor for an 4x4 scaling matrix.
     * @param s of type {@code float} as the scaling factor
     */
    public Matrix4x4(float s) {
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                m[x][y] = 0.0f; 
            }
        }
        m[0][0] = s;
        m[1][1] = s;
        m[2][2] = s;
        m[3][3] = 1.0f;
    }
    /**
     * Constructor for an 4x4 projection matrix used for projecting Worldspace to Screenspace.
     * @param fNear of type {@code float} as the near cliping distance
     * @param fFar of type {@code float} as the far cliping distance
     * @param fFov of type {@code float} as the camera FOV
     * @param fAspectRatio of type {@code float} as the screen aspect ratio
     */
    public Matrix4x4(float fNear, float fFar, float fFov, float fAspectRatio) {
        float fFovRad = 1.0f / (float) Math.tan(fFov * 0.5f / 180.0f * 3.14159f);
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                m[x][y] = 0.0f; 
            }
        }
        m[0][0] = fAspectRatio * fFovRad;
        m[1][1] = fFovRad;
        m[2][2] = fFar / (fFar - fNear);
        m[2][3] = (-fFar * fNear) / (fFar - fNear);
        m[3][2] = 1.0f;
        m[3][3] = 0.0f;
    }
    /**
     * Set an value at (x|y) of the Matrix
     * @param x row index
     * @param y column index
     * @param val value to be set
     */
    public void setVal(int x, int y, float val) {
        m[y][x] = val;
    }

    /**
     * Get an value at (x|y) of the Matrix
     * @param x row index
     * @param y column index
     */
    public float getVal(int x, int y) {
        return m[y][x];
    }
    /**
     * Multiplies the Matrix with a {@code Matrix4x4}
     * @param m of type {@code Matrix4x4}
     * @return resulting {@code Matrix4x4}
     */
    public static Matrix4x4 matMult(Matrix4x4 m1, Matrix4x4 m2) {
        Matrix4x4 o = new Matrix4x4();
        for (int c = 0; c < 4; c++)
			for (int r = 0; r < 4; r++)
				o.m[r][c] = m1.m[r][0] * m2.m[0][c] + m1.m[r][1] * m2.m[1][c] + m1.m[r][2] * m2.m[2][c] + m1.m[r][3] * m2.m[3][c];     
        return o;
    }
    /**
     * Multiplies the Matrix with a {@code Vector}
     * @param v of type {@code Vector}
     * @return result of the multiplication
     */
    public static Vector vecMult(Vector v, Matrix4x4 m) {
        Vector o = new Vector(
            v.getX() * m.m[0][0] + v.getY() * m.m[1][0] + v.getZ() * m.m[2][0] + m.m[3][0],
            v.getX() * m.m[0][1] + v.getY() * m.m[1][1] + v.getZ() * m.m[2][1] + m.m[3][1],
            v.getX() * m.m[0][2] + v.getY() * m.m[1][2] + v.getZ() * m.m[2][2] + m.m[3][2]
        );
        float w = v.getX() * m.m[0][3] + v.getY() * m.m[1][3] + v.getZ() * m.m[2][3] + m.m[3][3];
        if(w != 0.0f) 
            o = Vector.scalDiv(o, w);
        return o;
    }

    public void print() {
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                System.out.print(this.m[y][x] + " ");
            }
            System.out.print("\n");
        }
    }
}