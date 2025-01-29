import sas.*;
import java.awt.Color;

public class Laser extends Booster {
    public Picture booster;
    public int speed = 1;

    public Laser(double pX, double pY) {
        super(pX, pY);
        booster = new Picture(2, 2, "coin.png");
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