import sas.*;
import java.awt.Color;

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

    public Ufospiel() {
        fenster = new View(400,750,"Das Ufospiel");
        fenster.setBackgroundColor(Color.BLACK);
        backgroundImg = new Picture(-200,-400,"galaxy.jpg");
        
        textCounter = new Text(250, 50, "Progress: 0", Color.WHITE);

        derAsteroid = new Asteroid(genRanNum(), 0);
        derBlaueAsteroid = new Asteroid_blau(genRanNum(), 0);
        mond = new Mond(genRanNum(), 0);
        coin = new Booster(genRanNum(), 0);
        speedBoost = new Speed(genRanNum(), 0);
        defence = new Defence(genRanNum(), 0);
        laser = new Laser(genRanNum(), 0);

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
                derBlaueAsteroid.speed = 4;
                derAsteroid.speed = 3;
                mond.rotationSpeed = 0.3;
                derAsteroid.rotationSpeed = 0.6;
                spell = true;
            }
            
            if (progress == 60) {
                derBlaueAsteroid.speed = 5;
                derAsteroid.speed = 4;
                mond.rotationSpeed = 0.5;
                derAsteroid.rotationSpeed = 1;
                spell = true;
                defenceI = true;
            }
            
            if (progress == 100) {
                derBlaueAsteroid.speed = 6;
                derAsteroid.speed = 5;
                mond.speed = 2;
                mond.rotationSpeed = 0.5;
                derAsteroid.rotationSpeed = 1;
                dasUfo.movementSpeed = 2;
                spell = true;
            }
            
            if (fenster.keyLeftPressed()) {
                dasUfo.bewegeLinks();
                dasUfo.turningAnimation("left");
            } else if (fenster.keyRightPressed()) {
                dasUfo.bewegeRechts();
                dasUfo.turningAnimation("right");
            } else {
                dasUfo.turningAnimation("");
            }
            
            if (dasUfo.istKollidiert()) {
                spielendeErreicht = true;
            }
            
            derAsteroid.bewege();
            if (derAsteroid.getShapeAsImg().getShapeY() >= 750) {
                progress += 1;
                counterUpProgress();
                derAsteroid.getShapeAsImg().moveTo(genRanNum(), 0);
            }
            
            derBlaueAsteroid.bewege();
            if (derBlaueAsteroid.getShapeAsImg().getShapeY() >= 750) {
                progress += 1;
                counterUpProgress();
                derBlaueAsteroid.getShapeAsImg().moveTo(genRanNum(), 0);
            }
            
            mond.bewegeImg();
            if (mond.getShapeAsImg().getShapeY() >= 750) {
                progress += 1;
                counterUpProgress();
                mond.getShapeAsImg().moveTo(genRanNum(), 0);
            }
            
            coin.bewege();
            if (coin.getShapeAsImg().getShapeY() >= 750) {
                coin.getShapeAsImg().moveTo(genRanNum(), 0);
                coin.getShapeAsImg().setHidden(false);
            }
            
            speedBoost.bewege();
            if (spell == true) {
                speedBoost.getShapeAsImg().moveTo(genRanNum(), 0);
                speedBoost.getShapeAsImg().setHidden(true);
                spell = false;
            }
            
            defence.bewege();
            if (defenceI == true) {
                defence.getShapeAsImg().moveTo(genRanNum(), 0);
                defence.getShapeAsImg().setHidden(true);
                defenceI = false;
            }
            
            //System.out.println(laser.getLaserActivated());
            
            if (laser.getLaserActivated() == true) {
                laser.bewege();
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