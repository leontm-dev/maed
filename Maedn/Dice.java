import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the dice class.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dice extends Actor
{
    /**
     * Act - do whatever the Dice wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int currentNumber = 1;
    public boolean isRolled = false;
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            Roll();
        }
    }
    public void Roll()
    {
        if (!isRolled) {
            int spinDice = Greenfoot.getRandomNumber(3) + 1;
            for (int i = 0; i < spinDice; i++)
            {
                int number = Greenfoot.getRandomNumber(6) + 1;
                // setImage(new GreenfootImage("dice" + number + ".png"));
                currentNumber = number;
                showText(currentNumber, getX(), getY());
                Greenfoot.delay(0.5);
            }
            isRolled = true;
        }
    }
}
