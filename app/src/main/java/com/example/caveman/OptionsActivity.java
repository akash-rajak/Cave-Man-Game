// This java code allows the user to see various option =========================================================

package com.example.caveman;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class OptionsActivity extends Activity implements AnimationListener {
	//private Button rateButton;

	// Creating Media Player to play any sound or music -------------------------------------------------------------
	private MediaPlayer mp;//Creates a new MediaPlayer to play any kind of sound
	private void clickSound() {// this function Plays a sound when a button is clicked.
		if (mp != null) {
			mp.release();
		}
		mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
		mp.start();
	}

	RatingBar ratingBar;
	Button ratebutton;
	ToggleButton togglebutton;

	private void createOptionButtons() {
		final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.clicked);

		// for GameButton ----------------------
		ratebutton = (Button) findViewById(R.id.Rate);
		ratebutton.startAnimation(AnimationUtils.loadAnimation(OptionsActivity.this, R.anim.slide_down));
		ratebutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				clickSound();
				ratebutton.startAnimation(animation1);
				animation1.setAnimationListener(OptionsActivity.this);
			}
		});
	}

	// This function is called when the activity is first created.
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//clickSound();
		setContentView(R.layout.options_layout);
		createOptionButtons();

		ratingBar = findViewById(R.id.rating_bar);
		ratebutton = findViewById(R.id.Rate);

		ratebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String s = String.valueOf(ratingBar.getRating());
				Toast.makeText(getApplicationContext(),"Rated : " + s + " STAR ",Toast.LENGTH_SHORT).show();
			}
		});

		togglebutton = findViewById(R.id.toggle1);
		togglebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(togglebutton.isChecked()){
					//Toast.makeText(OptionsActivity.this,"On", Toast.LENGTH_SHORT).show();
					mp = MediaPlayer.create(getApplicationContext(), R.raw.believer);
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

	// This function handles the condition when backbutton is clicked
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent Menu = new Intent(OptionsActivity.this, MainActivity.class);
			startActivity(Menu);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onAnimationStart(Animation animation) {

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {

	}
}