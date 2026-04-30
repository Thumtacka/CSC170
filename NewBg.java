import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NewBg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NewBg extends Actor
{
    /**
     * Act - do whatever the NewBg wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int opacityInt = 0;
    private static boolean isActive = false;
    
    public static void activate() {
        isActive = true;
    }
    public void act()
    {
        getImage().setTransparency(opacityInt);
        if (isActive) {
            if (opacityInt < 255) {
                opacityInt++;
            }
        }
    }
}
