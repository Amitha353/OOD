public class Chess {
    ChessBoard chessBoard;
    Player[] player;
    Player currentPlayer;
    List<Moves> movesList;
	GameStatus gameStatus;

	public boolean playerMove(CellPosition from, CellPosition to, Piece piece);
	public boolean endGame();
	private void changeTurn(); // playerMove
}

public class Player {
	Account account;
	Color color;
	Time timeleft;
}

public class Account {
	String username;
	String password;
	String name;
	String email;
	String phone;
}

public enum Color {
	BLACK, WHITE;
}

public class Time {
	int mins;
	int secs;
}

public enum GameStatus {
	ACTIVE, PAUSED, FORTFEIGHT, WHITE_WIN, BLACK_WIN;
}

public class ChessBoard {
	Cell[][] board;
	
	public void resetBoard();
	public void updateBoard(Move move);
}

public class Cell {
	Color color;
	Piece piece;
	CellPosition position;
}

public class CellPosition {
	Char ch;
	int i;
}

public class Move {
	Player turn;
	Piece piece;
	Piece killedPiece;
	CellPosition startPosition;
	CellPosition endPosition;
}

public abstract class piece {
	Color color;
	
	public abstract boolean move(CellPosition from, CellPosition to);
	public abstract List<CellPosition> PossibleMoves(CellPosition from);
	public abstract boolean validate(CellPosition from, CellPosition to);
}

public class Knight extends Piece {
	public boolean move(CellPosition from, CellPosition to);
	public List<CellPosition> PossibleMoves(CellPosition from);
	public boolean validate(CellPosition from, CellPosition to);
}

public class Bishop extends Piece {
	public boolean move(CellPosition from, CellPosition to);
	public List<CellPosition> PossibleMoves(CellPosition from);
	public boolean validate(CellPosition from, CellPosition to);
}

public class Rook extends Piece {
	public boolean move(CellPosition from, CellPosition to);
	public List<CellPosition> PossibleMoves(CellPosition from);
	public boolean validate(CellPosition from, CellPosition to);
}

public class King extends Piece {
	public boolean move(CellPosition from, CellPosition to);
	public List<CellPosition> PossibleMoves(CellPosition from);
	public boolean validate(CellPosition from, CellPosition to);
}

public class Queen extends Piece {
	public boolean move(CellPosition from, CellPosition to);
	public List<CellPosition> PossibleMoves(CellPosition from);
	public boolean validate(CellPosition from, CellPosition to);
}

public class Pawn extends Piece {
	public boolean move(CellPosition from, CellPosition to);
	public List<CellPosition> PossibleMoves(CellPosition from);
	public boolean validate(CellPosition from, CellPosition to);
}