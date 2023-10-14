package com.alyhassan.caveman.gui;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GridTile {
	
	private int x,y;
	private Bitmap image;
	public static int TILE_SIZE = 60;
	
	public GridTile(int xPos, int yPos, Bitmap image){
		setCoords(xPos, yPos);
		setImage(image);
	}
	
	public void setX(int xPos){
		x = xPos;
	}
	
	public void setY(int yPos){
		y = yPos;
	}
	
	public void setCoords(int xPos, int yPos){
		setX(xPos);
		setY(yPos);
	}
	
	public void setImage(Bitmap b){
		image = b;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString(){
		return "GridTile @ x = "+x+" y = "+y;
	}
	
	public void drawTile(Canvas c){
		c.drawBitmap(image, x, y, null);
	}

}
