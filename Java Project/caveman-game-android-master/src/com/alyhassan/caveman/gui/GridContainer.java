package com.alyhassan.caveman.gui;

import com.alyhassan.caveman.model.Boardx;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GridContainer {
	
	private GridTile[][] tiles;
	private int rows, cols;
	private Bitmap[] imgs;
	private Boardx board;
	
	public GridContainer(Boardx board, Bitmap[] img){
		this.rows = Boardx.BOARD_ROWS;
		this.cols = Boardx.BOARD_COLS;
		this.board = board;
		imgs = img;
		tiles = new GridTile[rows][cols];
		createTiles();
		
	}
	
	private void createTiles(){
		int y = 5;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){			
				tiles[i][j] = new GridTile(j*GridTile.TILE_SIZE,y,imgs[board.get(i,j)]);
		    }
			y+=GridTile.TILE_SIZE;
		}
	}
	
	public void updateTile(int row, int col, Canvas c){
		tiles[row][col].drawTile(c);
	}
	
	public void drawGrid(Canvas canvas){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){			
				tiles[i][j].drawTile(canvas);
		    }
		}
	}

}
