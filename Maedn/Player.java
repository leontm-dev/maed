import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    public int id;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String direction;
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            if (GameBoard.turnInt != id)    // Isn't your turn
            {
                return;
            }
            
            getWorld().showText("Clicked", getX(), getY());
            
            // Clicked on this player figure
            // Move this figure
        }
    }
    public void Move()
    {
        if (direction == "north") {
            setLocation(getX(), getY() - 1);
        } else if (direction == "south") {
            setLocation(getX(), getY() + 1);
        } else if (direction == "east") {
            setLocation(getX() + 1, getY());
        } else if (direction == "west") {
            setLocation(getX() - 1, getY());
        }
    }
    public void ChangeDirectionOfPlayer(String newDirections) {
        String[] static directions = { "north", "south", "east", "west" };
        if (directions.includes(newDirections)) {
            direction = newDirections;
        };
    }
}