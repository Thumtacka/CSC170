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
        //sounds
       private GreenfootSound music = new GreenfootSound("minigame.mp3");
       private GreenfootSound show = new GreenfootSound("showcard.mp3");
       private GreenfootSound tick = new GreenfootSound("WarTimer.mp3");
       private GreenfootSound gameOver = new GreenfootSound("gameoveryeah.mp3");
       private GreenfootSound angerWhistle = new GreenfootSound("angry.mp3");
       private GreenfootSound musicPhase2 = new GreenfootSound("thousandmarch.mp3");
       private GreenfootSound youWin = new GreenfootSound("win.mp3");
       // v used for pretty much all game logic
       private static int animState = 0;
       // v other misc things
       private int squash = 0;
       private static int timer = 180;
       private int specialTimer;
       private static int speedUpVal;
       private static boolean takeAction = false;
       private static boolean keepCounting = true;
       private boolean keepCountingSpecial = false;
       private static boolean doMusic = false;
       private static boolean isPhase2 = false;
       //methods
       
       public static int getAnimState() {
           return animState;
       }
       
       public static void setAnimState(int x) {
           animState = x;
           takeAction = true;
       }
       
       public static int getSpeedUp() {
           return speedUpVal;
       }
       public static void incrementSpeedUp() {
           if (speedUpVal <= 5) {
               speedUpVal++;
           }
       }
       public static void setSpeedUp(int x) {
           speedUpVal = x;
       }
       
       public static int getTimer() {
           return timer;
       }
       
       public static boolean phase2Check() {
           return isPhase2;
       }
       
       public static void stopCounting() {
           keepCounting = false;
       }
    
       public static void startCounting() {
           keepCounting = true;
       }
       
       public static void startMusic() {
           doMusic = true;
       }
       public static void stopMusic() {
           doMusic = false;
       }
       
    public void act()
    {
        show.setVolume(60);
        music.setVolume(50);
        musicPhase2.setVolume(50);
        tick.setVolume(60);
        gameOver.setVolume(50);
        getImage().scale(100, 100);
        // v debug
        //getWorld().showText(("Timer: " + (speedUpVal)), 530, 50);
        //getWorld().showText(("specialTimer " + (specialTimer)), 530, 100);
        //getWorld().showText(("animState: " + (animState)), 530, 150);
        if (doMusic) {
            if (!isPhase2) {
                music.playLoop();
            } else {
                musicPhase2.playLoop();
            }
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
        switch (animState) {
            case 0:
                //preparation
                if (takeAction) {
                setImage("dealerN-1.png");
                timer = 180;
                Player.enableLoco();
                takeAction = false;
            }
                break;
            case 2:
                //anticipation
                if (takeAction) {
                    setImage("dealerN-2.png");
                    squash = 20;
                    takeAction = false;
                }
                break;
            case 3:
                //show correct card
                if (takeAction) {
                    setImage("dealerN-3.png");
                    show.play();
                    squash = -20;
                    timer = 120 - (10 * speedUpVal);
                    takeAction = false;
                }
                break;
            case 4:
                //hide correct card
                if (takeAction) {
                    Player.disableLoco();
                    setImage("dealerN-1.png");
                    squash = 14;
                    getWorld().showText("Don't forget it!", 400, 250);
                    takeAction = false;
                    timer = 180;
                }
                break;
            case 5:
                //selection phase
                CorrectCard.show();
                if (takeAction) {
                    keepCounting = false;
                    specialTimer = 600 - (speedUpVal * 60);
                    Player.enableLoco();
                    takeAction = false;
                }
                break;
            case 6:
                //successful guess part1
                if (takeAction) {
                    Player.disableLoco();
                    keepCounting = true;
                    setImage("dealerN-W.png");
                    getWorld().showText("", 400, 250);
                    Score.addScore();
                    takeAction = false;
                    timer = 130 - (speedUpVal * 10 + 10);
                }
                break;
            case 7:
                //successful guess part2, reset everything
                if (takeAction) {
                    Player.enableLoco();
                    setImage("dealerN-1.png");
                    animState = 0;
                    
                    takeAction = false;
                }
                break;
            case 8:
                //unsuccessful guess part1
                if (takeAction) {
                    Player.disableLoco();
                    setImage("dealerN-L.png");
                    getWorld().showText("", 400, 250);
                    keepCounting = true;
                    timer = 120;
                    takeAction = false;
                }
                break;
            case 9:
                //unsuccessful guess part2, game over
                if (takeAction) {
                    doMusic = false;
                    music.stop();
                    musicPhase2.stop();
                    gameOver.play();
                    getWorld().showText("GAME OVER", 400, 250);
                    keepCounting = false;
                    takeAction = false;
                }
                break;
            case 10:
                if (takeAction) {
                    Player.disableLoco();
                    keepCounting = true;
                    setImage("dealerN-W.png");
                    getWorld().showText("", 400, 250);
                    Score.addScore();
                    NewBg.activate();
                    music.stop();
                    musicPhase2.stop();
                    doMusic = false;
                    takeAction = false;
                    timer = 140;
                }
                break;
            case 11:
                if (takeAction) {
                    Player.enableLoco();
                    setImage("dealerN-A2.png");
                    angerWhistle.play();
                    timer = 220;
                    takeAction = false;
                }
                if (timer % 5 == 0 && squash < 20) {
                    squash += 5;
                }
                break;
            case 12:
                if (takeAction) {
                    Player.enableLoco();
                    setImage("dealerN-1.png");
                    isPhase2 = true;
                    doMusic = true;
                    speedUpVal = 6;
                    animState = 0;
                    takeAction = false;
                }
                break;
            case 13:
                if (takeAction) {
                    Player.disableLoco();
                    keepCounting = true;
                    setImage("dealerN-W.png");
                    getWorld().showText("", 400, 250);
                    Score.addScore();
                    music.stop();
                    musicPhase2.stop();
                    doMusic = false;
                    takeAction = false;
                    timer = 140;
                }
            case 14:
                if (takeAction) {
                    doMusic = false;
                    music.stop();
                    musicPhase2.stop();
                    youWin.play();
                    getWorld().showText("YOU WON!", 400, 250);
                    keepCounting = false;
                    takeAction = false;
                }
            default:
                break;
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
