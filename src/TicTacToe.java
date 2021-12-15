import java.util.Random;
import java.util.Scanner;
public class TicTacToe {

	private int n; // number of rows
	private int m; // number of columns;
	private int k; // number of moves to win
	private int number_of_turns; // number of turns that have been played
	private int tempi,tempj; // variables to take a continous int from the user to the place his move in, then change that number to indexes
	///////
	private int [] rowcontainerX;
	private int [] columncontainerX;
	private int [] rowcontainerO;
	private int [] columncontainerO;
	private int [] diagonalcontainerO;
	private int [] diagonalcontainerX;
	//////// these variables are used to check the winner
	private int smaller;
	private Scanner sc= new Scanner(System.in); 
	private enum board_state{ // enum represting the state of the game
		INPROGRESS,
		DRAW,
		WIN_X,
		WIN_O
	}
	private enum cell_state{ // enum represting the state of each cell
		O,
		X,
		Empty
	}
	private cell_state [][] board; // the board that we will place the values in
	private board_state Current_board_state; // the current statues of the game
	public TicTacToe (TicTacToe temp) // deep copy constructor, so i can make a new object while preserving all the same values
	{
		this.n=temp.n;
		this.m=temp.m;
		this.k=temp.k;
		initizalize_board(this.n,this.m);
		this.number_of_turns=temp.number_of_turns;
		this.tempi=temp.tempi;
		this.tempj=temp.tempj;
		this.smaller=temp.smaller;
		this.Current_board_state=temp.Current_board_state;
		for (int i=0;i<n;i++)
		{
			this.rowcontainerO[i]=temp.rowcontainerO[i];
			this.rowcontainerX[i]=temp.rowcontainerX[i];
			for(int j=0;j<m;j++)
				{
				this.board[i][j]=temp.board[i][j];
				this.columncontainerO[j]=temp.columncontainerO[j];
				this.columncontainerX[j]=temp.columncontainerX[j];
				}
		}
		for (int i=0;i<this.smaller;i++)
		{
			this.diagonalcontainerX[i]=temp.diagonalcontainerX[i];
			this.diagonalcontainerO[i]=temp.diagonalcontainerO[i];
		}
	}
	public TicTacToe () // basic initializer that sets the number of rows,columns and number of moves to win to 3
	{
		set_board_rows(3);
		set_board_columns(3);
		set_number_of_moves_to_win(3);
		set_number_of_turns_played(0);
		setBoardState("INPROGRESS");
		draw_board(n,m);
		initizalize_board(n,m);
	}
	public TicTacToe(int rows, int columns) // an initializer that let the user choose the size of the board nXm and let's k equal to 3
	{
		set_board_rows(rows);
		set_board_columns(columns);
		set_number_of_moves_to_win(3);
		set_number_of_turns_played(0);
		setBoardState("INPROGRESS");
		draw_board(n,m);
		initizalize_board(n,m);
	}
	public TicTacToe(int rows, int columns, int moves) // an initializer that let the user choose n,m and k
	{
		set_board_rows(rows);
		set_board_columns(columns);
		set_number_of_moves_to_win(moves);
		set_number_of_turns_played(0);
		setBoardState("INPROGRESS");
		draw_board(n,m);
		initizalize_board(n,m);
	}
	private void draw_board(int rows, int columns) // this is a function to draw the board empty for the first time when the user initializes the size of the board
	{
		for (int i=0; i<rows;i++)
		{
			for (int j=0;j<columns;j++)
			{
					System.out.print("   |");
			}
			System.out.println();
			for(int k=0;k<columns*4;k++)
			{
				System.out.print("-");
			}
			System.out.println();
		}
	}
	
	public void redraw_board() // this is a function to redraw the board after every move to show the new board with it's corresponding values
	{
		for (int i=0; i<n;i++)
		{
			for (int j=0;j<m;j++)
			{
				if(board[i][j]==cell_state.Empty)
					System.out.print("   |");
				else if(board[i][j]==cell_state.X)
					System.out.print("X  |");
				else if(board[i][j]==cell_state.O)
					System.out.print("O  |");
			}
			System.out.println();
			for(int k=0;k<m*4;k++)
			{
				System.out.print("-");
			}
			System.out.println();
		}
	}
	private void initizalize_board(int rows, int columns) // to iniztialize the 2d array"board" by the right diminsions
	{
		this.board = new cell_state [rows][columns];
		this.rowcontainerX = new int [rows];
		this.columncontainerX=new int [columns];
		this.rowcontainerO = new int [rows];
		this.columncontainerO=new int [columns];
		
		if(rows>=columns)
			this.smaller=rows;
		else 
			this.smaller=rows;
		this.diagonalcontainerX= new int [this.smaller];
		this.diagonalcontainerO= new int [this.smaller];
		for (int i=0;i<this.smaller;i++)
		{
			this.diagonalcontainerX[i]=0;
			this.diagonalcontainerO[i]=0;
		}
		for (int i=0; i<rows; i++)
		{
			this.rowcontainerX[i]=0;
			this.rowcontainerO[i]=0;
			for (int j=0; j<columns; j++)
			{
				board[i][j]=cell_state.Empty;
				this.columncontainerX[j]=0;
				this.columncontainerO[j]=0;
			}
		}

	}
	public void print_cells_status() // this is a testing function to check the enum status of the 2d array, to see if the values are set correctly
	{
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<m; j++)
			{
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	private void set_board_rows(int rows)
	{
		this.n=rows;
	}
	private void set_board_columns(int columns)
	{
		this.m=columns;
	}
	private void set_number_of_moves_to_win (int moves)
	{
		while (moves>n && moves>m)
		{
			System.out.println("the number of moves to win can't be larger than the number of rows and columns");
			moves=sc.nextInt();
		}
		this.k=moves;
	}
	private void set_number_of_turns_played (int num)
	{
		this.number_of_turns=num;
	}
	public int return_board_rows()
	{
		return this.n;
	}
	public int return_board_columns()
	{
		return this.m;
	}
	public int return_number_of_moves_to_win()
	{
		return this.k;
	}
	public int return_number_of_turns_played()
	{
		return this.number_of_turns;
	}
	public String return_board_status() // return the status of the game
	{
		if (this.Current_board_state== board_state.INPROGRESS)
			return "INPROGRESS";
		else if (this.Current_board_state== board_state.DRAW)
			return "DRAW";
		else if (this.Current_board_state== board_state.WIN_O)
			return "WIN_O";
		else
			return "WIN_X";
	}
	public void setBoardState(String state) // setting the statues of the game
	{
		if (state.equalsIgnoreCase("INPROGRESS"))
			this.Current_board_state=board_state.INPROGRESS;
		else if (state.equalsIgnoreCase("DRAW"))
			this.Current_board_state=board_state.DRAW;
		else if (state.equalsIgnoreCase("WIN_O"))
			this.Current_board_state=board_state.WIN_O;
		else if (state.equalsIgnoreCase("WIN_X"))
			this.Current_board_state=board_state.WIN_X;
		else 
		{
			System.out.println("this value is not valid for board statues, the value of the board states will not be changed");
		}
			
	}
	public char nextPlayer() // get which player is set to play this round
	{
		if (this.number_of_turns==0)
			return 'O';
		else if (this.number_of_turns%2==0) // if the turn is even then it's O turn else it's X turn
			return 'O';
		else
			return 'X';
	}
	public int getwinner(char c) // checks if the game has a winner or is it still in progress
	{
		int sum=0;
		if (c=='X')
		{
			for (int i=0;i<n;i++)
			{
				if(this.rowcontainerX[i]==this.k) // if i have k X values in the same row then i have a winner
					return 1; // indicates X is winner
				
			}
			for (int j=0;j<m;j++)
			{
				if(this.columncontainerX[j]==k) // if i have k X values in the same column then i have a winner
					return 1;
			}
			for (int i=0;i<this.smaller;i++)
			{
				if (this.diagonalcontainerX[i]==1) // if the some of the diagonal that contains X is K then i have a winner
					sum+=1;
			}
			if(sum==k)
				return 1;
		}
		else if (c=='O')
		{
			for (int i=0;i<n;i++)
			{
				if(this.rowcontainerO[i]==this.k)
					return 2; // indicates X is winner
				
			}
			for (int j=0;j<m;j++)
			{
				if(this.columncontainerO[j]==k)
					return 2; // indicates O is the winner
			}
			for (int i=0;i<this.smaller;i++)
			{
				if (this.diagonalcontainerO[i]==1)
					sum+=1;
			}
			if(sum==k)
				return 2;
		}
		if (this.number_of_turns==n*m) // if i don't have a winner and the number of turns equal to the board then the board is full and the game is a Draw
			return 3; // draw

			return 0; // game is still on going
		
	}
	private void calculatewinner() // increasing the containers of the placements of the o and x so we can calculate from those arrays the winner
	{
		if(nextPlayer()=='O')
		{
			
		this.rowcontainerO[this.tempi]+=1;
		this.columncontainerO[this.tempj]+=1;
		if(this.tempi==this.tempj)
		{
			this.diagonalcontainerO[this.tempi]+=1;
		}
		}
		else
		{
			this.rowcontainerX[this.tempi]+=1;
			this.columncontainerX[this.tempj]+=1;	
			if(this.tempi==this.tempj)
			{
				this.diagonalcontainerX[this.tempi]+=1;
			}
		}
	}
	private void loop_to_get_index(int index) // to convert from a continous number that the user entered to indexes for the 2d array
	{
		int counter;
		index=index-1;
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<m;j++)
			{
				counter=(i*n)+j;
				if(counter==index)
				{
					this.tempi=i;
					this.tempj=j;
					return;
				}
			
			}
		}
		
	}
	public boolean play (int index)
	{
		
		while (index<=0 || index > n*m)
		{
			System.out.printf("this index is out of boundraies please enter a value between 1 and %d \n" ,n*m);
			index=sc.nextInt();
		}
		
		loop_to_get_index(index);
		if(board[tempi][tempj]!= cell_state.Empty)
			{
			return false;
			}
		else
		{
			char player_char=nextPlayer();
			if(player_char=='O')
				board[tempi][tempj]=cell_state.O;
			else
				board[tempi][tempj]=cell_state.X;
			calculatewinner();
			number_of_turns++;
			return true;
		}
	}
	public void RandomAi() // this algorithms just picks a random place on the board to place the O in it
	{
		
		Random rand = new Random();
		 tempi = rand.nextInt(n);
		 tempj = rand.nextInt(m);
		while(board[tempi][tempj]!=cell_state.Empty)
		{
			 tempi = rand.nextInt(n);
			 tempj = rand.nextInt(m);
		}
		board[tempi][tempj]=cell_state.O;
		calculatewinner();
		number_of_turns++;
		System.out.println("RandomAI has played it's turn");
	}
	private void MediumAi_move()
	{
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<m;j++)
			{
				if(this.board[i][j]==cell_state.X)
				{
					if(i+1<n)
						{
						if(this.board[i+1][j]==cell_state.Empty)
							{
							tempi=i+1;
							tempj=j;
							return;
							}
						}
					 if (j+1<m)
					{
						if(this.board[i][j+1]==cell_state.Empty)
							{
							tempi=i;
							tempj=j+1;
							return;
							}
			
					}
				}
			}
		}
		Random rand = new Random();
		 tempi = rand.nextInt(n);
		 tempj = rand.nextInt(m);
		while(board[tempi][tempj]!=cell_state.Empty)
		{
			 tempi = rand.nextInt(n);
			 tempj = rand.nextInt(m);
		}
	}
	public void MediumAi()
	{
		if(this.number_of_turns==0)
		{
			tempi=n/2;
			tempj=m/2;
		}
		else
		{
			MediumAi_move();
		}
		board[tempi][tempj]=cell_state.O;
		calculatewinner();
		number_of_turns++;
		System.out.println("MediumAI has played it's turn");
	}
	
}
