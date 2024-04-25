import greenfoot.*;

public class GameBoard extends World
{
    public static int cellSize = 75;
    public static int boardSizeX = 11;
    public static int boardSizeY = 11;
    private Player[] players;
    private GreenfootImage[] playerImages;
    private int currentPlayerIndex;
    private int[] positions;
    public static int turnInt;
    private int playerCount = 2;
    public static boolean gameStart = false;
    public static String playerCountInput;

    private GreenfootImage bgImage;
    private GreenfootImage normalImage;
    private GreenfootImage redImage;
    private GreenfootImage orangeImage;
    private GreenfootImage greenImage;
    private GreenfootImage blueImage;
    
    private GreenfootImage playerRed;
    private GreenfootImage playerOrange;
    private GreenfootImage playerGreen;
    private GreenfootImage playerBlue;
    private Dice dice;
    
    private int[][] redPositions;
    private int[][] orangePositions;
    private int[][] greenPositions;
    private int[][] bluePositions;
    private int[][] normalPositions;

    public static int[][] dicePositions = { {2, 2}, {8, 2}, {8, 8}, {2, 8}};

    public static int[][] redSpawns = { {10, 9}, {9, 10}, {10, 10}, {10, 6}};
    public static int[][] orangeSpawns = { {0, 10}, {0, 9}, {1, 10}, {4, 10}};
    public static int[][] greenSpawns = { {0, 0}, {1, 0}, {0, 1}, {0, 4}};
    public static int[][] blueSpawns = { {9, 0}, {10, 0}, {10, 1}, {6, 0}};

    
    public static int[][] checkPoints = { {4, 0}, {6, 0}, {6, 4}, {10, 4}, {10, 6}, {6, 6}, {6, 10}, {4, 10}, {4, 6}, {0, 6}, {0, 4}, {4, 4}};
    // 0 = north, 1 = east, 2 = south, 3 = west
    public static int[] directionCheckpoint = {1, 2, 1, 2, 3, 2, 3, 0, 3, 0, 1, 0};

    public GameBoard()
    {
        super(boardSizeX, boardSizeY, cellSize);
        bgImage = new GreenfootImage("bgImage.png");
        normalImage = new GreenfootImage("normalImage.png");
        redImage = new GreenfootImage("redImage.png");
        orangeImage = new GreenfootImage("orangeImage.png");
        greenImage = new GreenfootImage("greenImage.png");
        blueImage = new GreenfootImage("blueImage.png");
        
        playerImages = new GreenfootImage[4];
        playerRed = new GreenfootImage("playerRed.png");
        playerImages[3] = playerRed;
        playerOrange = new GreenfootImage("playerOrange.png");
        playerImages[2] = playerOrange;
        playerGreen = new GreenfootImage("playerGreen.png");
        playerImages[0] = playerGreen;
        playerBlue = new GreenfootImage("playerBlue.png");
        playerImages[1] = playerBlue;
        
        SetSpecialPositions();
        Greenfoot.delay(1);
        PrepareBoard();

        dice = new Dice();
        dice.isRolled = false;

        turnInt = 0;
        gameStart = false;
    }
    
    private void SetSpecialPositions()
    {
        redPositions = new int[][]{
                {10, 9}, {9, 10}, {10, 10}, {10, 6}, {9, 5}, {8, 5}, {7, 5}
            };
        orangePositions = new int[][] {
                {0, 10}, {0, 9}, {1, 10}, {4, 10}, {5, 9}, {5, 8}, {5, 7}
            };
        greenPositions = new int[][] {
                {0, 0}, {1, 0}, {0, 1}, {0, 4}, {1, 5}, {2, 5}, {3, 5},
            };
        bluePositions = new int[][] {
                {9, 0}, {10, 0}, {10, 1}, {6, 0}, {5, 1}, {5, 2}, {5, 3} 
            };
        normalPositions = new int[][] {
                {1, 4}, {2, 4}, {3, 4}, {4, 4}, {4, 3}, {4, 2}, {4, 1}, {4, 0}, {5, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {7, 4}, {8, 4}, {9, 4}, {10, 4}, {10, 5}, {9, 6}, {8, 6}, {7, 6}, {6, 6}, {6, 7}, {6, 8}, {6, 9}, {6, 10}, {5, 10}, {4, 9}, {4, 8}, {4, 7}, {4, 6}, {3, 6}, {2, 6}, {1, 6}, {0, 6}, { 0, 5} 
            };
    }

    private void PrepareBoard()
    {
        // Create the game board cells
        for (int i = 0; i < boardSizeX; i++) {
            for (int j = 0; j < boardSizeY; j++){
                Cell cell = new Cell();
                if (IsSpecialPosition(i, j) == "red")
                {
                    cell.setImage(redImage);
                }
                else if(IsSpecialPosition(i, j) == "orange") 
                {
                    cell.setImage(orangeImage);
                }
                else if (IsSpecialPosition(i, j) == "green")
                {
                    cell.setImage(greenImage);
                }
                else if(IsSpecialPosition(i, j) == "blue")
                {
                    cell.setImage(blueImage);
                }
                else if(IsSpecialPosition(i, j) == "normal")
                {
                    cell.setImage(normalImage);
                }
                else
                {
                    cell.setImage(bgImage);
                    //cell.setColor(Color.RED);
                }
                addObject(cell, i, j);
            }
        }
    }

    private String IsSpecialPosition(int x, int y)
    {
        for (int[] position : redPositions) {
            if (x == position[0] && y == position[1]) {
                return "red";
            }
        }
        for (int[] position : orangePositions) {
            if (x == position[0] && y == position[1]) {
                return "orange";
            }
        }
        for (int[] position : greenPositions) {
            if (x == position[0] && y == position[1]) {
                return "green";
            }
        }
        for (int[] position : bluePositions) {
            if (x == position[0] && y == position[1]) {
                return "blue";
            }
        }
        for (int[] position : normalPositions) {
            if (x == position[0] && y == position[1]) {
                return "normal";
            }
        }

        return "bg";
    }

    private void PreparePlayers()
    {
        players = new Player[playerCount * 3];
        int h = 0;
        for (int i = 0; i < playerCount; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                players[h] = new Player(i, j);
                players[h].setImage(playerImages[i]);
                addObject(players[h], 75, 75);

                if (i == 0)
                {
                    players[h].setLocation(greenSpawns[j][0], greenSpawns[j][1]);
                }
                else if (i == 1)
                {
                    players[h].setLocation(blueSpawns[j][0], blueSpawns[j][1]);
                }
                else if (i == 2)
                {
                    players[h].setLocation(redSpawns[j][0], redSpawns[j][1]);
                }
                else if (i == 3)
                {
                    players[h].setLocation(orangeSpawns[j][0], orangeSpawns[j][1]);
                }
                h++;
            }
        }
        dice.setImage(new GreenfootImage("Dice0.png"));
        addObject(dice, 75, 75);

        TurnStart();
    }

    private void TurnStart()
    {
        showText("P" + (turnInt + 1) + "'s turn", 5, 5);
        dice.setLocation(dicePositions[turnInt][0], dicePositions[turnInt][1]);
        dice.setImage(new GreenfootImage("Dice0.png"));
    }
    
    public void TurnEnd(Player player)
    {
        for (int i = 0; i < players.length; i++)
        {
            if (players[i].getX() == player.getX() && players[i].getY() == player.getY())
            {
                if (players[i] != player)
                {
                    if (players[i].id == player.id) // own team
                    {
                        dice.collisionIsRolled = true;
                        player.Collision(true);
                        return;

                    }
                    else
                    {
                        players[i].Collision(false);
                    }
                }
            }
        }
        if (turnInt < playerCount - 1)
        {
            turnInt++;
        }
        else
        {
            turnInt = 0;
        }
        Dice.isRolled = false;
        TurnStart();
    }

    public void act() {
        while (!gameStart)
        {
            playerCountInput = Greenfoot.ask("How many players do you want to play with? (2-4)");
            switch (playerCountInput) {
                case "2":
                    gameStart = true;
                    playerCount = 2;
                    break;
                case "3":
                    gameStart = true;
                    playerCount = 3;
                    break;
                case "4":
                    gameStart = true;
                    playerCount = 4;
                    break;
                default:
                    showText("Invalid input", 5, 5);
                    break;
            }
            PreparePlayers();
        }
        if (gameStart)
        {
            if (playerCount == 2)
            {
                showText("P1", 1, 1);
                showText("P2", 9, 1);
            }
            else if (playerCount == 3)
            {
                showText("P1", 1, 1);
                showText("P2", 9, 1);
                showText("P3", 9, 9);
            }
            else if (playerCount == 4)
            {
                showText("P1", 1, 1);
                showText("P2", 9, 1);
                showText("P3", 9, 9);
                showText("P4", 1, 9);
            }
        }
    }
}