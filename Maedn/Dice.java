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
    public static int currentNumber = 1;
    public static boolean isRolled = false;
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
            int spinDice = 3;
            for (int i = 0; i < spinDice; i++)
            {
                int number = Greenfoot.getRandomNumber(6) + 1;
                // this.setImage(new GreenfootImage("dice" + number + ".png"));
                currentNumber = number;
                System.out.println(""+currentNumber);
                showText("" + currentNumber, getX(), getY());
                Greenfoot.delay(500);
            }
            isRolled = true;
        }
    }
    private void showText(String text, int x, int y)
    {
        getWorld().showText(text, x, y);
    }
}
