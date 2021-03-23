// This java code is basically to display the instruction of playing ==================================

package com.example.caveman;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;

public class HelpActivity extends Activity {
	// Creating Media Player to play any sound or music -------------------------------------------------------------
	private MediaPlayer mp;//Creates a new MediaPlayer to play any kind of sound
	private void clickSound() {// this function Plays a sound when a button is clicked.
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(), R.raw.music2);
		mp.start();
	}

	// This method is called when the activity is first created.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//clickSound();
		setContentView(R.layout.help_layout);
	}

	// This function handles the case when back key is pressed
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent Menu = new Intent(HelpActivity.this, MainActivity.class);
			startActivity(Menu);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}