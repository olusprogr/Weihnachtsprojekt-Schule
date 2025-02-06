import sas.*;
import java.awt.Color;

public class Laser extends Defence {
    public Picture laser;
    private boolean laserActivated = false;
    int laserSpeed = 4;

    public Laser(double pX, double pY) {
        super(pX, pY);
        this.sword.setHidden(true);
        this.booster.setHidden(true);
        
        laser = new Picture(pX, pY, "laser.png");
        laser.setHidden(true);
    }

    public void bewege() {
        laser.move(0, -laserSpeed);
    }
       
    public Picture getShapeAsImg() {
        return laser;
    }
    
    public void setLaserActivated(boolean state) {
        laserActivated = state;
        if (state == true) {
            laser.setHidden(false);
        }
    }
    
    public boolean getLaserActivated() {
        return laserActivated;
    }
}