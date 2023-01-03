package com.example.youarecoolapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserStats.db";

    private static final String TABLE_NAME = "user_stats";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TIME = "_time";
    private static final String COLUMN_TIME_DIFFERENCE = "difference_time";
    private static final String COLUMN_MESSAGE = "user_message";
    private static final String COLUMN_KARMA_INCOME = "got_karma";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MESSAGE + " TEXT, " +
                        COLUMN_TIME + " TEXT, " +
                        COLUMN_TIME_DIFFERENCE + " TEXT, " +
                        COLUMN_KARMA_INCOME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    void addHistory(String msg, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MESSAGE, msg);
    }
}
