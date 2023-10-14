package com.alyhassan.caveman.gui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.alyhassan.caveman.R;

public class AboutPage extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
				
		TextView title = (TextView) findViewById(R.id.TextView01);
		title.setTypeface(tf,Typeface.BOLD);
		
		Log.d("caveman", "About screen loaded");
	}
}
