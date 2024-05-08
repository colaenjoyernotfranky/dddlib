package com.chestbrah.dddlib.engine.file;
// 03.02.2024
// .obj File Reader
// By Mirza Makarevic
// Version: 1.0

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import com.chestbrah.dddlib.engine.math.Triangle;
import com.chestbrah.dddlib.engine.math.Vector;
/**
 * The Class {@code FReader} provides the engine with the capability to read different File types
 */
public class FReader {
    /**
     * Constructor for initializing {@code FReader}.
     */
    public FReader() {}

    /**
     * The Method reads an .obj File and returns it as an ArrayList of {@code Triangle}
     * @param path the filepath
     * @return {@code ArrayList} filled with {@code Triangle} Objects that represent the Model from the .obj file
     */
    public static ArrayList<Triangle> readObj(String path) {
        ArrayList<Vector> vectors = new ArrayList<Vector>();
        ArrayList<Triangle> faces = new ArrayList<Triangle>();
        String[] splitLine; // Used for determining the coordinates and the Index of faces
        String lineReader = ""; // readLine() input
        File objFile = new File(path);
        BufferedReader in = null;
        int faceCount = 0;
        try {
            in = new BufferedReader(new FileReader(objFile));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        try {
            // reads the .obj File line by line and interprets it as an ArrayList of Triangles
            while((lineReader = in.readLine()) != null) {
                if(lineReader.length() > 0) {
                    if(lineReader.charAt(0) == 'v') {
                        splitLine = lineReader.split(" ");
                        float x, y, z;
                        x = Float.parseFloat(splitLine[1]);
                        y = Float.parseFloat(splitLine[2]);
                        z = Float.parseFloat(splitLine[3]);
                        vectors.add(new Vector(x, y, z));
                    }
                    if(lineReader.charAt(0) == 'f') {
                        splitLine = lineReader.split(" ");
                        int a, b, c;
                        a = Integer.parseInt(splitLine[1]);
                        b = Integer.parseInt(splitLine[2]);
                        c = Integer.parseInt(splitLine[3]);
                        faces.add(new Triangle(
                            vectors.get(a-1), 
                            vectors.get(b-1), 
                            vectors.get(c-1),
                            Color.ORANGE, //Need to change Hardcode
                            null
                        ));
                        faceCount += 1;
                    }
                }
            }
            System.out.println("Loaded " + faceCount + " faces");
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return faces;
    }
}