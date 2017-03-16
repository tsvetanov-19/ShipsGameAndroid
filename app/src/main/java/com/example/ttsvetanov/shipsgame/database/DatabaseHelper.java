package com.example.ttsvetanov.shipsgame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ttsvetanov.shipsgame.database.Game;
import com.example.ttsvetanov.shipsgame.database.Ships;

/**
 * Created by ttsvetanov on 24.02.17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String DB_NAME = "shipsgame.db";
    public static final int DB_VERSION = 1;
    public static final String ERROR_TAG = "error";

    //Table names
    public static final String TABLE_GAME = "game";
    public static final String TABLE_SHIPS = "ships";

    //table game
    public static final String COLUMN_GAME_ID = "_id";
    public static final String COLUMN_GAME_DATE = "date";
    public static final String COLUMN_GAME_RESULT = "result";
    public static final String COLUMN_GAME_TURNS = "turns";

    //table ships
    public static final String COLUMN_SHIP_ID = "_id";

    public static final String COLUMN_SHIP_1 = "ship1";
    public static final String COLUMN_SHIP_2 = "ship2";
    public static final String COLUMN_SHIP_3 = "ship3";
    public static final String COLUMN_SHIP_4 = "ship4";
    public static final String COLUMN_SHIP_5 = "ship5";


    public static final String COLUMN_SHIP_1_ORIENTATION = "orientation_ship1";
    public static final String COLUMN_SHIP_2_ORIENTATION = "orientation_ship2";
    public static final String COLUMN_SHIP_3_ORIENTATION = "orientation_ship3";
    public static final String COLUMN_SHIP_4_ORIENTATION = "orientation_ship4";
    public static final String COLUMN_SHIP_5_ORIENTATION = "orientation_ship5";

    public static final String COLUMN_MAX_SHOTS = "max_shots";

    public static final String CREATE_TABLE_GAME =
            "CREATE TABLE " + TABLE_GAME + "(" +
                    "'" + COLUMN_GAME_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "'" + COLUMN_GAME_DATE + "' DATETIME NOT NULL," +
                    "'" + COLUMN_GAME_TURNS + "' INTEGER NOT NULL," +
                    "'" + COLUMN_GAME_RESULT + "'  TEXT NULL)";

    public static final String CREATE_TABLE_SHIPS =
            "CREATE TABLE " + TABLE_SHIPS + "(" +
                    "`" + COLUMN_SHIP_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "`" + COLUMN_SHIP_1 + "` INTEGER," +
                    "`" + COLUMN_SHIP_2 + "` INTEGER," +
                    "`" + COLUMN_SHIP_3 + "` INTEGER," +
                    "`" + COLUMN_SHIP_4 + "` INTEGER," +
                    "`" + COLUMN_SHIP_5 + "` INTEGER," +
                    "`" + COLUMN_SHIP_1_ORIENTATION + "` INTEGER," +
                    "`" + COLUMN_SHIP_2_ORIENTATION + "` INTEGER," +
                    "`" + COLUMN_SHIP_3_ORIENTATION + "` INTEGER," +
                    "`" + COLUMN_SHIP_4_ORIENTATION + "` INTEGER," +
                    "`" + COLUMN_SHIP_5_ORIENTATION + "` INTEGER," +
                    "`" + COLUMN_MAX_SHOTS + "` INTEGER NOT NULL DEFAULT 25)";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_GAME);
        sqLiteDatabase.execSQL(DatabaseHelper.CREATE_TABLE_SHIPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.TABLE_GAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.TABLE_SHIPS);
        onCreate(sqLiteDatabase);
    }

    public void insertNewGame(Game game) {
        try {
            db = getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(DatabaseHelper.COLUMN_GAME_ID, game.get_id());
            cv.put(DatabaseHelper.COLUMN_GAME_RESULT, game.getResult());
            cv.put(DatabaseHelper.COLUMN_GAME_DATE, game.getDate());
            cv.put(DatabaseHelper.COLUMN_GAME_TURNS, game.getTurns());

            db.insertOrThrow(DatabaseHelper.TABLE_GAME, null, cv);

        }catch (SQLException e){
            Log.wtf(ERROR_TAG, e.getMessage());
        }finally {
            if(db != null)
                db.close();
        }
    }

    public Cursor getGameData() {
        try{
            db = getReadableDatabase();
            String query = "SELECT date, result, turns FROM " + DatabaseHelper.TABLE_GAME;
            return db.rawQuery(query, null);

        }catch (SQLException e){
            Log.wtf(ERROR_TAG, e.getMessage());
        }
        return null;
    }

    public String insertShipsData(Ships ships) {
        String status = "saved";

        try {
            db = getWritableDatabase();

            ContentValues cv = new ContentValues();
//            cv.put(COLUMN_SHIP_ID, ships.get_id());
            cv.put(DatabaseHelper.COLUMN_SHIP_1, ships.getShip1());
            cv.put(DatabaseHelper.COLUMN_SHIP_2, ships.getShip2());
            cv.put(DatabaseHelper.COLUMN_SHIP_3, ships.getShip3());
            cv.put(DatabaseHelper.COLUMN_SHIP_4, ships.getShip4());
            cv.put(DatabaseHelper.COLUMN_SHIP_5, ships.getShip5());;

            cv.put(DatabaseHelper.COLUMN_SHIP_1_ORIENTATION, ships.getShip1_orientation());
            cv.put(DatabaseHelper.COLUMN_SHIP_2_ORIENTATION, ships.getShip2_orientation());
            cv.put(DatabaseHelper.COLUMN_SHIP_3_ORIENTATION, ships.getShip3_orientation());
            cv.put(DatabaseHelper.COLUMN_SHIP_4_ORIENTATION, ships.getShip4_orientation());
            cv.put(DatabaseHelper.COLUMN_SHIP_5_ORIENTATION, ships.getShip5_orientation());
            cv.put(DatabaseHelper.COLUMN_MAX_SHOTS, ships.getMaxGameShots());


            db.insertOrThrow(DatabaseHelper.TABLE_SHIPS, null, cv);

        }catch (SQLException e){
            status = e.getMessage();

            Log.wtf(ERROR_TAG, status);
        }finally {
            if(db != null)
                db.close();
        }

        return status;
    }

    public Cursor getShipsData() {

        try{
            db = getReadableDatabase();
            String query = "SELECT * FROM " + DatabaseHelper.TABLE_SHIPS;
            return db.rawQuery(query, null);

        }catch (SQLException e){
            Log.wtf(ERROR_TAG, e.getMessage());
        }
        return null;
    }
}
