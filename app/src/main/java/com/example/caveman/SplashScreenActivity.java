// This java code is responsible for what is going to be the first page of the game when someone opens it =================
package com.example.caveman;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class SplashScreenActivity extends Activity {
	protected volatile boolean active = true;//Holds the state of the splash screen, wheteher its active or not
	protected boolean backKey = false;// boolean variable backKey to check if user hits the backbutton or not, if
										// if hit then we end the game there only
	protected final int TIMER = 10000;// TIMER variable decides means how long the splash screen will be visible
	private Thread splashThread;// The Thread in which the splash screen time is measured

	// Creating Media Player to play any sound or music -------------------------------------------------------------
	private MediaPlayer mp;//Creates a new MediaPlayer to play any kind of sound
	private void clickSound() {// this function Plays a sound when a button is clicked.
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(), R.raw.splash_background);
		//mp = MediaPlayer.create(getApplicationContext(), R.raw.splash_background_1);
		//mp = MediaPlayer.create(getApplicationContext(), R.raw.splash_background_2);
		mp.start();
	}

	// This function is called when the activity is first created. ----------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		//Toast.makeText(SplashScreenActivity.this,"Firebase Connection Success",Toast.LENGTH_LONG).show();

		clickSound();
		// thread for displaying the splash screen
		splashThread = new Thread() {
			@Override
			public void run() {
				try {
					// could have just done sleep, but then hit screen to
					// continue to next Activity would not work.
					int time = 0;
					while (active && (time < TIMER)) {
						sleep(100);
						if (active) {
							time += 100;
						}
					}
				}
				catch (InterruptedException e) {
				}
				finally {
					finish();
					if (backKey == false) {
						clickSound();
						Intent intent = new Intent();
						// switch to the next screen/Activity
						intent.setClass(SplashScreenActivity.this,
								MainActivity.class);
						startActivity(intent);
					}
					interrupt();
				}
			}
		};
		splashThread.start();
	}

	// This function indicates what happens when the back and home buttons are pressed. --------------------------
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:// case when backbutton is pressed
			this.splashThread.interrupt();
			backKey = true;
			finish();
			break;
		case KeyEvent.KEYCODE_HOME:// case when homebutton is pressed
			this.splashThread.interrupt();
			backKey = true;
			finish();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// The splash screen is visible for set time , but if user touches the splash screen, if goes off and menu button is showed
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			active = false;
		}
		return true;
	}
}