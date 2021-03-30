// This java code is just for crediting part

package com.example.caveman;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class CreditsActivity extends Activity {
	// Creating Media Player to play any sound or music -------------------------------------------------------------
	private MediaPlayer mp;//Creates a new MediaPlayer to play any kind of sound
	private void clickSound() {// this function Plays a sound when a button is clicked.
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(), R.raw.music2);
		mp.start();
	}

	private ViewFlipper mFlipper;//A flipper to switch between the credits

	ToggleButton togglebutton;

	// This function is called when the activity is first created. ----------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//clickSound();
		setContentView(R.layout.credits_layout);
		mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
		mFlipper.startFlipping();

		togglebutton = findViewById(R.id.toggle4);
		togglebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(togglebutton.isChecked()){
					//Toast.makeText(OptionsActivity.this,"On", Toast.LENGTH_SHORT).show();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.final_masquerade);
					mp.start();
				}
				else{
					mp.stop();
					mp.release();
					//Toast.makeText(OptionsActivity.this,"Off", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	// When the back key is pressed, this Activity finishes and the menu button are visible to us
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent Menu = new Intent(CreditsActivity.this, MainActivity.class);
			startActivity(Menu);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}