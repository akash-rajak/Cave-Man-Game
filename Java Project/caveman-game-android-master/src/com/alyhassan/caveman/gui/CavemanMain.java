package com.alyhassan.caveman.gui;

import com.alyhassan.caveman.R;
import com.alyhassan.caveman.io.OptionsIO;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CavemanMain extends Activity implements View.OnClickListener {
	
	private Button play, options, about;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		OptionsIO.loadOptions();
		
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ArchitectsDaughter.ttf");
                
        play = (Button) findViewById(R.id.play);
        play.setTypeface(tf);
	    play.setOnClickListener(this);
	    
	    options = (Button) findViewById(R.id.options);
        options.setTypeface(tf);
        options.setOnClickListener(this);
        
        about = (Button) findViewById(R.id.about);
        about.setTypeface(tf);
        about.setOnClickListener(this);
    }
    
    public void onClick(View v){
    	if(v == play){
    		startActivity(new Intent("com.alyhassan.caveman.PLAYGAME"));
        	Log.d("caveman", "Play Game Selected");
    	}else if(v == options){
    		startActivity(new Intent("com.alyhassan.caveman.OPTIONS"));
        	Log.d("caveman", "Options Selected");
    	}else{
    		startActivity(new Intent("com.alyhassan.caveman.ABOUT"));
        	Log.d("caveman", "About Selected");
    	}
    }
    
}