import java.util.Scanner;
import java.util.ArrayList; 
public class Game {
	private Scanner sc= new Scanner(System.in); 
	private ArrayList<TicTacToe> replay_board=new ArrayList<TicTacToe>();
	 private TicTacToe game;
	 public Game( TicTacToe g) // constructor
	 {
		 this.game=g;
	 }
	 public void printwinner(int winner) // to set the state of the board and print the final winner of the game
	 {
		 if (winner==1)
		 { 
			 System.out.println("X is the winner");
		 	game.setBoardState("WIN_X");
		 }
		 else if(winner==2)
			 {
			 System.out.println("O is the winner");
			 game.setBoardState("WIN_O");
			 }
		 else if(winner==3)
		 {
			 System.out.println("it's a Draw");
			 game.setBoardState("DRAW");
		 }
	 }
	 public void manual_Mode() // 2 player play against each other
	 {
		 int index;
		 char c=game.nextPlayer();
		 boolean nextturn=true;
		 int winner=0;
		 while(winner==0)
		 { 
		 System.out.printf("%c's round to play \n", c);
		 System.out.printf("please enter a number between 1 and %d or -1 to replay \n", game.return_board_columns()*game.return_board_rows());
		 index=sc.nextInt();
			 if (index!=-1) 
			 {
				 nextturn=game.play(index);
				 while(nextturn==false)
				 	{
					 System.out.println("you have entered a value that have already been occupied before please choose another value");
					 System.out.printf("please enter a number between 1 and %d \n", game.return_board_columns()*game.return_board_rows());
					 index=sc.nextInt();
					 nextturn=game.play(index);
				 	}
				 game.redraw_board();
				 winner=game.getwinner(c);
				 c=game.nextPlayer();
				 TicTacToe temp_board = new TicTacToe(game);
				 this.replay_board.add(temp_board);
			 }
			 else
			 {
				 replay();
			 }
		 }
		 printwinner(winner);
	 }
	 private void replay()
	 {
		 int index;
		 System.out.printf("please enter from were you want to replay from 1'st move to %d", this.replay_board.size());
		 index=sc.nextInt();
		 while(index<1||index>this.replay_board.size()+1)
		 {
			 System.out.println("this game has not been played yet");
			 System.out.printf("please enter from were you want to replay from 1'st move to %d", this.replay_board.size());
			 index=sc.nextInt();
		 }
		 index=index-1;
		 replay_option(index);
	 }
	private void replay_option(int index)
	{
		System.out.println("replaying from here");
		this.game=this.replay_board.get(index);
		this.game.redraw_board();
	}
	 public void Random_Auto()
	 {
		 int index;
		 char c=game.nextPlayer();
		 boolean nextturn=true;
		 int winner=0;
		 while(winner==0)
		 { 
			 if(c=='X')
			 {
				 System.out.printf("%c's round to play \n", c);
				 System.out.printf("please enter a number between 1 and %d  or -1 to replay\n", game.return_board_columns()*game.return_board_rows());
				 index=sc.nextInt();
				 if(index!=-1) 
				 {
					 nextturn=game.play(index);
					 while(nextturn==false)
					 {
						 System.out.println("you have entered a value that have already been occupied before please choose another value");
						 System.out.printf("please enter a number between 1 and %d \n", game.return_board_columns()*game.return_board_rows());
						 index=sc.nextInt();
						 nextturn=game.play(index);
					 }
				 }
				 else
				 {
					 replay();
				 }
			 }
			 else
				 game.RandomAi();
			 
		 game.redraw_board();
		 winner=game.getwinner(c);
		 c=game.nextPlayer();	
		 TicTacToe temp_board = new TicTacToe(game);
		 this.replay_board.add(temp_board);
	 
		 }
		 printwinner(winner);
	 }
	 public void Medium_Auto()
	 {
		 int index;
		 char c=game.nextPlayer();
		 boolean nextturn=true;
		 int winner=0;
		 while(winner==0)
		 { 
			 if(c=='X')
			 {
				 System.out.printf("%c's round to play \n", c);
				 System.out.printf("please enter a number between 1 and %d  or -1 to replay\n", game.return_board_columns()*game.return_board_rows());
				 index=sc.nextInt();
				 if(index!=-1) 
				 {
					 nextturn=game.play(index);
					 while(nextturn==false)
					 {
						 System.out.println("you have entered a value that have already been occupied before please choose another value");
						 System.out.printf("please enter a number between 1 and %d \n", game.return_board_columns()*game.return_board_rows());
						 index=sc.nextInt();
						 nextturn=game.play(index);
					 }
				 }
				 else
				 {
					 replay();
				 }
			 }
			 else
				 game.MediumAi();
			 
		 game.redraw_board();
		 winner=game.getwinner(c);
		 c=game.nextPlayer();	
		 TicTacToe temp_board = new TicTacToe(game);
		 this.replay_board.add(temp_board);
	 
		 }
		 printwinner(winner);
	 }

}
