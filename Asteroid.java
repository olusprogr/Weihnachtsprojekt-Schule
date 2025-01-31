import sas.*;
import java.awt.Color;

public class Asteroid {
    public Circle kreis;
    
    public int speed = 2;
    public double rotationSpeed = 0.5;
    public Picture asteroid;

    public Asteroid(double pX, double pY) {
        asteroid = new Picture(pX, pY, "standart_asteroid.png");
    }

    public void bewege() {
        asteroid.move(0, speed);
        asteroid.turn(rotationSpeed);
    }
       
    public Picture getShapeAsImg() {
        return asteroid;
    }
}