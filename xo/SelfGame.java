//sindi koifan 211657481 alon cohen 319039707

package xo;

public class SelfGame extends Game {
    private SelfPlayer selfPlayerX;
    private SelfPlayer selfPlayerO;

    public SelfGame() {
        super();
        selfPlayerX = new SelfPlayer(PlayerType.X);
        selfPlayerO = new SelfPlayer(PlayerType.O);
        currentPlayer = selfPlayerX;
        currentTurn = PlayerType.X;
    }

    @Override
    public void playGame() {
        while (true) {
            if (isBoardFull()) {
                System.out.println("Board is full. It's a draw!");
                break;
            }

            currentPlayer.makeMove(this);
            if (checkWin()) {
                System.out.println("SelfPlayer " + currentPlayer.playerType + " wins!");
                break;
            }

            // Switch players
            currentPlayer = (currentPlayer == selfPlayerX) ? selfPlayerO : selfPlayerX;
        }
    }

    private boolean isBoardFull() {
        for (char[] row : gameBoard) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }
	private boolean checkWin() {
		return checkRows() || checkColumns() || checkDiagonals();
	}

	private boolean checkRows() {
		for (int i = 0; i < gameBoard.length; i++) {
			if (gameBoard[i][0] != '-' && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2]
					&& gameBoard[i][2] == gameBoard[i][3]) {
				return true;
			}
			if (gameBoard[i][1] != '-' && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][2] == gameBoard[i][3]
					&& gameBoard[i][3] == gameBoard[i][4]) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColumns() {
		for (int j = 0; j < gameBoard[0].length; j++) {
			if (gameBoard[0][j] != '-' && gameBoard[0][j] == gameBoard[1][j] && gameBoard[1][j] == gameBoard[2][j]
					&& gameBoard[2][j] == gameBoard[3][j]) {
				return true;
			}
			if (gameBoard[1][j] != '-' && gameBoard[1][j] == gameBoard[2][j] && gameBoard[2][j] == gameBoard[3][j]
					&& gameBoard[3][j] == gameBoard[4][j]) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonals() {
		// Check main diagonal
		if (gameBoard[0][0] != '-' && gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]
				&& gameBoard[2][2] == gameBoard[3][3]) {
			return true;
		}
		if (gameBoard[1][1] != '-' && gameBoard[1][1] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[3][3]
				&& gameBoard[3][3] == gameBoard[4][4]) {
			return true;
		}
		// Check main next side diagonal
		if (gameBoard[0][4] != '-' && gameBoard[0][4] == gameBoard[1][3] && gameBoard[1][3] == gameBoard[2][2]
				&& gameBoard[2][2] == gameBoard[3][1]) {
			return true;
		}
		if (gameBoard[4][0] != '-' && gameBoard[4][0] == gameBoard[1][3] && gameBoard[1][3] == gameBoard[2][2]
				&& gameBoard[2][2] == gameBoard[3][1]) {
			return true;
		}

		// Check anti-diagonal right
		if (gameBoard[0][3] != '-' && gameBoard[0][3] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][1]
				&& gameBoard[2][1] == gameBoard[3][0]) {
			return true;
		}
		if (gameBoard[1][4] != '-' && gameBoard[1][4] == gameBoard[2][3] && gameBoard[2][3] == gameBoard[3][2]
				&& gameBoard[3][2] == gameBoard[4][1]) {
			return true;
		}
		// Check anti-diagonal left
		if (gameBoard[1][0] != '-' && gameBoard[1][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[3][2]
				&& gameBoard[3][2] == gameBoard[4][3]) {
			return true;
		}
		if (gameBoard[0][1] != '-' && gameBoard[0][1] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][3]
				&& gameBoard[2][3] == gameBoard[3][4]) {
			return true;
		}

		return false;
	}


}
