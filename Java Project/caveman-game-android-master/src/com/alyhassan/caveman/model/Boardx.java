package com.alyhassan.caveman.model;


public class Boardx
{
	public int[][] board;
	
	public static final int EMPTY = 0;
	public static final int CAVEMAN = 1;
	public static final int BOULDER = 2;
	public static final int WALL = 3;
	public static final int EXIT = 4;
	public static final int HOLE = 5;
	
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	public static final int BOARD_ROWS = 12, BOARD_COLS = 8;

	/**
	 * constructs a Board object 
	 */
	public Boardx()
	{
		board = new int[BOARD_ROWS][BOARD_COLS];
		for(int i = 0; i < BOARD_ROWS; i++)
			for(int j = 0; j < BOARD_COLS; j++)
				board[i][j] = EMPTY;
		board[0][0] = CAVEMAN;
		board[Boardx.BOARD_ROWS-1][Boardx.BOARD_COLS-1] = EXIT;
	}
	
	/**
	 * constructs a Board object 
	 * @param board
	 */
	public Boardx(int[][] board)
	{
		this.board = board;
	}
	
	public Boardx(Boardx b) {
		this.board = b.board;
	}
	
	/**
	 * 
	 * @return location of caveman(use the constants in this class)
	 */
	public Loc getCavemanLoc()
	{
		int row=0, col=0;
		for(int i = 0;i<BOARD_ROWS;i++)
		{
			for(int j = 0;j<BOARD_COLS;j++)
			{
				if(this.get(i,j)==CAVEMAN)
				{
					row=i;
					col=j;
				}
			}
		}
		Loc caveman = new Loc(row,col);
		return caveman;
	}
	
	/**
	 * 
	 * @param location
	 * @return an integer indicating what is at that location on the board(use the constants in this class)
	 */
	public int get(Loc l)
	{
		return board[l.getRow()][l.getCol()];
	}
	
	/**
	 * sets the location l to the value a on the board
	 * @param l
	 * @param a
	 */
	public void set(Loc l, int a)
	{
		if(a >= EMPTY && a <= HOLE)
			board[l.getRow()][l.getCol()] = a;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return an integer indicating what is at that location on the board(use the constants in this class)
	 */
	public int get(int row, int col)
	{
		return board[row][col];
	}
	
	/**
	 * 
	 * @return the entire board
	 */
	public int[][] getBoard()
	{
		return board;
	}
	
	/**
	 * 
	 * @return number of rows on the board
	 */
	public int getRows()
	{
		return BOARD_ROWS;
	}
	
	/**
	 * 
	 * @return number of columns on the board
	 */
	public int getCols()
	{
		return BOARD_COLS;
	}
	
	/**
	 * on a valid board:
	 * -there are only numbers from 0-4 inclusive
	 * -there is only one caveman
	 * -there is at least one exit
	 * (use the constants in this class)
	 * @return if the board is valid
	 */
	public boolean isValidBoard()
	{
		byte caveman=0, exit=0, error=0;
		for(int i = 0;i<BOARD_ROWS;i++)
		{
			for(int j = 0;j<BOARD_COLS;j++)
			{
				if(this.get(i,j)==CAVEMAN)
					caveman++;
				if(this.get(i,j)==EXIT)
					exit++;
				if(this.get(i,j)<0||this.get(i,j)>5)
					error++;
			}
		}
		if(caveman==1&&exit>=1&&error==0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param location
	 * @return if this location on the board is empty(use the constants in this class)
	 */
	public boolean isEmpty(Loc location)
	{
		if(this.get(location)==EMPTY)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return if this location on the board is empty(use the constants in this class)
	 */
	public boolean isEmpty(int row, int column)
	{
		if(this.get(row, column)==EMPTY)
			return true;
		return false;
	}
	
		
	/**
	 * 
	 * @param b
	 * @return if the two boards are equal
	 */
	public boolean equals(Boardx b)
	{
		if(b.getBoard()==this.getBoard())
			return true;
		return false;
	}
} // end class