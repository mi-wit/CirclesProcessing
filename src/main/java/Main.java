import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;


public class Main extends PApplet {

    private static final int CONFIG_SCALE_FACTOR = 3;
    List<Circle> circles = new ArrayList<>();
    int seed;

    @Override
    public void settings() {
        int s = 250 * 3;
        size(4 * s, 3 * s);
//        fullScreen();
        smooth(2);
        seed = millis();
    }

    @Override
    public void setup() {
        seededRender();
    }

    void seededRender() {
        randomSeed(seed);
        noiseSeed(seed);
        render();
    }

    @Override
    public void draw() {
        // wait for key input
    }

    private void render() {
        background(20);
        stroke(80);
        for (int i = 0; i < 500; i++) {
            line(cos(i) * width, height, cos(i) * random(width), cos(i) * random(height));
        }

        // koła
        for (int i = 0; i < 5000; i++) {
            circles.add(new Circle(this, random(width), random(height), sin((float) (i % 5))*random(20, 25)));
        }


        for (Circle circle : circles) {
            fill(map(circle.x * circle.y, 0, width * height, 0, 255));
            stroke(68, 110, 38);
//            stroke(148, 20, 0);
            strokeWeight(1);
            if ((int) random(0, 5) == 1) {
                Circle rndCircle = circles.get((int) random(circles.size()));
                line(circle.x, circle.y, rndCircle.x, rndCircle.y);
            }

            strokeWeight(random(5, 8));
            stroke(209, 230, 237, map(circle.x * circle.y, 0, width * height, 0, 255));
            line(width, height, circle.x, circle.y);
            circle.draw();

        }
        System.out.println("hmm");
    }

    void saveHighRes(int scaleFactor) {
        PGraphics hires = createGraphics(
                width * scaleFactor,
                height * scaleFactor,
                JAVA2D);
        println("Generating high-resolution image...");

        beginRecord(hires);
        hires.scale(scaleFactor);
        seededRender();
        endRecord();

        hires.save(seed + "-highres.png");
        println("Finished");
    }

    @Override
    public void keyPressed() {
        if (key == 's') {
            saveLowRes();
        } else if (key == 'h') {
            saveHighRes(CONFIG_SCALE_FACTOR);
        } else {
            seed = millis();
            seededRender();
        }
    }

    void saveLowRes() {
        println("Saving low-resolution image...");
        save(seed + "-lowres.png");
        println("Finished");
    }

    public static void main(String[] args) {
        String[] processingArgs = {Main.class.getName()};
        Main mySketch = new Main();

        PApplet.runSketch(processingArgs, mySketch);
    }
}
