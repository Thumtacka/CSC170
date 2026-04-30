import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CorrectCard extends Actor
{
    /**
     * Act - do whatever the Cards wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static int suit;
    private static int value;
    private static int[] position = {
        100, 250, 400, 550, 700
    };
    private static int posRand;
    private boolean alreadyPicked = false;
    private int velocityY;
    private static int opacityInt = 0;
    private boolean takeAction;
    static GreenfootSound correct = new GreenfootSound("right.mp3");
    
    public void setUp() {
        suit = Greenfoot.getRandomNumber(Dealer.phase2Check() ? 4: 2);
        value = Greenfoot.getRandomNumber(5);
        setImage(Cards.cardImgs[suit][value]);
    }

    public void goSomewhere() {
        posRand = Greenfoot.getRandomNumber(5);
        setLocation(position[posRand], 220);
        if (isTouching(SelectionCard.class)) {
            getOneIntersectingObject(SelectionCard.class).setLocation(100, 220);
        }
    }
    
    public static int getSuit() {
        return suit;
    }
    public static int getValue() {
        return value;
    }
    
    public static void show() {
        opacityInt = 255;
    }
    public static void hide() {
        opacityInt = 0;
    }
    
    public void act()
    {
        correct.setVolume(45);
        //getWorld().showText(("specialTimer " + (alreadyPicked)), 530, 100);
        getImage().setTransparency(opacityInt);
        if (Dealer.getTimer() == 0) {
            takeAction = false;
        }
        //different states
        switch(Dealer.getAnimState()) {
            case 0:
                hide();
                takeAction = true;
                break;
            case 3:
                if (!alreadyPicked) {
                    setUp();
                    alreadyPicked = true;
                }
                show();
                setLocation(700, 270);
                takeAction = false;
                break;
            case 4:
                if (!takeAction) {
                    hide();
                    if (Dealer.getTimer() < 90) {
                        goSomewhere();
                        takeAction = true;
                        alreadyPicked = false;
                    }
                }
                break;
            case 5:
                if (!takeAction) {
                    show();
                    takeAction = true;
                }
                if (isTouching(Player.class)) {
                    velocityY = -15;
                    correct.stop();
                    correct.play();
                    switch (Score.getScore()) {
                        case 14:
                            Dealer.setAnimState(10);
                            break;
                        case 29:
                            Dealer.setAnimState(13);
                            break;
                        default:
                            Dealer.setAnimState(6);
                            break;
                    }
                }
                break;
            default:
                break;
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
