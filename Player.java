import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class guy2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the guy2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int velocityX = 0;
     int velocityY = 0;
     int squash = 0;
     boolean notJumped = true;
     private static boolean playerLoco = true;
     boolean facingRight = true;
     
     GreenfootSound jump = new GreenfootSound("sfx_jump.mp3");
     GreenfootSound hitCard = new GreenfootSound("mortslap.mp3");
         public static void disableLoco() {
             playerLoco = false;
         }
         public static void enableLoco() {
             playerLoco = true;
         }
    public void act()
    {
        getImage().scale(100, 100);
        
        // all loco, yet to be toggle-able but here's the groundwork
        if (playerLoco) {
        // jump, but check if player is airborne
        if ((Greenfoot.isKeyDown("W") || Greenfoot.isKeyDown("Space")) && notJumped) {
            jump.stop();
            jump.play();
            velocityY = -31;
           squash = -26;
        }
        //left n right
        if ((Greenfoot.isKeyDown("A") || Greenfoot.isKeyDown("left"))) {
           if (velocityX > -20) {
               setImage("player-2L.png");
               velocityX -= 2;
               facingRight = false;
           }
           
        }
        if ((Greenfoot.isKeyDown("D") || Greenfoot.isKeyDown("right"))) {
           if (velocityX < 20) {
               setImage("player-2.png");
               velocityX += 2;
               facingRight = true;
           }
           
        }
        // vestigial down input
        if ((Greenfoot.isKeyDown("S") || Greenfoot.isKeyDown("down")) && !((Greenfoot.isKeyDown("W") || Greenfoot.isKeyDown("Space")) && notJumped) && notJumped) {
            squash = 60;
            velocityX /= 2;
        }    
    }
    // create friction
        if (velocityX != 0) {
            
            if ((Math.abs(velocityX) == 1)) {
                velocityX = 0;
                
                setImage((facingRight) ? "player-1.png" : "player-1L.png");
            } else {
                if (velocityX < 0) {
                    velocityX++;
                } else {
                    velocityX--;
                }
                setLocation((getX() + (velocityX / 2)), getY());
            }
        }
    // create gravity
        if (velocityY != 0) {
            // if gravity would put player below 400, set velY to 0 & set location to 400
            if (!((getY() + (velocityY / 2) >= 425))) {
                
                velocityY += 2;
                setLocation(getX(), (getY() + (velocityY / 2)));
                notJumped = false;
                if (velocityY <= 0) {
                setImage((facingRight) ? "player-3.png" : "player-3L.png");
            } else {
                setImage((facingRight) ? "player-4.png" : "player-4L.png");
            }
            } else {
                setLocation(getX(), 425);
                velocityY = 0;
                notJumped = true;
                setImage((facingRight) ? "player-1.png" : "player-1L.png");
                squash = 26;
            }
        }
    // squash n stretch, visual feedback 
        if (squash != 0) {
            getImage().scale((getImage().getWidth() + squash), (getImage().getHeight() - squash));
            if (squash < 0) {
                squash++;
            } else {
                squash /= 2;
                setLocation(getX(), (425 + (squash)));
            }
        }
    // if touching a card, reverse Y velocity
    if (isTouching(SelectionCard.class) && (Dealer.getGameState() == 3)) {
        hitCard.stop();
        hitCard.play();
        velocityY *= -1;
    }
    }
}
