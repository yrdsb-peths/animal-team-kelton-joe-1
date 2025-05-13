import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Food for our elephant.
 * 
 * @author Kelton
 * @version April-May 2025
 */
public class Apple extends Actor
{
    int speed = 1;
    
    public Apple() {
        setImage("images/apple1.png");
    }
    
    public void act()
    {
        int x = getX();
        int y = getY() + 2;
        setLocation(x, y);
        
        MyWorld world = (MyWorld) getWorld();
        if (getY() >= world.getHeight()) {
            world.gameOver();
            world.removeObject(this);
        }
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
