import processing.core.PApplet;

public class Circle {
    private final PApplet sketch;
    float x;
    float y;
    float radius;

    public Circle(PApplet sketch, float x, float y, float radius) {
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        sketch.ellipse(x, y, radius, radius);
    }
}
