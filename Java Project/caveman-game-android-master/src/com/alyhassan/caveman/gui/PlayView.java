package com.alyhassan.caveman.gui;

import com.alyhassan.caveman.R;
import com.alyhassan.caveman.model.Boardx;
import com.alyhassan.caveman.model.PlayBoard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;

public class PlayView extends View{
	
	private boolean swipeEnabled;
	public static final short UP=1, DOWN=2, LEFT=3,RIGHT=4;
	private int[][] orgBoard;
	private PlayBoard playBoard;
	private Bitmap[] images;

	public PlayView(Context context, Boardx b) {
		super(context);	
    	Log.d("caveman", "Game Mode Launched");
    	swipeEnabled = true;
    	
    	createImages();
    	
    	playBoard = new PlayBoard(b.getBoard());
    	
    	orgBoard = new int[Boardx.BOARD_ROWS][Boardx.BOARD_COLS];
    	
    	for(int i = 0; i < Boardx.BOARD_ROWS; i++)
    		for(int j = 0; j < Boardx.BOARD_COLS; j++)
    			orgBoard[i][j] = b.get(i, j);
    	
    	SwipeDetector sd = new SwipeDetector(this);
    	this.setOnTouchListener(sd);  
    	   	
	}	
	
	private void createImages() {
		images = new Bitmap[6];
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
		images[0] = Bitmap.createScaledBitmap(bitmap, GridTile.TILE_SIZE,  GridTile.TILE_SIZE, true);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.caveman);
		images[1] = Bitmap.createScaledBitmap(bitmap,  GridTile.TILE_SIZE,  GridTile.TILE_SIZE, true);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rock);
		images[2] = Bitmap.createScaledBitmap(bitmap, GridTile.TILE_SIZE,  GridTile.TILE_SIZE, true);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
		images[3] = Bitmap.createScaledBitmap(bitmap,  GridTile.TILE_SIZE,  GridTile.TILE_SIZE, true);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.door);
		images[4] = Bitmap.createScaledBitmap(bitmap,  GridTile.TILE_SIZE,  GridTile.TILE_SIZE, true);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
		images[5] = Bitmap.createScaledBitmap(bitmap,  GridTile.TILE_SIZE,  GridTile.TILE_SIZE, true);

    	Log.d("caveman", "Images successfully loaded");
	}



	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.argb(127,101,103,0));
		if(swipeEnabled){
			if(getWidth()/Boardx.BOARD_COLS != GridTile.TILE_SIZE){
	    		GridTile.TILE_SIZE = canvas.getWidth()/Boardx.BOARD_COLS;
	    		Log.d("caveman", "Tile size changed to "+ getWidth());
	    	}
			
			new GridContainer(playBoard,images).drawGrid(canvas);
			
			Paint paint = new Paint(); 
			paint.setColor(Color.argb(127,220,230,0)); 
			paint.setTextSize(20);
			
			checkForWin(canvas,paint);
		}else{
			Paint paint = new Paint(); 
			paint.setColor(Color.argb(127,220,230,0)); 
			paint.setTextSize(61);
			float width = paint.measureText("YOU WON!");
			float x = (canvas.getWidth()-width)/2;
			canvas.drawText("YOU WON!", x, GridTile.TILE_SIZE*((Boardx.BOARD_ROWS/2)-1) + 80, paint);
		}
	}
	
	private void checkForWin(Canvas c,Paint p) {
		if(orgBoard[playBoard.getCavemanLoc().getRow()][playBoard.getCavemanLoc().getCol()] == Boardx.EXIT){
			message("Choose 'Restart' from the menu to play again!", c, p);
			swipeEnabled = false;
			this.invalidate(40, GridTile.TILE_SIZE*((Boardx.BOARD_ROWS/2)-1), 440, GridTile.TILE_SIZE*((Boardx.BOARD_ROWS/2)+1));
		}else{
			message("Try to get the caveman to the door!", c, p);
		}
	}
	
	private void message(String message, Canvas c, Paint p){
		float width = p.measureText(message);
		float x = (c.getWidth()-width)/2;
		c.drawText(message, x, GridTile.TILE_SIZE*Boardx.BOARD_ROWS + 30, p);
		
	}

	public void swiped(int swipe){
		if(swipeEnabled){
			if(swipe == UP){
				if(playBoard.moveCaveman(Boardx.UP))
					this.invalidate();
			}
			else if(swipe == DOWN){
				if(playBoard.moveCaveman(Boardx.DOWN))
					this.invalidate();
			}
			else if(swipe == LEFT){
				if(playBoard.moveCaveman(Boardx.LEFT))
					this.invalidate();
			}
			else if(swipe == RIGHT){
				if(playBoard.moveCaveman(Boardx.RIGHT))
					this.invalidate();
			}	
		}
	}
	
	public void refresh(){
		this.invalidate();
	}
	
	public void reset(Boardx b){
		playBoard = new PlayBoard(b.getBoard());    	    	
    	for(int i = 0; i < Boardx.BOARD_ROWS; i++)
    		for(int j = 0; j < Boardx.BOARD_COLS; j++)
    			orgBoard[i][j] = b.get(i, j);
    	
    	invalidate();
		swipeEnabled = true;
	}
	
}
