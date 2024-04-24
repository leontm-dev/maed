import greenfoot.*;


// for dicing
// Greenfoot.getRandomNumber


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
    public static boolean gameStart = true;

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
    private Dice dice = new Dice();
    
    private int[][] redPositions;
    private int[][] orangePositions;
    private int[][] greenPositions;
    private int[][] bluePositions;
    private int[][] normalPositions;

    private static int[][] dicePositions = { {2, 2}, {8, 2}, {8, 8}, {2, 8}};

    private static int[][] redSpawns = { {10, 9}, {9, 10}, {10, 10}, {10, 6}};
    private static int[][] orangeSpawns = { {0, 10}, {0, 9}, {1, 10}, {4, 10}};
    private static int[][] greenSpawns = { {0, 0}, {1, 0}, {0, 1}, {0, 4}};
    private static int[][] blueSpawns = { {9, 0}, {10, 0}, {10, 1}, {6, 0}};

    
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
        PreparePlayers();
        
        turnInt = 0;
        TurnStart();
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

    private void PreparePlayers() {
        playerCount = 4;
        players = new Player[playerCount];
        for (int i = 0; i < players.length; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                players[i] = new Player(i);
                players[i].id = i;
                players[i].setImage(playerImages[i]);
                dice.setImage(new GreenfootImage("Dice0.png"));
                addObject(dice, 75, 75);
                addObject(players[i], 75, 75);

                if (i == 0)
                {
                    players[i].setLocation(greenSpawns[j][0], greenSpawns[j][1]);
                }
                else if (i == 1)
                {
                    players[i].setLocation(blueSpawns[j][0], blueSpawns[j][1]);
                }
                else if (i == 2)
                {
                    players[i].setLocation(orangeSpawns[j][0], orangeSpawns[j][1]);
                }
                else if (i == 3)
                {
                    players[i].setLocation(redSpawns[j][0], redSpawns[j][1]);
                }
            }
        }
    }

    private void TurnStart()
    {
        showText("Turn: Player " + (turnInt + 1), 5, 5);
        dice.setLocation(dicePositions[turnInt][0], dicePositions[turnInt][1]);
        dice.setImage(new GreenfootImage("Dice0.png"));
    }
    
    public void TurnEnd()
    {
        if (turnInt < players.length - 1)
        {
            turnInt++;
        }
        else
        {
            turnInt = 0;
        }
        TurnStart();
        Dice.isRolled = false;
    }
    public void act() {
        if (gameStart)
        {
            String key = Greenfoot.getKey();
            if (key == "1")
            {
                showText("P1", 1, 1);
            } else if (key == "2")
            {
                showText("P1", 1, 1);
                showText("P2", 9, 1);
            } else if (key == "3")
            {
                showText("P1", 1, 1);
                showText("P2", 9, 1);
                showText("P3", 9, 9);
            } else if (key == "4")
            {
                showText("P1", 1, 1);
                showText("P2", 9, 1);
                showText("P3", 9, 9);
                showText("P4", 1, 9);
            }
            // ...
        }
    }
}