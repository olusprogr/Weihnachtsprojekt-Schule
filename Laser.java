import sas.*;
import java.awt.Color;

public class Laser extends Defence {
    public Picture laser;
    private boolean laserActivated = false;

    public Laser(double pX, double pY) {
        super(pX, pY);
        this.sword.setHidden(true);
        this.booster.setHidden(true);
        
        laser = new Picture(pX, pY, "laser.png");
    }

    public void bewege() {
        laser.move(0, -speed);
        if (laser.getShapeY() > 0) {
            laser.move(0,-700);
        }
    }
       
    public Picture getShapeAsImg() {
        return laser;
    }
    
    public void setLaserActivated(boolean state) {
        System.out.println("test");
        laserActivated = state;
    }
    
    public boolean getLaserActivated() {
        return laserActivated;
    }
}