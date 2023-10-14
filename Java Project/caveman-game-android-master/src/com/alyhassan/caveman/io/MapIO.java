package com.alyhassan.caveman.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.alyhassan.caveman.R;
import com.alyhassan.caveman.model.Boardx;

import android.os.Environment;
import android.util.Log;

public class MapIO {
		
	public static Boardx getBoard(String name){
		int[][] tiles = new int[Boardx.BOARD_ROWS][Boardx.BOARD_COLS];
		
		File root = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.alyhassan.caveman/");
		
		if(!name.endsWith(".map"))
			name+=".map";
		
        File file = new File(root, name);

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    
		    for(int i=0;i<Boardx.BOARD_ROWS;i++){
		    	line = br.readLine();
		    	for(int j=0;j<Boardx.BOARD_COLS;j++)		    		
		    		tiles[i][j] = Integer.parseInt(line.substring(j,j+1));
		    }
		    
		   return new Boardx(tiles);
		    
		}catch (IOException e) {return new Boardx();}
	}
	
	public static Boardx readBoard(InputStream in){
//		try {
//			in.skip(Boardx.BOARD_COLS*Boardx.BOARD_ROWS + Boardx.BOARD_COLS);
//		} catch (IOException e1) {}
		
		int[][] tiles = new int[Boardx.BOARD_ROWS][Boardx.BOARD_COLS];
		try {
			for(int i=0;i<Boardx.BOARD_ROWS;i++){	    	
		    	for(int j=0;j<Boardx.BOARD_COLS;j++){
		    		int num = (in.read()) - 48;
					if(num >= 0)
		    			tiles[i][j] = num;
		    	}
		    	in.read();
		    }
			return new Boardx(tiles);
		} catch (IOException e) {}
		
	    return new Boardx();
	}
}
