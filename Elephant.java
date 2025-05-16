import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Elephant, our Hero
 * 
 * @author Kelton
 * @version April-May 2025
 */
public class Elephant extends Actor
{
    GreenfootSound elephantSound = new GreenfootSound("elephantcub.mp3");
    GreenfootImage[] idleRight = new GreenfootImage[8];
    GreenfootImage[] idleLeft = new GreenfootImage[8];
    
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    
    /**
     * constructor for elephant
     */
    public Elephant() {
        for (int i = 0; i < idleRight.length; i++) {
            idleRight[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            idleRight[i].scale(100, 100);
        }
        
        for (int i = 0; i < idleLeft.length; i++) {
            idleLeft[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            idleLeft[i].mirrorHorizontally();
            idleLeft[i].scale(100, 100);
        }
        
        animationTimer.mark();
        
        //Initial elephant image
        setImage(idleRight[0]);
    }
    
    /**
     *  Animate the elephant
     */
    
    int imageIndex = 0;
    public void animateElephant() {
        if (animationTimer.millisElapsed() < 100) {
            return;
        }
        if (facing.equals("right")) {
            setImage(idleRight[imageIndex]);
            imageIndex = (imageIndex + 1) % idleRight.length;
        } else {
            setImage(idleLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % idleLeft.length;
        }
    }
    
    // base elephant speed of 4
    int speed = 4;
    int maxSpeed = 18;
    
    // speed change timer cooldown
    int cooldownTime = 15;
    int cooldownTimer = 0;
    
    /**
     * moves the elephant when the controlled by the user
     */
    public void act()
    {
        if (MyWorld.gameOver == true) return;
        if (Greenfoot.isKeyDown("left")) {
            move(-speed);
            facing = "left";
        } else if (Greenfoot.isKeyDown("right")) {
            move(speed);
            facing = "right";
        }
        
        if (cooldownTimer > 0) {
            cooldownTimer --;
        }
    
        if (Greenfoot.isKeyDown("a") && cooldownTimer == 0) {
            speed--;
            cooldownTimer = cooldownTime;
        }
        else if (Greenfoot.isKeyDown("d") && cooldownTimer == 0) {
            speed++;
            cooldownTimer = cooldownTime;
        }
        
        /**
         * ruin the fun
         */
        if (speed < 0) speed *= -1; 
        if (speed >= maxSpeed) speed = maxSpeed;
        
        //Remove apple if elephant eats it
        eat();
        
        //Animate the elephant
        animateElephant();
    }
    
    /**
     * Eat the apple and spawn new apple if an apple is eaten
     */
    public void eat() {
        if (isTouching(Apple.class)) {
            removeTouching(Apple.class);
            MyWorld world = (MyWorld) getWorld();
            world.createApple();
            world.increaseScore();
            elephantSound.play();
        }
    }
}
