import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cards here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cards extends Actor
{
    /**
     * Act - do whatever the Cards wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static GreenfootImage[][] cardImgs = {
        {new GreenfootImage("heart-ace.png"), new GreenfootImage("heart-2.png"), new GreenfootImage("heart-3.png"), new GreenfootImage("heart-4.png"), new GreenfootImage("heart-5.png")},
        {new GreenfootImage("spade-ace.png"), new GreenfootImage("spade-2.png"), new GreenfootImage("spade-3.png"), new GreenfootImage("spade-4.png"), new GreenfootImage("spade-5.png")},
        {new GreenfootImage("diamond-ace.png"), new GreenfootImage("diamond-2.png"), new GreenfootImage("diamond-3.png"), new GreenfootImage("diamond-4.png"), new GreenfootImage("diamond-5.png")},
        {new GreenfootImage("club-ace.png"), new GreenfootImage("club-2.png"), new GreenfootImage("club-3.png"), new GreenfootImage("club-4.png"), new GreenfootImage("club-5.png")},
    };
}
