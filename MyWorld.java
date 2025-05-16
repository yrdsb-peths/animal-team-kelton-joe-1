import greenfoot.*;

public class MyWorld extends World {
    public int score = 0;
    Label scoreLabel;
    int level = 1;
    
    static boolean gameOver = false;
    
    SimpleTimer appleTimer;
    
    /**
     * Creates the world
     */
    public MyWorld() {
        // size 600 x 400
        super(600, 400, 1, false);
        
        gameOver = false;
        
        // sets the background
        setBackground(new GreenfootImage("savannah.jpg"));
        
        // creates the elephant
        Elephant elephant = new Elephant();
        addObject(elephant, 300, 300);
        
        // adds the score label
        scoreLabel = new Label(0, 80);
        addObject(scoreLabel, 50, 50);
        
        // creates apples
        appleTimer = new SimpleTimer();
        appleTimer.mark();
        createApple();
    }
    
    /**
     * Function for creating apples
     */
    public void act() {
        // creates apples every few seconds
        if (appleTimer.millisElapsed() > 5000) {
            appleTimer.mark();
            createApple();
        }
        
        if (Greenfoot.isKeyDown("r") && gameOver == true) {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
            
            gameOver = false;
        }
    }
    
    /**
     * End the game and draw 'GameOver'
     */
    public void gameOver() {
        // game over label
        Label gameOverLabel = new Label("Game Over", 100);
        Label restartLabel = new Label("Press r to restart the game", 40);
        addObject(gameOverLabel, 300, 200);
        addObject(restartLabel, 300, 250);
        
        gameOver = true;
    }
    
    /**
     * Increase score
     */
    public void increaseScore() {
        score++;
        scoreLabel.setValue(score);
        
        if (score % 5 == 0) {
            level++;
        }
    }
    
    /**
     * Create a new apple at random location at top of the screen
     */
    public void createApple() {
        // creates a new object
        Apple apple = new Apple();
        apple.setSpeed(level);
        
        // random starting x pos of apple and constant starting y pos
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        
        // adds the object into the world
        addObject(apple, x, y);
    }
}
