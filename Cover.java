import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cover extends Actor
{
    /**
     * Act - do whatever the Cover wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean alreadyChanged;
    public void act()
    {
        if (Dealer.phase2Check() && !alreadyChanged) {
            setImage("bgmask2.png");
            alreadyChanged = true;
        }
        if (Dealer.getAnimState() == 2) {
            getImage().setTransparency(255);
        }
        if ((Dealer.getAnimState() == 4) && (Dealer.getTimer() < 90)) {
            getImage().setTransparency(0);
        }
    }
}
