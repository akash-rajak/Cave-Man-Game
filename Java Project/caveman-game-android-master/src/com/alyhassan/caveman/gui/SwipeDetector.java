package com.alyhassan.caveman.gui;

import com.alyhassan.caveman.options.Options;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {
	
	private final String logTag = "ActivitySwipeDetector";
	private PlayView view;
	private int minDistance = 100;
	private float downX, downY, upX, upY;
	
	public SwipeDetector(PlayView v){
	    this.view = v;
	    minDistance = (100-Options.sensitivity)*3 + 50;
	}
	
	public void onRightToLeftSwipe(){
	    Log.i(logTag, "RightToLeftSwipe!");
	    if(Options.inverseHorizontal)
	    	view.swiped(PlayView.RIGHT);
	    else
	    	view.swiped(PlayView.LEFT);
	}
	
	public void onLeftToRightSwipe(){
	    Log.i(logTag, "LeftToRightSwipe!"); 
	    if(Options.inverseHorizontal)
	    	view.swiped(PlayView.LEFT);
	    else
	    	view.swiped(PlayView.RIGHT);
	}
	
	public void onTopToBottomSwipe(){
	    Log.i(logTag, "onTopToBottomSwipe!");
	    if(Options.inverseVertical)
	    	view.swiped(PlayView.UP);
	    else
	    	view.swiped(PlayView.DOWN);
	}
	
	public void onBottomToTopSwipe(){
	    Log.i(logTag, "onBottomToTopSwipe!");
	    if(Options.inverseVertical)
	    	view.swiped(PlayView.DOWN);
	    else
	    	view.swiped(PlayView.UP);
	}
	
	public boolean onTouch(View v, MotionEvent event) {
	    switch(event.getAction()){
	        case MotionEvent.ACTION_DOWN: {
	            downX = event.getX();
	            downY = event.getY();
	            return true;
	        }
	        case MotionEvent.ACTION_UP: {
	            upX = event.getX();
	            upY = event.getY();
	
	            float deltaX = downX - upX;
	            float deltaY = downY - upY;
	
	            // swipe horizontal?
	            if(Math.abs(deltaX) > minDistance){
	                if(deltaX < 0) { this.onLeftToRightSwipe(); return true; }
	                if(deltaX > 0) { this.onRightToLeftSwipe(); return true; }
	            }
	            else {
	                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + minDistance);
	            }
	
	            // swipe vertical?
	            if(Math.abs(deltaY) > minDistance){
	                if(deltaY < 0) { this.onTopToBottomSwipe(); return true; }
	                if(deltaY > 0) { this.onBottomToTopSwipe(); return true; }
	            }
	            else {
	                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + minDistance);
	            }
	            
	            return false;
	        }
	    }
	    return false;
	}

}