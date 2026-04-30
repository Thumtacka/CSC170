import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThinkFast extends World
{
    
    /**
     * Constructor for objects of class ThinkFast.
     * 
     */
    public ThinkFast()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        //setBackground("patternbghalloween.png");
        //showText("temp :D (WASD or Space & Arrow Keys)", 400, 300);
    
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        NewBg newBg = new NewBg();
        addObject(newBg,400,300);
        
        Dealer theDealer = new Dealer();
        addObject(theDealer,700,417);

        SelectionCard selectionCard = new SelectionCard(1);
        addObject(selectionCard,250,220);

        SelectionCard selectionCard2 = new SelectionCard(2);
        addObject(selectionCard2,400,220);

        SelectionCard selectionCard3 = new SelectionCard(3);
        addObject(selectionCard3,550,220);

        SelectionCard selectionCard4 = new SelectionCard(4);
        addObject(selectionCard4,700,220);

        Cover cover = new Cover();
        addObject(cover,400,300);

        Player thePlayer = new Player();
        addObject(thePlayer,100,425);

        CorrectCard correctCard = new CorrectCard();
        addObject(correctCard,100,220);

        Score score = new Score();
        addObject(score,70,30);

        Jumpscare jumpscare = new Jumpscare();
        addObject(jumpscare,400,300);
        cover.setLocation(400,300);
    }

    public void started() {
        Score.setScore(0);
        Dealer.setAnimState(0);
        Dealer.setSpeedUp(0);
        Dealer.stopCounting();
        Dealer.stopMusic();
        Jumpscare.init();
        Player.disableLoco();
    }
}
