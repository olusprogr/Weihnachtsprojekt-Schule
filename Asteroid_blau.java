import sas.*;
import java.awt.Color;

public class Asteroid_blau extends Asteroid
{
    public Picture asteroidBlau;

    public Asteroid_blau(double pX, double pY) {
        super(pX, pY);
        
        this.speed = 3;
        asteroid.setHidden(true);
        
        asteroidBlau = new Picture(pX, pY, "blue_asteroid.png");
    }
    
    public void bewege() {
        asteroidBlau.move(0, this.speed);
    }
    
    public Picture getShapeAsImg() {
        return asteroidBlau;
    }
}
