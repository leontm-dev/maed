import greenfoot.*;

public class GameBoard extends World
{
    public static int cellSize = 75;
    public static int boardSizeX = 11;
    public static int boardSizeY = 11;
    private Player[] players;
    private int currentPlayerIndex;
    private int[] positions;

    private GreenfootImage bgImage;
    private GreenfootImage redImage;
    private GreenfootImage orangeImage;
    private GreenfootImage greenImage;
    private GreenfootImage blueImage;
    private GreenfootImage normalImage;
        

    public GameBoard()
    {
        super(boardSizeX, boardSizeY, cellSize);
        bgImage = new GreenfootImage("bgImage.png");
        redImage = new GreenfootImage("redImage.png");
        orangeImage = new GreenfootImage("orangeImage.png");
        greenImage = new GreenfootImage("greenImage.png");
        blueImage = new GreenfootImage("blueImage.png");
        blueImage = new GreenfootImage("normalImage.png");
        prepareBoard();
        preparePlayers();
    }

    private void prepareBoard()
    {
        // Create the game board cells
        for (int i = 0; i < boardSizeX; i++) {
            for (int j = 0; j < boardSizeY; j++){
                Cell cell = new Cell();
                if (isSpecialPosition(i, j) == "red")
                {
                    cell.setImage(redImage);
                }
                else if(isSpecialPosition(i, j) == "orange") 
                {
                    cell.setImage(orangeImage);
                }
                else if (isSpecialPosition(i, j) == "green")
                {
                    cell.setImage(greenImage);
                }
                else if(isSpecialPosition(i, j) == "blue")
                {
                    cell.setImage(blueImage);
                }
                else if(isSpecialPosition(i, j) == "normal")
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

    private String isSpecialPosition(int x, int y)
    {
        // Determine if the position (x, y) is a special position
        // based on the colored cells of the "Mensch Ärger dich nicht" board game

        int[][] redPosition = {
                {0, 0}, {0, 1}, {0, 9}, {0, 10}, // Red area
            };
        int[][] orangePosition = {
                {0, 0}, {0, 1}, {0, 9}, {0, 10}, // Red area
            };
        int[][] greenPosition = {
                {0, 0}, {0, 1}, {0, 9}, {0, 10}, // Red area
            };
        int[][] bluePosition = {
                {0, 0}, {0, 1}, {0, 9}, {0, 10}, // Red area
            };
        int[][] normalPosition = {
                {0, 0}, {0, 1}, {0, 9}, {0, 10}, // Red area
            };

        // Check if the given position matches any special position
        for (int[] position : redPosition) {
            if (x == position[0] && y == position[1]) {
                return "red";
            }
        }
        for (int[] position : redPosition) {
            if (x == position[0] && y == position[1]) {
                return "orange";
            }
        }
        for (int[] position : redPosition) {
            if (x == position[0] && y == position[1]) {
                return "green";
            }
        }
        for (int[] position : redPosition) {
            if (x == position[0] && y == position[1]) {
                return "blue";
            }
        }

        return "bg";
    }

    private void preparePlayers() {
        // Initialize players
        players = new Player[4];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i);
            addObject(players[i], 40, 40 + i * 80);
        }
    }

    public void act() {
        // Game logic goes here
    }
}
