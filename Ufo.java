import sas.*;

public class Ufo {
    private Picture ufoBild;
    private Picture explosionsBild;
    private Asteroid asteroid;
    private Asteroid_blau derBlaueAsteroid;
    private Mond moon;
    private Booster coin;
    
    private int TURN_ANGLE = 20;
    private int currentAngle = 0;
    private boolean turnedLeft = false;
    private boolean turnedRight = false;
    public int movementSpeed = 1;
    public int coinsCollected = 0;

    public Ufo(double pX, double pY,
        Asteroid pAsteroid,
        Asteroid_blau derBlaueAsteroid,
        Mond mond,
        Booster coin
        ) {
        ufoBild = new Picture(pX, pY,"rakete.png");
        explosionsBild = new Picture(0,-100,"explosion.png");
        explosionsBild.setHidden(true);
        asteroid = pAsteroid;
        this.derBlaueAsteroid = derBlaueAsteroid;
        this.moon = mond;
        this.coin = coin;
    }

    public void bewegeLinks() {
        ufoBild.move(-movementSpeed,0);
    }

    public void bewegeRechts() {
        ufoBild.move(movementSpeed,0);
    }

    public boolean istKollidiert() {
        boolean ergebnis = false;
        
        if (
            ufoBild.intersects(asteroid.getShapeAsImg()) ||
            ufoBild.intersects(derBlaueAsteroid.getShapeAsImg()) ||
            ufoBild.intersects(moon.getShapeAsImg())
        ) {
            ergebnis = true;
            ufoBild.setHidden(true);
            explosionsBild.moveTo(ufoBild.getShapeX()-5, ufoBild.getShapeY());
            explosionsBild.setHidden(false);
        }
        
        if (
            ufoBild.intersects(coin.getShapeAsImg())
        ) {
            coinsCollected += 1;
            coin.getShapeAsImg().setHidden(true);
        }

        return ergebnis;
    }
    
    public void turningAnimation(String direction) {
        if (direction.equals("left") && !turnedLeft && currentAngle == 0) {
            ufoBild.turn(-TURN_ANGLE);
            currentAngle =+ -TURN_ANGLE;
            turnedLeft = true;
            turnedRight = false;
        } else if (direction.equals("right") && !turnedRight && currentAngle == 0) {
            ufoBild.turn(TURN_ANGLE);
            currentAngle =+ TURN_ANGLE;
            turnedRight = true;
            turnedLeft = false;
        } else if (!direction.equals("left") && !direction.equals("right") && currentAngle != 0) {
            if (turnedLeft) {
                ufoBild.turn(TURN_ANGLE);
                currentAngle = 0;

            } else if (turnedRight) {
                ufoBild.turn(-TURN_ANGLE);
                currentAngle = 0;
            }
            turnedLeft = false;
            turnedRight = false;
        }
    }
}