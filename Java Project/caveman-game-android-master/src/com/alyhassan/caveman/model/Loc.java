package com.alyhassan.caveman.model;

public class Loc 
{
	private int r;
	private int c;
	public Loc(int row, int col)
	{
		r=row;
		c=col;
	}
	public Loc(Loc l){
		r=l.getRow();
		c=l.getCol();
	}
	public int getRow()
	{
		return r;
	}
	public int getCol()
	{
		return c;
	}
	public void setLoc(int row, int col)
	{
		r=row;
		c=col;
	}
	public void setLoc(Loc l)
	{
		c=l.getCol();
		r=l.getRow();
	}
	public void setRow(int row)
	{
		r=row;
	}
	public void setCol(int col)
	{
		c=col;
	}
	public boolean equals(Loc l)
	{
		return (l.getRow()==r&&l.getCol()==c);
	}
	public String toSring()
	{
		return "The row is "+this.getRow()+" and the column is "+this.getCol()+".";
	}
	public Loc clone()
	{
		Loc l = new Loc(this.getRow(),this.getCol());
		return l;
	}
}