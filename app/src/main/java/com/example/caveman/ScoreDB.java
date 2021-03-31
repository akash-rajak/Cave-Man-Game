//This java code is responsible for holding the highscores achieved on each level using data base

package com.example.caveman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ScoreDB extends SQLiteOpenHelper {

    public static final String PLAYER_TABLE = "PLAYER_TABLE";
    public static final String COLUMN_PLAYER_SCORE = "PLAYER_SCORE";
    public static final String COLUMN_ID = "ID";

    public ScoreDB(@Nullable Context context) {
        super(context, "SCORE.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + PLAYER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PLAYER_SCORE + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(PlayerModel playermodel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PLAYER_SCORE,playermodel.getPlayer_score());

        long insert = db.insert(PLAYER_TABLE, null, cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteOne(PlayerModel playerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PLAYER_TABLE +  " WHERE " + COLUMN_ID + " = " + playerModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public List<PlayerModel> getEveryone(){
        List<PlayerModel> returnList = new ArrayList<>();

        //get the data from the database
        String queryString = "SELECT * FROM " + PLAYER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);// Cursor is the return type in rawQuery
        if(cursor.moveToFirst()){
            do{
                int playerID = cursor.getInt(0);
                int playerScore = cursor.getInt(1);
                PlayerModel newPlayer = new PlayerModel(playerID,playerScore);
                returnList.add(newPlayer);
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}