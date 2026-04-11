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
    
    public void act()
    {
        if (isTouching(Player.class)) {
            velocityY = -15;
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
