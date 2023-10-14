package com.alyhassan.caveman.gui;
import com.alyhassan.caveman.R;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * 
 * @author Aly
 *
 */
public class LevelScreen extends Activity implements OnClickListener{

	private Button[][] button;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		TableLayout table = new TableLayout(this);		
		
		button = new Button[5][4];
		TableRow[] row = new TableRow[button.length];
		int count = 1;
		
		Drawable dr = getResources().getDrawable(R.drawable.button);
		Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
			
		for(int i=0;i<button.length;i++){
			row[i] = new TableRow(this);
			row[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
			for(int j=0;j<button[0].length;j++){
				button[i][j] = new Button(this);
				button[i][j].setText(count+"");
				button[i][j].setBackgroundColor(Color.argb(127,220,230,0));
				button[i][j].setPadding(20, 20,20, 20);
				button[i][j].setTextSize(32);				
				button[i][j].setOnClickListener(this);
				row[i].addView(button[i][j]);			
				count++;
			}
			table.addView(row[i]);
		}
		
/*		Drawable d = new BitmapDrawable(Bitmap.createScaledBitmap(bitmap, 40, 40, true));
				
		for(int i=0;i<button.length;i++)
			for(int j=0;j<button[0].length;j++)
				button[i][j].setBackgroundDrawable(d);
*/				
		
		table.setPadding(10, 30, 10, 20);
		layout.addView(table);
		layout.setBackgroundResource(R.drawable.back_main);
		
		setContentView(layout);
		
	}

	
	
	
	@Override
	protected void onDestroy() {
		this.finish();
		super.onDestroy();
	}




	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
