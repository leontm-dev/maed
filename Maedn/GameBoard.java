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
    
    private int[][] redPositions;
    private int[][] orangePositions;
    private int[][] greenPositions;
    private int[][] bluePositions;
    private int[][] normalPositions;
        

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
        playerImages[0] = playerRed;
        playerOrange = new GreenfootImage("playerOrange.png");
        playerImages[1] = playerOrange;
        playerGreen = new GreenfootImage("playerGreen.png");
        playerImages[2] = playerGreen;
        playerBlue = new GreenfootImage("playerBlue.png");
        playerImages[3] = playerBlue;
        
        SetSpecialPositions();
        Greenfoot.delay(1);
        PrepareBoard();
        PreparePlayers();
        
        showText("Turn: Player " + (turnInt + 1), 5, 5);
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
        // Initialize players
        players = new Player[4];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i);
            players[i].id = i;
            players[i].setImage(playerImages[i]);
            addObject(players[i], 75, 75);
            players[i].setLocation(i, 2);
        }
    }

    public void act() {
        // Game logic goes here
    }
}