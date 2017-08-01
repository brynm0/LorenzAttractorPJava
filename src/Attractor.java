import processing.core.PApplet;
import processing.core.PVector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Attractor extends PApplet {
    private float x, y, z, sigma, rho, beta;
    private List<PVector> points;
    private PrintWriter writer;
    public static void main(String[] args) {
        PApplet.main("Attractor", args);
    }
    public void settings() {
        size(1000,1000);
    }
    public void setup() {
        colorMode(HSB);
        x = 0.1f;
        y = 0f;
        z = 0f;
        rho = 28f;
        sigma = 10f;
        beta = 8.0f/3.0f;
        points = new ArrayList<PVector>();
    }
    public void draw() {
        background(255);
        float dt = 0.01f;
        float dx = (sigma * (y - x)) * dt;
        float dy = (x * (rho - z) - y) * dt;
        float dz = (x * y - beta * z) * dt;
        x = x + dx;
        y = y + dy;
        z = z + dz;
        PVector pt = new PVector(x,y,z);
        translate(width/2, height/2);
        scale(width/75);
        points.add(pt);
        beginShape();
        for (PVector p : points) {
            noFill();
            stroke(0);
            strokeWeight(0.1f);
            vertex(p.x, p.y);
        }
        endShape();
        println(points.size());

    }
    public void mousePressed() {
        //saveFrame("attractor-#####.png");
        String fileID = "_Y";
        try {
            writer = new PrintWriter("amplitude_out" + fileID + ".txt", "UTF-8");
        } catch (IOException e) {
            print("IOException found: " + e);

        }
        for (int i = 0; i < 500; i++) {
            writer.println(points.get(i).y);
        }
        writer.close();
    }
}
