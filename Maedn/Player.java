import greenfoot.*;
import java.util.*;

public class Player extends Actor
{
    public int id;
    public String direction;
    private int fieldCount;

    GameBoard myWorld = (GameBoard)getWorld();

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            if (GameBoard.turnInt != id || !Dice.isRolled)    // TODO: Max fieldCount or to big
            {
                return;
            }
            if (fieldCount == 0)
            {
                if (Dice.currentNumber == 6)
                {
                    if (id == 0)
                    {
                        setLocation(GameBoard.greenSpawns[3][0], GameBoard.greenSpawns[3][1]);
                    }
                    else if (id == 1)
                    {
                        setLocation(GameBoard.blueSpawns[3][0], GameBoard.blueSpawns[3][1]);
                    }
                    else if (id == 2)
                    {
                        setLocation(GameBoard.orangeSpawns[3][0], GameBoard.orangeSpawns[3][1]);
                    }
                    else if (id == 3)
                    {
                        setLocation(GameBoard.redSpawns[3][0], GameBoard.redSpawns[3][1]);
                    }
                }
            }
            if (Dice.isRolled)
            {
                for (int i = 0; i < Dice.currentNumber; i++)
                {
                    Move();
                }
            }
            ((GameBoard)getWorld()).TurnEnd();
        }
    }
    
    public void Move()
    {
        int i = 0;
        for (int[] checkPoint : GameBoard.checkPoints)
        {
            if (getX() == checkPoint[0] && getY() == checkPoint[1])
            {
                switch (GameBoard.directionCheckpoint[i])
                    {
                        case 0:
                            ChangeDirectionOfPlayer("north");
                            break;
                        case 1:
                            ChangeDirectionOfPlayer("east");
                            break;
                        case 2:
                            ChangeDirectionOfPlayer("south");
                            break;
                        case 3:
                            ChangeDirectionOfPlayer("west");
                            break;
                    }
            }
            i++; // manuell index
        }

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
        String[] directions = { "north", "south", "east", "west" };
        if (ArrayContains(newDirections, directions)) {
            direction = newDirections;
        };
    }

    public boolean ArrayContains(String S, String[] A) {
        for(int i = 0; i < A.length; i++) {
            if(A[i] == S) {
                return true;
            }
        }
        return false;
    }
}