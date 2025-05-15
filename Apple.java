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
    
    /**
     * sets the image of the apple
     */
    public Apple() {
        setImage("images/apple1.png");
    }
    
    /**
     * move function for the apple
     */
    public void act()
    {
        int x = getX();
        int y = getY() + 2;
        setLocation(x, y);
        
        MyWorld world = (MyWorld) getWorld();
        if (getY() >= world.getHeight()) {
            world.gameOver();
            world.removeObject(this);
        } else if (world.gameOver) {
            world.removeObject(this);
        }
    }
    
    /**
     * changes the speed of the apple
     * 
     * @param speed - the speed of the apple
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
