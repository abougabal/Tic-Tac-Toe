
public class Main {

	public static void main(String[] args) {
		TicTacToe board = new TicTacToe(3,3);
		Game game = new Game(board);
		//game.manual_Mode();
		game.Random_Auto();
		//game.Medium_Auto();
		
	}

}
