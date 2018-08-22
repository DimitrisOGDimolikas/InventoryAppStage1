package com.example.android.inventoryappstage1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.inventoryappstage1.data.ItemDbHelper;
import com.example.android.inventoryappstage1.data.ItemRegister;

public class MainActivity extends AppCompatActivity {

    private ItemDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new ItemDbHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
            displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.

        String[] projection = {
                ItemRegister.ItemEntry._ID,
                ItemRegister.ItemEntry.COLUMN_PRODUCT_NAME,
                ItemRegister.ItemEntry.COLUMN_PRODUCT_PRICE,
                ItemRegister.ItemEntry.COLUMN_PRODUCT_QUANTITY,
                ItemRegister.ItemEntry.COLUMN_SUPPLIER_NAME,
                ItemRegister.ItemEntry.COLUMN_SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor = db.query(
                ItemRegister.ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_product);

        try {
            displayView.setText("The table contains " + cursor.getCount() + " items.\n\n");
            displayView.append(ItemRegister.ItemEntry._ID + " - " +
                    ItemRegister.ItemEntry.COLUMN_PRODUCT_NAME + " - " +
                    ItemRegister.ItemEntry.COLUMN_PRODUCT_PRICE + " - " +
                    ItemRegister.ItemEntry.COLUMN_PRODUCT_QUANTITY + " - " +
                    ItemRegister.ItemEntry.COLUMN_SUPPLIER_NAME + " - " +
                    ItemRegister.ItemEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ItemRegister.ItemEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(ItemRegister.ItemEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ItemRegister.ItemEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ItemRegister.ItemEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(ItemRegister.ItemEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(ItemRegister.ItemEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(productNameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                int currentSupplierPhoneNumber = cursor.getInt(supplierPhoneNumberColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhoneNumber));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
