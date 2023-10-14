package com.alyhassan.caveman.model;

import java.util.LinkedList;

public class MapGroup {
	
	private LinkedList<Boolean> maps;
	private int position;
	
	/**
	 * Creates a map group with the number of given maps.
	 * Note: The first map (0) is set as the current one.
	 * @param levels -  Number of maps in the group
	 */
	public MapGroup(int numMaps){
		maps = new LinkedList<Boolean>();
		addMaps(numMaps);
		unlockMap(0);
		position = 0;
	}
	
	/**
	 * Adds "locked" maps to the linked list of maps.
	 */
	public void addMaps(int n){
		for(int i=0;i<n;i++)
			maps.offer(false);
	}
			
	/**
	 * Checks if the current map being played is the last one
	 * @param position - Current map being played
	 * @return true if it is the last map
	 */
	public boolean isLast(int position){
		return position==maps.size()-1;
	}

	/**
	 * Unlocks the next map and changes the current map position
	 */
	public void unlockNext(){
		maps.set(position+1, true);
		position++;
	}
	
	/**
	 * Unlocks the next <i>n</i> map and changes the current map position
	 */
	public void unlockNext(int n){
		for(int i=1;i<=n && position<maps.size()-1;i++){
			position++;
			maps.set(position, true);
			
		}		
	}
	
	/**
	 * Checks if a map is unlocked
	 * @param map - The map to check
	 * @return true if it is unlocked
	 */
	public boolean isUnlocked(int map){
		if(map < maps.size())
			return maps.get(map);
		return false;
	}
	
	/**
	 * Unlocks a specific map
	 * @param map - Map to unlock
	 */
	public void unlockMap(int map){
		if(map < maps.size())
			maps.set(map, true);
	}
	
	/**
	 * @return A String with information about each map
	 */
	public String toString(){
		String temp = "";
		for(int i =0;i<maps.size();i++)
			temp+="Map"+i+":"+maps.get(i)+" ";
		return temp;
	}	
}
