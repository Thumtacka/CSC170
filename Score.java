import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' buttoscorn gets pressed in the environment.
     */
    private static int score;
    
    public static void addScore() {
        score++;
    }
    public void act()
    {
        getImage().setTransparency(0);
        getWorld().showText("Score: " + (score), 50, 50);
    }
}
