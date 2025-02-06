import sas.*;
import java.awt.Color;
import java.util.ArrayList;

public class Ufospiel {
    private View fenster;
    private Picture backgroundImg;
    
    private Text textCounter;
    private int progress = 0;

    private Ufo dasUfo;
    private Asteroid derAsteroid;
    private Asteroid_blau derBlaueAsteroid;
    private Mond mond;
    
    private Booster coin;
    public int coinsCollected = 0;
    private Speed speedBoost;
    private Defence defence;
    private Laser laser;
    
    private Asteroid[] asteroids;

    public Ufospiel() {
        fenster = new View(400, 750, "Das Ufospiel");
        fenster.setBackgroundColor(Color.BLACK);
        backgroundImg = new Picture(-200,-400,"galaxy.jpg");
        
        textCounter = new Text(250, 50, "Progress: 0", Color.WHITE);

        derAsteroid = new Asteroid(genRanNum(), 0);
        derBlaueAsteroid = new Asteroid_blau(genRanNum(), 0);
        mond = new Mond(genRanNum(), 0);
        coin = new Booster(genRanNum(), 0);
        speedBoost = new Speed(genRanNum(), 0);
        speedBoost.getShapeAsImg().setHidden(true);
        defence = new Defence(genRanNum(), 0);
        defence.getShapeAsImg().setHidden(true);
        laser = new Laser(0, 0);
        
        asteroids = new Asteroid[] {derAsteroid, derBlaueAsteroid, mond};


        dasUfo = new Ufo(
        fenster.getWidth()/2, fenster.getHeight()-100,
        derAsteroid,
        derBlaueAsteroid,
        mond,
        coin,
        speedBoost,
        defence,
        laser
        );
        
        spielen();
    }

    public void spielen() {
        boolean spielendeErreicht = false;
        boolean spell = false;
        boolean defenceI = false;
        
        while (!spielendeErreicht && !fenster.keyEnterPressed()) {
            if (progress == 30) {
                mond.rotationSpeed = 0.3;
                derAsteroid.rotationSpeed = 0.6;
                spell = true;
            }
            
            if (progress == 60) {
                mond.rotationSpeed = 0.5;
                derAsteroid.rotationSpeed = 1;
                spell = true;
                defenceI = true;
            }
            
            if (progress == 100) {
                mond.rotationSpeed = 0.5;
                derAsteroid.rotationSpeed = 1;
                dasUfo.movementSpeed = 2;
                spell = true;
            }
            
            derAsteroid.speed = 2 + Math.round(progress/40);
            derBlaueAsteroid.speed = 3 + Math.round(progress/30);
            mond.speed = 1 + Math.round(progress/60);
            
            if (fenster.keyLeftPressed() && dasUfo.getShapeAsImg().getShapeX() >= 0) {
                dasUfo.bewegeLinks();
                dasUfo.turningAnimation("left");
            } else if (fenster.keyRightPressed() && dasUfo.getShapeAsImg().getShapeX() <= 350) {
                dasUfo.bewegeRechts();
                dasUfo.turningAnimation("right");
            } else {
                dasUfo.turningAnimation("");
            }
            
            if (dasUfo.istKollidiert()) {
                spielendeErreicht = true;
            }
            
            for (int i = 0; i < asteroids.length; i++) {
                asteroids[i].bewege();
                if(asteroids[i].getShapeAsImg().getShapeY() >= 750) {
                    progress += 1;
                    counterUpProgress();
                    asteroids[i].getShapeAsImg().moveTo(genRanNum(), 0);
                    asteroids[i].getShapeAsImg().setHidden(false);
                }
            }
            
            coin.bewege();
            if (coin.getShapeAsImg().getShapeY() >= 750) {
                coin.getShapeAsImg().moveTo(genRanNum(), 0);
                coin.getShapeAsImg().setHidden(false);
            }
            
            speedBoost.bewege();
            if (spell == true) {
                speedBoost.getShapeAsImg().moveTo(genRanNum(), -50);
                speedBoost.getShapeAsImg().setHidden(false);
                spell = false;
            }
            
            defence.bewege();
            if (defenceI == true) {
                defence.getShapeAsImg().moveTo(genRanNum(), -50);
                defence.getShapeAsImg().setHidden(false);
                defenceI = false;
            }
                        
            if (laser.getLaserActivated() == true) {
                laser.bewege();

                if (laser.getShapeAsImg().getShapeY() < 0) {
                    laser.getShapeAsImg().moveTo(dasUfo.getShapeAsImg().getShapeX() + 15, 620);
                    laser.getShapeAsImg().setHidden(false);
                }
                
                if (
                    laser.getShapeAsImg().intersects(derAsteroid.getShapeAsImg())
                )   {
                    derAsteroid.getShapeAsImg().setHidden(true);
                    laser.getShapeAsImg().setHidden(true);
                }
                
                if (laser.getShapeAsImg().intersects(derBlaueAsteroid.getShapeAsImg())) {
                    derBlaueAsteroid.getShapeAsImg().setHidden(true);
                    laser.getShapeAsImg().setHidden(true);
                }
                
                if (laser.getShapeAsImg().intersects(mond.getShapeAsImg())) {
                    laser.getShapeAsImg().setHidden(true);
                }
            }
             
            fenster.wait(5);
        }
    }
    
    public int genRanNum() {
        int zufallszahl = (int) (Math.random()*fenster.getWidth()-20);
        return zufallszahl;
    }
    
    private void counterUpProgress() {
        textCounter.setText("Progress: " + progress);
    }
    
    private void getCollectedCoins() {
        coinsCollected = dasUfo.coinsCollected;
    }
}