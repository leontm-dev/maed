import greenfoot.*;


public class Dice extends Actor
{
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
            int spinDice = Greenfoot.getRandomNumber(4) + 2;
            for (int i = 0; i < spinDice; i++)
            {
                int number = Greenfoot.getRandomNumber(6) + 1;
                currentNumber = number;
                setImage(new GreenfootImage("Dice" + number + ".png"));
                Greenfoot.delay(50);
            }
            isRolled = true;
        }
    }
}
