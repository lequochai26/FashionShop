package org.lequochai.fashionshop.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.lequochai.fashionshop.entities.RestCookie;

import java.util.ArrayList;
import java.util.List;

public class FashionShopDBHelper extends SQLiteOpenHelper {
//    Static fields:
    public static final String TAG = "SQLite";
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "FashionShop";
    public static final String TABLE_COOKIE_NAME = "Cookie";
    public static final String TABLE_COOKIE_COOKIEKEY = "cookieKey";
    public static final String TABLE_COOKIE_COOKIEVALUE = "cookieValue";
    public static final String TABLE_COOKIE_CREATE = "CREATE TABLE " + TABLE_COOKIE_NAME +  "(" +
            TABLE_COOKIE_COOKIEKEY + " TEXT PRIMARY KEY," +
            TABLE_COOKIE_COOKIEVALUE + "TEXT NOT NULL)";

    public static final String TABLE_COOKIE_DROP = "DROP TABLE IF EXISTS " + TABLE_COOKIE_NAME;

//    Constructors:
    public FashionShopDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

//    Methods:
    @Override
    public void onCreate(SQLiteDatabase db) {
//        Create Cookies database
        db.execSQL(TABLE_COOKIE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Drop old tables
        db.execSQL(TABLE_COOKIE_DROP);

//        Re-create DB
        onCreate(db);
    }

    public List<RestCookie> getAllCookies() {
//        Get DB
        SQLiteDatabase db = this.getWritableDatabase();

//        Query
        Cursor cursor = db.query(
                TABLE_COOKIE_NAME, new String[]{ TABLE_COOKIE_COOKIEKEY, TABLE_COOKIE_COOKIEVALUE }, null, null, null,
                null, null
        );

//        Result initialize
        List<RestCookie> result = new ArrayList<>();

//        Getting
        while (cursor.moveToNext()) {
            result.add(
                    new RestCookie(
                            cursor.getString(0),
                            cursor.getString(1)
                    )
            );
        }

//        Close cursor and db
        cursor.close();
        db.close();

        return result;
    }

    public RestCookie getCookie(String cookieKey) {
//        Get db
        SQLiteDatabase db = this.getWritableDatabase();

//        Query
        Cursor cursor = db.query(
                        TABLE_COOKIE_NAME, new String[]{ TABLE_COOKIE_COOKIEKEY,
                        TABLE_COOKIE_COOKIEVALUE },
                TABLE_COOKIE_COOKIEKEY + "=?",
                new String[]{ cookieKey },
                null,
                        null, null
                );

//        Target declaration
        RestCookie target = null;

//        Found case
        if (cursor.moveToNext()) {
            target = new RestCookie(
                    cursor.getString(0),
                    cursor.getString(1)
            );
        }

//        Close cursor and db
        cursor.close();
        db.close();

//        Return target
        return target;
    }

    public void insertCookie(RestCookie cookie) {
//        Get DB
        SQLiteDatabase db = this.getWritableDatabase();

//        Create content values
        ContentValues values = new ContentValues();
        values.put(TABLE_COOKIE_COOKIEKEY, cookie.getCookieKey());
        values.put(TABLE_COOKIE_COOKIEVALUE, cookie.getCookieValue());

//        Insert
        db.insert(TABLE_COOKIE_NAME, null, values);

//        Close db
        db.close();
    }

    public void updateCookie(RestCookie cookie) {
//        Get DB
        SQLiteDatabase db = this.getWritableDatabase();

//        Create content values
        ContentValues values = new ContentValues();
        values.put(TABLE_COOKIE_COOKIEVALUE, cookie.getCookieValue());

//        Update
        db.update(TABLE_COOKIE_NAME, values, TABLE_COOKIE_COOKIEKEY + "=?",
                new String[]{ cookie.getCookieKey() });

//        Close db
        db.close();
    }

    public void deleteCookie(String cookieKey) {
//        Get DB
        SQLiteDatabase db = this.getWritableDatabase();

//        Deleting
        db.delete(TABLE_COOKIE_NAME, TABLE_COOKIE_COOKIEKEY + "=?",
                new String[]{ cookieKey });

//        Close db
        db.close();
    }

    public void deleteCookie(RestCookie cookie) {
        deleteCookie(cookie.getCookieKey());
    }

    public void saveCookie(RestCookie cookie) {
//        Get Target
        RestCookie target = getCookie(cookie.getCookieKey());

//        Target null case
        if (target == null) {
            insertCookie(cookie);
        }
//        Target not null case
        else {
            updateCookie(cookie);
        }
    }
}
