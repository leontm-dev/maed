import greenfoot.*;

public class Dice extends Actor
{
    public static int currentNumber = 1;
    public static boolean isRolled = false;
    public static boolean collisionIsRolled = false;

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
            //int spinDice = Greenfoot.getRandomNumber(4) + 2;
            int spinDice = 1;   // For testing to don't have to wait a decade each time
            for (int i = 0; i < spinDice; i++)
            {
                int number = Greenfoot.getRandomNumber(6) + 1;
                currentNumber = number;
                setImage(new GreenfootImage("Dice" + number + ".png"));
                Greenfoot.delay(25);
            }
            isRolled = true;
        }
    }
}
