package com.example.android.inventoryappstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDbHelper extends SQLiteOpenHelper {

    //Name of the database
    private static final String DATABASE_NAME = "inventory.db";
    //Database version
    private static final int DATABASE_VERSION = 1;

    //New instance
    public ItemDbHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    //Called the first time the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " + ItemRegister.ItemEntry.TABLE_NAME + "("
                + ItemRegister.ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ItemRegister.ItemEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL,"
                + ItemRegister.ItemEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL DEFAULT 0,"
                + ItemRegister.ItemEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0,"
                + ItemRegister.ItemEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + ItemRegister.ItemEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL);";

        //Execute the SQL statement
        db.execSQL(SQL_CREATE_ITEMS_TABLE);
    }

    //Called when the database is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // The database is still at version 1, so there's nothing to do be done here.
    }

}
