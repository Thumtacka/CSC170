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
    private static boolean perFrameFix = true;
    //private static String scoreString = "";
    //private GreenfootImage scoreText = new GreenfootImage(60, 30);
    //Font theFont = new Font("Dialog", false, false, 40);    
    public static void addScore() {
        score++;
        perFrameFix = true;
        }
    public static void setScore(int x) {
        score = x;
    }
    public static int getScore() {
        return score;
    }
    public void act()
    {
        setImage(new GreenfootImage("Score: " + score, 40, Color.WHITE, null, Color.BLACK));
        if ((score % 3 == 0) && perFrameFix) {
            Dealer.incrementSpeedUp();
            perFrameFix = false;
            }
        if (Dealer.getAnimState() == 9 || Dealer.getAnimState() == 14) {
            setLocation(400, 320);
        }
    }
}
