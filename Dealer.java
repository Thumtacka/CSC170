import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class dealer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dealer extends Actor
{
    /**
     * Act - do whatever the dealer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //variables
       GreenfootSound music = new GreenfootSound("minigameloop.mp3");
       GreenfootSound show = new GreenfootSound("summonsound.wav");
       GreenfootSound tick = new GreenfootSound("WarTimer.mp3");
       GreenfootSound gameOver = new GreenfootSound("gameoveryeah.mp3");
       // v 0 for initial start, 3 for guessing period. oops.
       private static int gameState = 0;
       private static int animState = 0;
       int squash = 0;
       int timer = 180;
       int specialTimer;
       private static boolean takeAction = false;
       boolean keepCounting = true;
       boolean keepCountingSpecial = false;
       boolean doMusic = true;
       //methods
       public static int getGameState() {
           return gameState;
       }
       public static void setAnimState(int x) {
           animState = x;
           takeAction = true;
       }
       
    public void act()
    {
        getImage().scale(100, 100);
        // v debug
        //getWorld().showText(("Timer: " + (timer)), 530, 50);
        //getWorld().showText(("specialTimer " + (specialTimer)), 530, 100);
        //getWorld().showText(("animState: " + (animState)), 530, 150);
        if (doMusic) {
            music.playLoop();
        }
        //timers
        if ((timer != 0) && keepCounting) {
            timer--;
        }
        if (keepCountingSpecial) {
            specialTimer--;
        }
        //increment behavior state when timer finishes
        if (timer == 0) {
            animState++;
            timer = 60;
            takeAction = true;
        }
        // all the actions. coulda done in a switch statement technically but it only like when I do if-else statements >:(
        if ((animState == 0) && takeAction) {
            setImage("dealerN-1.png");
            timer = 180;
            Player.enableLoco();
            takeAction = false;
        }
        //anticipation
        if ((animState == 2) && takeAction) {
            setImage("dealerN-2.png");
            squash = 20;
            RandomCard.randomize();
            takeAction = false;
        }
        //show random card
        if ((animState == 3) && takeAction) {
            setImage("dealerN-3.png");
            show.play();
            RandomCard.show();
            squash = -20;
            takeAction = false;
            timer = 120;
        }
        //grace period
        if ((animState == 4) && takeAction) {
            Player.disableLoco();
            RandomCard.hide();
            setImage("dealerN-1.png");
            squash = 14;
            getWorld().showText("Don't forget it!", 400, 250);
            takeAction = false;
            timer = 180;
        }
        //urge player to select card
        if ((animState == 5)) {
            if (takeAction) {
            keepCounting = false;
            gameState = 3;
            getWorld().showText("Now, which one was it?", 400, 250);
            specialTimer = 600;
            Player.enableLoco();
            takeAction = false;
            }
        }
        //behavioral states for correct answers
        if ((animState == 6) && takeAction) {
            Player.disableLoco();
            keepCounting = true;
            setImage("dealerN-W.png");
            getWorld().showText("", 400, 250);
            Score.addScore();
            takeAction = false;
            timer = 120;
        }
        if ((animState == 7) && takeAction) {
            Player.enableLoco();
            setImage("dealerN-1.png");
            animState = 0;
            gameState = 0;
            takeAction = false;
        }
        //behavioral states for incorrect answers
        if ((animState == 8) && takeAction) {
            Player.disableLoco();
            setImage("dealerN-L.png");
            getWorld().showText("", 400, 250);
            keepCounting = true;
            timer = 120;
            takeAction = false;
        }
        if ((animState == 9) && takeAction) {
            doMusic = false;
            music.stop();
            gameOver.play();
            getWorld().showText("GAME OVER", 400, 250);
            keepCounting = false;
            takeAction = false;
        }
        //squash n stretch
        if (squash != 0) {
            getImage().scale((getImage().getWidth() + squash), (getImage().getHeight() - squash));
            if (squash < 0) {
                squash /= 2;
                setLocation(getX(), (417 + (squash)));
            } else {
                squash /= 2;
                setLocation(getX(), (417 + (squash)));
            }
        }
        //special timer for decision phase. might make it shorter over time
        if (animState == 5) {
            if ((specialTimer != 0)) {
                specialTimer--;
                getWorld().showText("" + (specialTimer / 60), 400, 250);
                if ((specialTimer % 60 == 0) && ((specialTimer / 60) <= 5)) {
                    tick.stop();
                    tick.play();
                }
            } else {
                SelectionCard.incorrect.play();
                setAnimState(8);
            }
        } else {
            specialTimer = 0;
        }
    }
}
