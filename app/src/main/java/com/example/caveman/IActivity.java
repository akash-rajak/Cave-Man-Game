package com.example.caveman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class IActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i_layout);
    }

    // When the back key is pressed, this Activity finishes and the menu button are visible to us
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent Menu = new Intent(IActivity.this, MainActivity.class);
            startActivity(Menu);
            finish();
//			mp.stop();// when backkey is pressed , the music stopped
//			mp.release();// and also music is released
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
