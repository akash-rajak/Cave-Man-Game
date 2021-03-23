// This java code responsible for buiding the game acitivity ============================================================

package com.example.caveman;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;

import androidx.annotation.RequiresApi;

public class GameActivity extends Activity {
	private com.example.caveman.UserInteract userInter;// The Canvas layer of the game
	private com.example.caveman.GameView surface;// The OpenGL ES layer of the game
	private Bundle extras;// A bundle to pass data from an Activity to a View
	private String level = "1";// the default level is taken as 1, if user doed not select any level

	// Creating Media Player to play any sound or music -------------------------------------------------------------
	private MediaPlayer mp;//Creates a new MediaPlayer to play any kind of sound
	private void clickSound() {// this function Plays a sound when a button is clicked.
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(), R.raw.music1);
		mp.start();
	}

	// This function called when the activity is first created. -----------------------------------------------------
	// Creates the layers, passes the level value and basically starts the game.
	@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Get the height and width of the display
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		clickSound();
		userInter = new com.example.caveman.UserInteract(this);
		userInter.initActions();
		extras = this.getIntent().getExtras();
		level = extras.getString("level");
		surface = new com.example.caveman.GameView(this, height, width, Integer.parseInt(level));
		setContentView(surface);
		addContentView(userInter, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}

	// This function handles the occasion that this Activity finishes ---------------------------------------------
	@Override
	protected void onDestroy() {
		super.onDestroy();
		userInter.onBackButton();
	}

	// This function handles the occasion when backbutton is pressed ---------------------------------
	@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent Menu = new Intent(GameActivity.this, MainActivity.class);
			// clear the pipe, as it is static and may not clear itself
			userInter.onBackButton();
			// by calling the following I free up some memory and I avoid program crashes
			surface.destroyDrawingCache();
			surface.clearFocus();
			userInter.destroyDrawingCache();
			userInter.clearFocus();
			surface.onPause();
			startActivity(Menu);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// This function handles the occasion that the game pauses for some reason ------------------------------
	@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
	@Override
	protected void onPause() {
		super.onPause();
		surface.onPause();
	}

	// this function handles the occasion that the game resumes after pausing. ----------------------------------
	@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
	@Override
	protected void onResume() {
		super.onResume();
		surface.onResume();
	}

}