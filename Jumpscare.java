import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jumpscare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jumpscare extends Actor
{
    /**
     * Act - do whatever the Jumpscare wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootSound maltigi = new GreenfootSound("maltigi.mp3");
    
    private GreenfootImage[] scaryImgs = {
        new GreenfootImage("evilskeleton.png"), new GreenfootImage("bigbear5.png"), new GreenfootImage("literallyjustanelephant.png")
        };

    private int chance;
    private boolean alreadyScared;
    private static int titleStage = 0;
    private boolean alreadyPressed;
    private boolean alreadyStarted;
    
    public void addedToWorld() {
        getImage().scale(800, 600);
        getImage().setTransparency(0);
    }
    
    public void rollJumpscare() {
        chance = Greenfoot.getRandomNumber(20);
        if (chance <= Dealer.getSpeedUp() / 2) {
            maltigi.play();
            setImage(scaryImgs[Greenfoot.getRandomNumber(scaryImgs.length)]);
            getImage().setTransparency(255);
            Greenfoot.delay(20);
            getImage().setTransparency(0);
            maltigi.stop();
        }
    }
    public static void init() {
        titleStage = 0;
    }
    public void act()
    {
            if (!alreadyStarted) {
            switch (titleStage) {
                case 0:
                    setImage("titlescreenkinda.png");
                    break;
                case 1:
                    setImage("instructions.png");
                    break;
                case 2:
                    Dealer.startMusic();
                    Player.enableLoco();
                    Dealer.startCounting();
                    getImage().setTransparency(0);
                    alreadyStarted = true;
            }
            if (Greenfoot.isKeyDown("Space") && !alreadyPressed) {
                titleStage++;
                Greenfoot.delay(1);
                Player.hitCard.play();
                alreadyPressed = true;
            }
            if (!Greenfoot.isKeyDown("Space")) {
                alreadyPressed = false;
            }
        }
        if ((Dealer.getAnimState() == 4) && Dealer.phase2Check() && (Dealer.getTimer() < 70 - (Greenfoot.getRandomNumber(4) * 10)) && !alreadyScared) {
            rollJumpscare();
            alreadyScared = true;
        }
        if (Dealer.getAnimState() == 0) {
            alreadyScared = false;
        }
        
    }
}
