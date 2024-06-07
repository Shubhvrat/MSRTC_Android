package com.example.msrtc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase database;
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "bus.db";
    private static final int DATABASE_VERSION = 2;  // Incremented the database version
    private static final String TABLE_NAME = "msrtc";
    private static final String COLUMN_ID = "pid";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_NAME_BOOK = "book_ride";
    private static final String BOOK_NAME = "book_name";
    private static final String BOOK_ADDRESS = "book_address";
    private static final String PICKUP = "pickup";
    private static final String DESTINATION = "destination";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate called");

        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT);";


        String createBookTableQuery = "CREATE TABLE " + TABLE_NAME_BOOK + " (" +
                BOOK_NAME + " TEXT, " +
                BOOK_ADDRESS + " TEXT, " +
                PICKUP + " TEXT, " +
                DESTINATION + " TEXT);";

        sqLiteDatabase.execSQL(createTableQuery);
        sqLiteDatabase.execSQL(createBookTableQuery);
        Log.d(TAG, "Table created with query: " + createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade called from version " + oldVersion + " to " + newVersion);
        // This will drop the old table and call onCreate to create a new one
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser(String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        long res = database.insert(TABLE_NAME, null, cv);

        if (res == -1) {
            Log.d(TAG, "Insert failed for email: " + email);
            return false;
        } else {
            Log.d(TAG, "Insert succeeded for email: " + email);
            return true;
        }
    }


    public boolean insertBookUser(String bookname, String book_addr , String pickup , String destination) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOK_NAME, bookname);
        cv.put(BOOK_ADDRESS, book_addr);
        cv.put(PICKUP , pickup);
        cv.put(DESTINATION , destination);

        long res = database.insert(TABLE_NAME_BOOK, null, cv);

        if (res == -1) {
            Log.d(TAG, "Insert failed for email: " + bookname);
            return false;
        } else {
            Log.d(TAG, "Insert succeeded for email: " + book_addr);
            return true;
        }
    }

}
