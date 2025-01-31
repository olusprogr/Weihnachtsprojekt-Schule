import sas.*;
import java.awt.Color;

public class Speed extends Booster {
    public Picture speedBoost;
    public int speed = 1;

    public Speed(double pX, double pY) {
        super(pX, pY);
        this.booster.setHidden(true);
        
        speedBoost = new Picture(pX, pY, "spell.png");
    }

    public void bewege() {
        speedBoost.move(0, speed);
        if (speedBoost.getShapeY() > 810) {
            speedBoost.move(0,-850);
        }
    }
       
    public Picture getShapeAsImg() {
        return speedBoost;
    }
}