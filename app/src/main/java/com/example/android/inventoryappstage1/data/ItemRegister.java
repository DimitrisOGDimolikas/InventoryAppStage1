package com.example.android.inventoryappstage1.data;

import android.provider.BaseColumns;

public class ItemRegister {

    // To prevent someone from accidentally instantiating the class
    private ItemRegister() {}

    public static abstract class ItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "Inventory";
        public static final String _ID = BaseColumns._ID ;
        public static final String COLUMN_PRODUCT_NAME = "ProductName";
        public static final String COLUMN_PRODUCT_PRICE = "Price";
        public static final String COLUMN_PRODUCT_QUANTITY = "Quantity";
        public static final String COLUMN_SUPPLIER_NAME = "SupplierName";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "SupplierPhoneNumber";

    }
}
