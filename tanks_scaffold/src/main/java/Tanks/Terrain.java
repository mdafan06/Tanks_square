package Tanks;

import processing.core.PApplet;
import processing.core.PImage;
import java.lang.Math;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Terrain {
    private int[][] heightMap;
    private PImage backgroundImage;
    private int foregroundColor;

    public Terrain(PApplet sketch, String levelFile, String backgroundImagePath, int foregroundColor) {
        this.backgroundImage = sketch.loadImage(backgroundImagePath);
        this.foregroundColor = foregroundColor;

        String[] lines = sketch.loadStrings(levelFile); // .loadStrings stores the contents of the file as an array of strings
        //System.out.println("Level file lines: " + lines);

        int maxwidth = 0;
        for (String line : lines) {
            maxwidth = Math.max(maxwidth, line.length());
        }

        heightMap = new int[maxwidth][lines.length];
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == 'X') {
                    heightMap[x][y] = y * App.CELLHEIGHT;
                } else {
                    heightMap[x][y] = 0;
                }

            }
            //System.out.println(Arrays.deepToString(heightMap));
        }
    }
    

    public void render(PApplet sketch) {
        sketch.image(backgroundImage, 0, 0);
        sketch.fill(foregroundColor);
        sketch.noStroke();
        sketch.beginShape();

        for (int y = 0; y < heightMap[0].length; y++) {
            //sketch.beginShape(PApplet.TRIANGLE_STRIP);
            for (int x = 0; x < heightMap.length; x++) {
                if (heightMap[x][y] > 0) {
                    int size = Math.min(App.CELLSIZE, heightMap[x][y]);
                    sketch.rect(x * App.CELLSIZE, y * App.CELLSIZE, size, size);

                }
                //sketch.vertex(x * App.CELLSIZE, y * App.CELLHEIGHT);
                //sketch.vertex(x * App.CELLSIZE, y * App.CELLHEIGHT - heightMap[x][y]);
            }
            sketch.endShape();
        }
    }
    
}

        
