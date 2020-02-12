package com.my.zhomprass_java.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.my.zhomprass_java.Models.SingleProduct;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "order_cart";
    private static String TABLE_NAME = "new_table";
    public static String ID = "id";
    public static String CAT_ID = "cat_id";
    public static String SUB_CAT_ID = "sub_cat_id";
    public static String THIRD_CAT_ID = "third_cat_id";
    public static String PRODUCT_NAME = "product_name";
    public static String PRICE = "price";
    public static String POINT = "point";
    public static String QUANTITY = "quantity";
    public static String IMAGE = "image";
    private static int VERSION = 4;
    private Context context;
    private String table = "CREATE TABLE "+ TABLE_NAME+" ("+ ID+" INTEGER,"+ CAT_ID+" INTEGER,"
            + SUB_CAT_ID+" INTEGER,"+ THIRD_CAT_ID+" INTEGER,"+ PRODUCT_NAME+" VARCHAR(255),"
            + PRICE+" INTEGER,"+ POINT+" INTEGER,"+ QUANTITY+" INTEGER,"+ IMAGE+" VARCHAR)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public void addToCart(SingleProduct relatedProduct) {

        SQLiteDatabase sq = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, relatedProduct.getId());
        values.put(CAT_ID, relatedProduct.getCat_id());
        values.put(SUB_CAT_ID, relatedProduct.getSub_cat_id());
        values.put(THIRD_CAT_ID, relatedProduct.getThird_cat_id());
        values.put(PRODUCT_NAME, relatedProduct.getProduct_name());
        values.put(PRICE, relatedProduct.getPrice());
        values.put(POINT, relatedProduct.getPoint());
        values.put(QUANTITY, 1);
        values.put(IMAGE, relatedProduct.getImage());
        sq.insert(TABLE_NAME, null, values);

    }

    public void addQuantity(int id, int quantity) {

        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update "+TABLE_NAME+" set "+QUANTITY+" = "+quantity+" where "+ID+" = "+id);
        db.close();
    }

    public void deleteData(int id) {
        getWritableDatabase().delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
    }

    public boolean checkCart(int id) {

        SQLiteDatabase sq = getReadableDatabase();
        Cursor cursor = sq.rawQuery("select " + ID + " from " + TABLE_NAME + " where " + ID + " = " + id, (String[]) null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public Cursor getCart() {
        String sql = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }
    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}
