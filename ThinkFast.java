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
        showText("temp :D (WASD or Space & Arrow Keys)", 400, 300);
    
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Dealer theDealer = new Dealer();
        addObject(theDealer,700,417);
        Player thePlayer = new Player();
        addObject(thePlayer,100,425);

        RandomCard randomCard = new RandomCard();
        addObject(randomCard,700,300);
        SelectionCard selectionCard = new SelectionCard();
        addObject(selectionCard,250,220);
        SelectionCard selectionCard2 = new SelectionCard();
        addObject(selectionCard2,400,220);
        SelectionCard selectionCard3 = new SelectionCard();
        addObject(selectionCard3,550,220);
        SelectionCard selectionCard4 = new SelectionCard();
        addObject(selectionCard4,700,220);
        SelectionCard selectionCard5 = new SelectionCard();
        addObject(selectionCard5,100,220);
        Score score = new Score();
        addObject(score,61,30);
    }
}
