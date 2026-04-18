import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectionCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionCard extends Actor
{
    /**
     * Act - do whatever the SelectionCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int velocityY;
    boolean isActive;
    boolean alreadyPicked = false;
    boolean isCorrect;
    
    static GreenfootSound correct = new GreenfootSound("thumbs up.wav");
    static GreenfootSound incorrect = new GreenfootSound("boss 2 intro errorbuzz.wav");
    
    public void pickRandom() {
        int i = Greenfoot.getRandomNumber(2);
        if (i == 1) {
            setImage("heart-ace.png");
            isCorrect = true;
        } else {
            setImage("spade-ace.png");
            isCorrect = false;
        }
    }
    
    public void act()
    {
        if (Dealer.getGameState() >= 3) {
            isActive = true;
            if (!(alreadyPicked)) {
            pickRandom();
            alreadyPicked = true;
            }
            getImage().setTransparency(255);
        } else {
            isActive = false;
            alreadyPicked = false;
            getImage().setTransparency(0);
        }
        
        if ((isTouching(Player.class)) && (Dealer.getGameState() == 3)) {
            velocityY = -15;
            if (isCorrect) {
                correct.stop();
                correct.play();
                Dealer.setAnimState(6);
            } else {
                incorrect.play();
                incorrect.play();
                Dealer.setAnimState(8);
            }
        }
if (velocityY != 0) {
            // if gravity would put card below 220, set velY to 0 & set location to 400
            if (!((getY() + (velocityY / 2) >= 220))) {
                velocityY += 2;
                setLocation(getX(), (getY() + (velocityY / 2)));
                
            } else {
                setLocation(getX(), 220);
                velocityY = 0;
                
                
            }
    }
}       
}
