package com.alyhassan.caveman.options;

import com.alyhassan.caveman.R;
import com.alyhassan.caveman.io.OptionsIO;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class OptionsMain extends Activity implements OnClickListener, OnSeekBarChangeListener{
	
	private CheckBox vertBox, horzBox;
	private SeekBar bar;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.options);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
		
		vertBox = (CheckBox) findViewById(R.id.vertical);
		vertBox.setTypeface(tf,Typeface.BOLD);
		if(Options.inverseVertical)
			vertBox.setChecked(true);
		vertBox.setOnClickListener(this);

		horzBox = (CheckBox) findViewById(R.id.horizontal);
		horzBox.setTypeface(tf,Typeface.BOLD);
		if(Options.inverseHorizontal)
			horzBox.setChecked(true);
		horzBox.setOnClickListener(this);
		
		bar = (SeekBar) findViewById(R.id.sensitivity);
		bar.setOnSeekBarChangeListener(this);
		bar.setProgress(Options.sensitivity);
	
		TextView text = (TextView) findViewById(R.id.senseText);
		text.setTypeface(tf);
		
		TextView title = (TextView) findViewById(R.id.TextView01);
		title.setTypeface(tf,Typeface.BOLD);
		
		Log.d("caveman", "Options menu loaded");
	}

	public void onClick(View v) {
		
		if(v instanceof CheckBox){
			CheckBox action = (CheckBox)v;
			Log.d("caveman", "CheckBox state changed to : "+action.isChecked());
			
			if(action == vertBox){
				if(action.isChecked())
					Options.inverseVertical = true;
				else
					Options.inverseVertical = false;
			}else if(action == horzBox){
				if(action.isChecked())
					Options.inverseHorizontal = true;
				else
					Options.inverseHorizontal = false;
			}				
		}			
	}

	@Override
	protected void onPause() {
		Log.d("caveman", "Options Paused");
		OptionsIO.saveOptions();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		Log.d("caveman", "Options destroyed");
		OptionsIO.saveOptions();		
		super.onDestroy();
	}

	@Override
	public void onProgressChanged(SeekBar s, int amount, boolean touch) {
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar s) {
		Options.sensitivity = s.getProgress();
	}
	
	
	
}
