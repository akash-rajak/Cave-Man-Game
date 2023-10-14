package com.alyhassan.caveman.model;

public class PlayBoard extends Boardx
{
	/**
	 * constructor
	 * @param rows
	 * @param columns
	 */
	public PlayBoard()
	{
		super();
	}
	
	/**
	 * constructor
	 * @param board
	 */
	public PlayBoard(int[][] board)
	{
		super(board);
	}
	
	public PlayBoard(Boardx b) {
		super(b);
	}
	
	/**
	 * pushes the boulder at a certain location in a certain direction(use the constants in the Board class)
	 * @param location
	 * @param direction
	 * @return if the boulder was able to be pushed
	 */
	public boolean pushBoulder(Loc location, int direction)
	{
		return pushBoulder(location.getRow(), location.getCol(), direction);
	}
	
	/**
	 * pushes the boulder at a certain location in a certain direction(use the constants in the Board class)
	 * @param row
	 * @param column
	 * @param direction
	 * @return if the boulder was able to be pushed
	 */
	public boolean pushBoulder(int row, int column, int direction)
	{
		try{
			if(direction==RIGHT){
				if(board[row][column+1]==EMPTY){
					board[row][column+1]=BOULDER;
					board[row][column]=EMPTY;
					return true;
				}
				if(board[row][column+1]==HOLE){
					board[row][column+1]=EMPTY;
					board[row][column]=EMPTY;
					return true;
				}
			}
			
			if(direction==LEFT){
				if(board[row][column-1]==EMPTY){
					board[row][column-1]=BOULDER;
					board[row][column]=EMPTY;
				return true;
				}
				if(board[row][column-1]==HOLE){
					board[row][column-1]=EMPTY;
					board[row][column]=EMPTY;
				return true;
				}
			}
			
			if(direction==DOWN){
				if(board[row+1][column]==EMPTY){
					board[row+1][column]=BOULDER;
					board[row][column]=EMPTY;
				return true;
				}
				if(board[row+1][column]==HOLE){
					board[row+1][column]=EMPTY;
					board[row][column]=EMPTY;
				return true;
				}
			}
			
			if(direction==UP){
				if(board[row-1][column]==EMPTY){
					board[row-1][column]=BOULDER;
					board[row][column]=EMPTY;
					return true;
				}
				if(board[row-1][column]==HOLE){
					board[row-1][column]=EMPTY;
					board[row][column]=EMPTY;
					return true;
				}
			}
			}
			catch(ArrayIndexOutOfBoundsException e){
				return false;
			}
			
			return false;
	}
	
	/**
	 * moves caveman in a certain direction(use the constants in the Board class)
	 * @param direction
	 * @return if the caveman was able to be moved
	 */
	public boolean moveCaveman(int direction)
	{
		Loc l = getCavemanLoc();
		try{
			if(direction==RIGHT){
				if(board[l.getRow()][l.getCol()+1]==EMPTY || board[l.getRow()][l.getCol()+1]==EXIT){
					board[l.getRow()][l.getCol()+1]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
				return true;
				}
				else if(board[l.getRow()][l.getCol()+1]==BOULDER && pushBoulder(l.getRow(), l.getCol()+1, direction))
				{
					board[l.getRow()][l.getCol()+1]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
					return true;
				}
			}
			
			if(direction==LEFT){
				if(board[l.getRow()][l.getCol()-1]==EMPTY || board[l.getRow()][l.getCol()-1]==EXIT){
					board[l.getRow()][l.getCol()-1]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
				return true;
				}
				else if(board[l.getRow()][l.getCol()-1]==BOULDER && pushBoulder(l.getRow(), l.getCol()-1, direction))
				{
					board[l.getRow()][l.getCol()-1]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
					return true;
				}
			}
			
			if(direction==DOWN){
				if(board[l.getRow()+1][l.getCol()]==EMPTY || board[l.getRow()+1][l.getCol()]==EXIT){
					board[l.getRow()+1][l.getCol()]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
				return true;
				}
				else if(board[l.getRow()+1][l.getCol()]==BOULDER && pushBoulder(l.getRow()+1, l.getCol(), direction))
				{
					board[l.getRow()+1][l.getCol()]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
					return true;
				}
			}
			
			if(direction==UP){
				if(board[l.getRow()-1][l.getCol()]==EMPTY || board[l.getRow()-1][l.getCol()]==EXIT){
					board[l.getRow()-1][l.getCol()]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
				return true;
				}
				else if(board[l.getRow()-1][l.getCol()]==BOULDER && pushBoulder(l.getRow()-1, l.getCol(), direction))
				{
					board[l.getRow()-1][l.getCol()]=CAVEMAN;
					board[l.getRow()][l.getCol()]=EMPTY;
					return true;
				}
			}
			}
			catch(ArrayIndexOutOfBoundsException e){
				return false;
			}
			
			return false;
	}
}