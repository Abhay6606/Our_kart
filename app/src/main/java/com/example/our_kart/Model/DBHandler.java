package com.example.our_kart.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.our_kart.Domain.DBGetterSetter;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "prrrooo";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "myproo";
    private static final String SN_NO = "id";
    private static final String ITEM_COUNT = "itemCount";
    private static final String TITLE_COL = "title";
    private static final String IMAGE_COL = "image";
    private static final String TOTAL_PRICE = "totalPrice";
    private static final String PRICE_COL = "price";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + SN_NO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE_COL + " TEXT NOT NULL,"
                + PRICE_COL + " TEXT NOT NULL,"
                + IMAGE_COL + " TEXT NOT NULL,"
                + ITEM_COUNT + " INTEGER NOT NULL,"
                + TOTAL_PRICE + " INTEGER NOT NULL " + ")";
        db.execSQL(query);

    }

    public void addNewProduct(String title, String price, String image, String itemCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE_COL, title);
        values.put(PRICE_COL, price);
        values.put(IMAGE_COL, image);
        values.put(ITEM_COUNT, itemCount);
        values.put(TOTAL_PRICE,price);
        db.insert(TABLE_NAME, null, values);
        db.close();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<DBGetterSetter> callProducts() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<DBGetterSetter> dbProductsArraylist = new ArrayList<>();

        Log.d("DataCheck", String.valueOf(cursor.moveToNext()));

        if (cursor.moveToFirst()) {
            Log.d("DataCheck", "InsideMoveto First");
            do {
                DBGetterSetter pack = new DBGetterSetter();
                pack.setSnNo(Integer.parseInt(cursor.getString(0)));
                pack.setTitle(cursor.getString(1));
                pack.setPrice(cursor.getString(2));
                pack.setImage(cursor.getString(3));
                pack.setItemCount(Integer.parseInt(cursor.getString(4)));
                pack.setTotalPrice( Double.parseDouble(cursor.getString(5)));
                dbProductsArraylist.add(pack);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dbProductsArraylist;
    }

    public void updateData(int id, String title, String price, String image, String itemCount) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SN_NO, id);
        values.put(TITLE_COL, title);
        values.put(PRICE_COL, price);
        values.put(IMAGE_COL, image);
        values.put(ITEM_COUNT, itemCount);
        double updtePrice = Double.parseDouble(price )*Double.parseDouble(itemCount);
        String updatestr= String.valueOf(updtePrice);
        values.put(TOTAL_PRICE,updatestr);

        db.update(TABLE_NAME, values, SN_NO + "=?", new String[]{String.valueOf(id)});
        db.close();

    }

    public void deleteData(int id) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        db.delete(TABLE_NAME, SN_NO + "=?", new String[]{String.valueOf(id)});
        db.close();

    }


}