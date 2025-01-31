import sas.*;
import java.awt.Color;

public class Booster {
    public Picture booster;
    public int speed = 1;

    public Booster(double pX, double pY) {
        
        booster = new Picture(pX, pY, "coin.png");
    }

    public void bewege() {
        booster.move(0, speed);
        if (booster.getShapeY() > 810) {
            booster.move(0,-850);
        }
    }
       
    public Picture getShapeAsImg() {
        return booster;
    }
}