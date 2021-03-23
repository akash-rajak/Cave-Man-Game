// This java code records the points that the user touches while drawing the line,
// draws the line and also draws the score of the level

package com.example.caveman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;

public class UserInteract extends View implements OnTouchListener {

	// A thread to checks whether the score has increased
	public class UpdateThread implements Runnable {

		public void run() {
			while (!stopThread) {
				if (score != pipe.getScore()) {
					score = pipe.getScore();
					postInvalidate();
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// An ArrayList to hold the points that were selected in type Path
	private ArrayList<Path> line = new ArrayList<Path>();
	private Path path = new Path();// A new Path object
	private Paint paint = new Paint();// A new Paint object

	private com.example.caveman.Pipe pipe;
	// True if the user firstly touched on top of the caveman
	private boolean correct = false;
	// Holds the score of the level
	private int score = 0;

	// Thread created for the timer
	private Thread myThread = new Thread(new UpdateThread());
	// Responsible to stop the Thread when needed
	private boolean stopThread = false;

	// This Constructor initiates the line drawing action and starts the Thread.
	protected UserInteract(Context context) {
		super(context);
		setFocusable(true);
		this.setOnTouchListener(this);

		pipe = com.example.caveman.Pipe.getPipe();

		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		pipe.setWeaponCondition(false);
		pipe.setNextLevel(false);
		myThread.start();
	}

	// This function draws the score achieved so far
	private void drawScore(Canvas canvas) {
		paint.setAntiAlias(true);
		paint.setTextSize(50);
		canvas.drawText("LEVEL SCORE : " + pipe.getScore(), 40, 80, paint);
	}

	// Clears the values stored in the ArrayList of the Pipe
	public void initActions() {
		pipe.clearWeapon();
	}

	// Clears the values stored in the ArrayList of the Pipe and stops the score checking Thread.
	// Mainly used for back button presses
	public void onBackButton() {
		pipe.clearWeapon();
		stopThread = true;
	}

	// Draws the line that is created by the points collected so far.
	// as the user continues to draw the line, the number of points increases and the job of this method becomes bigger
	@Override
	public void onDraw(Canvas canvas) {
		this.drawScore(canvas);
		for (Path path : line) {
			canvas.drawPath(path, paint);
		}
	}

	// Stores the values of the points touched into two ArrayLists, one to be drawn in this layer and
	// one to get passed to the OpenGL layer in order to draw the axe on top.
	// Also states whether the user has touched the screen in order to proceed to the next level or the LevelSelect Activity
	// when he passes a level or gets a game over accordingly
	public boolean onTouch(View view, MotionEvent event) {
		if (pipe.getLevelComplete())
			pipe.setNextLevel(true);
		else
			switch (event.getAction()) {
			// Only allow drawing the line after the user touches on top of the
			// caveman
			case MotionEvent.ACTION_DOWN:
				if (event.getX() > 20 && event.getX() < 90
						&& event.getY() > (this.getHeight() / 2 - 40)
						&& event.getY() < (this.getHeight() / 2 + 40)
						&& pipe.weaponIsReady() == false) {
					path = new Path();
					path.moveTo(event.getX(), event.getY());
					path.lineTo(event.getX(), event.getY());
					pipe.addToList((int) event.getX());
					pipe.addToList((int) event.getY());
					line.add(path);
					correct = true;
					invalidate();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if (correct && pipe.weaponIsReady() == false) {
					path.lineTo(event.getX(), event.getY());
					pipe.addToList((int) event.getX());
					pipe.addToList((int) event.getY());
					invalidate();
				}
				break;
			case MotionEvent.ACTION_UP:
				if (correct && pipe.weaponIsReady() == false) {
					path.lineTo(event.getX(), event.getY());
					pipe.addToList((int) event.getX());
					pipe.addToList((int) event.getY());
					// return to the startpoint in the end of the line
					pipe.returnToInitPoint();
					pipe.setWeaponCondition(true);
					// I clear the ArrayList and prepare for the next round
					line.clear();
					correct = false;
					invalidate();
				}
				break;
			default:
				break;
			}
		return true;
	}
}