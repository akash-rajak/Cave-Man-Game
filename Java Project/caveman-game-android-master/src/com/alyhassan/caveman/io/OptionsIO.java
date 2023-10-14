package com.alyhassan.caveman.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.alyhassan.caveman.options.Options;

import android.os.Environment;
import android.util.Log;

public class OptionsIO {
	

	public static void saveOptions(){
		try {
			File root = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.alyhassan.caveman/");
	        if(!root.exists())
	        	root.mkdirs();
	        File file = new File(root, "options.cave");
			
			PrintWriter out = new PrintWriter(file);

			out.println(Options.inverseVertical?"true":"false");
			out.println(Options.inverseHorizontal?"true":"false");
			out.println(Options.sensitivity);
			
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {}
	}
	
	public static void loadOptions(){
		File root = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.alyhassan.caveman/");
		
        File file = new File(root, "options.cave");
        
        if(!file.exists())
        	return;
        
        try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    
		    Options.inverseVertical = br.readLine().equals("true");
		    Options.inverseHorizontal = br.readLine().equals("true");
		    Options.sensitivity = Integer.parseInt(br.readLine());
		    		    
		    Log.d("caveman", "Read saved options successfully");
		}catch (IOException e) {}

	}

}
