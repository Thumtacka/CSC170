import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandomCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomCard extends Actor
{
    /**
     * Act - do whatever the RandomCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static int opacityInt;
    private static boolean isRandomized;
    boolean alreadyPicked = false;
    private static String imgString = "";
    public static void show() {
        opacityInt = 255;
    }
    
    public static void hide() {
        opacityInt = 0;
    }
    
    public static void randomize() {
        isRandomized = true;
    }
    public void act()
    {
        getImage().scale(50, 70);
        getImage().setTransparency(opacityInt);
        
        if (isRandomized && !(alreadyPicked)) {
            //TEMPORARY!!!
            getImage().scale(50, 70);
            setImage("heart-ace.png");
            alreadyPicked = true;
        }
    }
}
