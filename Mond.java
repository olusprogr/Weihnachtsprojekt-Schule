import sas.*;
import java.awt.Color;

public class Mond extends Asteroid
{
    Picture moon;
    double rotationSpeed = 0.2;
    
    public Mond(double pX, double pY)
    {
        super(pX, pY);
        
        this.speed = 1;
        this.asteroid.setHidden(true);
        
        moon = new Picture(130, 130, "moon.png");
    }
    
    public void bewegeImg() {
        moon.move(0, this.speed);
        moon.turn(rotationSpeed);
    }
    
    public Picture getShapeAsImg() {
        return moon;
    }
}
