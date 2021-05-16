// This java code is just for crediting part

package com.example.caveman;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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
		mp.start();// media player is started
	}

	private ViewFlipper mFlipper;//A flipper to switch between the credits

	ToggleButton togglebutton;// a togglebutton variable
	Button goodbutton;
	Button badbutton;

	// This function is called when the activity is first created. ----------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//clickSound();
		setContentView(R.layout.credits_layout);// content of credits_layout.xml are set, on credits page
		mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
		mFlipper.startFlipping();

		togglebutton = findViewById(R.id.toggle4);
		togglebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(togglebutton.isChecked()){
					//Toast.makeText(OptionsActivity.this,"On", Toast.LENGTH_SHORT).show();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.bstd);
					mp.start();
				}
				else{
					mp.stop();// when backkey is pressed , the music stopped
					mp.release();// and also music is released
					//Toast.makeText(OptionsActivity.this,"Off", Toast.LENGTH_SHORT).show();
				}
			}
		});

		TextView textView = (TextView)findViewById(R.id.textView);
		textView.setMovementMethod(LinkMovementMethod.getInstance());

		goodbutton = findViewById(R.id.Good);
		goodbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast toast;
				toast = Toast.makeText(getApplicationContext(),"\uD83D\uDCCC Thanks for the Feedback. We are glad that you have positive experience with game.",Toast.LENGTH_SHORT);
				TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
				toastMessage.setTextSize(30);
				toastMessage.setTextColor(Color.GREEN);
				View view = toast.getView();
				view.setBackgroundColor(Color.BLACK);
				toast.show();
			}
		});

		badbutton = findViewById(R.id.Bad);
		badbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast toast;
				toast = Toast.makeText(getApplicationContext(),"\uD83D\uDCCC Thanks for the Feedback. We are sorry that you are having problem with game, Please give detailed feedback on Play Store review.",Toast.LENGTH_SHORT);
				TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
				toastMessage.setTextSize(30);
				toastMessage.setTextColor(Color.GREEN);
				View view = toast.getView();
				view.setBackgroundColor(Color.BLACK);
				toast.show();
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
//			mp.stop();// when backkey is pressed , the music stopped
//			mp.release();// and also music is released
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}