import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectionCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectionCard extends Actor
{
    public SelectionCard(int id) {
        this.id = id;
    }
    /**
     * Act - do whatever the SelectionCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int velocityY;
    private boolean isActive;
    private boolean alreadyPicked = false;
    private boolean isCorrect;
    private static int suit;
    private static int value;
    private int id;
    private static int homeX;
    static GreenfootSound incorrect = new GreenfootSound("wrong.mp3");
    
    public void pickRandom() {
        suit = Greenfoot.getRandomNumber(Dealer.phase2Check() ? 4: 2);
        value = Greenfoot.getRandomNumber(5);
        if ((suit == CorrectCard.getSuit()) && (value == CorrectCard.getValue())) {
            suit--;
        }
        setImage(Cards.cardImgs[Math.abs(suit)][value]);
    }
    
    public void goHome() {
        setLocation(homeX, 220);
    }
    
    public void act()
    {
        incorrect.setVolume(45);
        //set homeX for card based on id
        switch (id) {
            case 1: homeX = 250; break;
            case 2: homeX = 400; break;
            case 3: homeX = 550; break;
            case 4: homeX = 700; break;
        }
        //make sure they all go home
        if (Dealer.getAnimState() == 0) {
            goHome();
        }
        //selection phase
        if (Dealer.getAnimState() >= 5) {
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
        //check if touching player
        if ((isTouching(Player.class)) && (Dealer.getAnimState() == 5)) {
            velocityY = -15;
                incorrect.play();
                incorrect.play();
                Dealer.setAnimState(8);
        }
        //taken from Player
        if (velocityY != 0) {
            // if gravity would put card below 220, set velY to 0 & set location to 220
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
