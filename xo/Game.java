//sindi koifan 211657481 alon cohen 319039707

package xo;

public abstract class Game {
    protected char[][] gameBoard;
    protected Player currentPlayer;
    protected PlayerType currentTurn;

    public Game() {
        gameBoard = new char[5][5];
        initializeBoard();
    }

    public enum PlayerType {
        X, O
    }
    public void printBoard() {
        System.out.println("Current Board:");
        for (char[] row : gameBoard) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public Player getTurn() {
        return currentPlayer;
    }
    public PlayerType getTurn1() {
        return currentTurn;
    }

    private void initializeBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    public int[][] getFreeCells() {
        int countFreeCells = 0;
        for (char[] row : gameBoard) {
            for (char cell : row) {
                if (cell == '-') {
                    countFreeCells++;
                }
            }
        }

        int[][] freeCells = new int[countFreeCells][2];
        int index = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == '-') {
                    freeCells[index][0] = i;
                    freeCells[index][1] = j;
                    index++;
                }
            }
        }
        return freeCells;
    }

    public abstract void playGame();

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}
