import greenfoot.*;
import java.util.*;

public class Player extends Actor
{
    public int id;
    public int spawnId;
    public String direction;
    public String directionSave;
    public int fieldCount;
    public int maxFieldCount;
    public int fieldCountSave;
    public int[][] positionNow;
    public boolean inPosition;

    GameBoard myWorld = (GameBoard)getWorld();

    public Player(int id, int spawnId) {
        this.id = id;
        this.spawnId = spawnId;
        fieldCount = 0;
        maxFieldCount = 43;
        inPosition = false;
    }

    public int getId() {
        return id;
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            if (GameBoard.turnInt != id || GameBoard.gamEnd)    // TODO: Max fieldCount or to big
            {
                return;
            }
            if (!Dice.isRolled && !Dice.collisionIsRolled)
            {
                return;
            }
            else if (Dice.collisionIsRolled)
            {
                Dice.collisionIsRolled = false;
            }

            positionNow = new int[][]{
                {getX(), getY()}
            };
            fieldCountSave = fieldCount;
            directionSave = direction;

            if (Dice.isRolled && fieldCount > 0)
            {
                if (maxFieldCount >= (Dice.currentNumber + fieldCount))
                {
                    for (int i = 0; i < Dice.currentNumber; i++)
                    {
                        Move();
                        Greenfoot.delay(15);
                    }
                }
                else
                {
                    getWorld().showText("Would go to far!", 5, 5);
                    Greenfoot.delay(100);
                    Dice.collisionIsRolled = true;
                    return;
                }

                if (maxFieldCount == fieldCount)
                {
                    inPosition = true;
                    ((GameBoard)getWorld()).UpdateFieldCount(this);
                }
            }
            if (fieldCount == 0)
            {
                if (Dice.currentNumber != 6)
                {
                    positionNow = new int[][]{
                        {getX(), getY()}
                    };
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
                }
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
                        case 4:
                            if (id == 0)
                            {
                                ChangeDirectionOfPlayer("east");
                            }
                            break;
                        case 5:
                            if (id == 1)
                            {
                                ChangeDirectionOfPlayer("south");
                            }
                            break;
                        case 6:
                            if (id == 2)
                            {
                                ChangeDirectionOfPlayer("west");
                            }
                            break;
                        case 7:
                            if (id == 3)
                            {
                                ChangeDirectionOfPlayer("north");
                            }
                            break;
                    }
            }
            i++;
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

    public void Collision(boolean team)
    {
        if (team)
        {
            setLocation(positionNow[0][0], positionNow[0][1]);
            fieldCount = fieldCountSave;
            direction = directionSave;
        }
        else
        {
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
            fieldCount = 0;
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