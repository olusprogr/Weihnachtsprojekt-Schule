import sas.*;
import java.awt.Color;

public class Defence extends Booster {
    public Picture sword;

    public Defence(double pX, double pY) {
        super(pX, pY);
        this.booster.setHidden(true);
        
        sword = new Picture(pX, pY, "defence.png");
    }

    public void bewege() {
        sword.move(0, speed);
        if (sword.getShapeY() > 810) {
            sword.move(0, -810);
        }
    }
       
    public Picture getShapeAsImg() {
        return sword;
    }
}