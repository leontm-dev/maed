import greenfoot.*;
import java.util.*;

public class Player extends Actor
{
    public int id;
    public int spawnId;
    public String direction;
    public int fieldCount;
    public boolean inGame;
    public int[][] positionNow;

    GameBoard myWorld = (GameBoard)getWorld();

    public Player(int id) {
        this.id = id;
        fieldCount = 0;
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
            if (Dice.isRolled && fieldCount > 0)
            {
                positionNow = new int[][]{
                    {getX(), getY()}
                };
                for (int i = 0; i < Dice.currentNumber; i++)
                {
                    Move();
                    Greenfoot.delay(25);
                }
            }
            if (fieldCount == 0)
            {
                //if (Dice.currentNumber != 6)
                //{
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
                        setLocation(GameBoard.redSpawns[3][0], GameBoard.redSpawns[3][1]);
                    }
                    else if (id == 3)
                    {
                        setLocation(GameBoard.orangeSpawns[3][0], GameBoard.orangeSpawns[3][1]);
                    }
                    fieldCount++;
                    inGame = true;

                    positionNow = new int[][]{
                        {getX(), getY()}
                    };
                //}
            }
            ((GameBoard)getWorld()).TurnEnd(this);
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
        fieldCount++;
    }

    public void Collision()
    {
        fieldCount = 0;
        inGame = false;

        if (id == 0)
        {
            setLocation(GameBoard.greenSpawns[spawnId][0], GameBoard.greenSpawns[spawnId][1]);
        }
        else if (id == 1)
        {
            setLocation(GameBoard.blueSpawns[spawnId][0], GameBoard.blueSpawns[spawnId][1]);
        }
        else if (id == 2)
        {
            setLocation(GameBoard.redSpawns[spawnId][0], GameBoard.redSpawns[spawnId][1]);
        }
        else if (id == 3)
        {
            setLocation(GameBoard.orangeSpawns[spawnId][0], GameBoard.orangeSpawns[spawnId][1]);
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