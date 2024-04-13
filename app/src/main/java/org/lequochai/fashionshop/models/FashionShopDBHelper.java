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
    public static final int VERSION = 2;
    public static final String DATABASE_NAME = "FashionShop";
    public static final String TABLE_COOKIE_NAME = "Cookie";
    public static final String TABLE_COOKIE_COOKIEKEY = "cookieKey";
    public static final String TABLE_COOKIE_COOKIEDOMAIN = "cookieDomain";
    public static final String TABLE_COOKIE_COOKIEVALUE = "cookieValue";
    public static final String TABLE_COOKIE_CREATE = "CREATE TABLE " + TABLE_COOKIE_NAME +  "(" +
            TABLE_COOKIE_COOKIEKEY + " TEXT," +
            TABLE_COOKIE_COOKIEDOMAIN + " TEXT," +
            TABLE_COOKIE_COOKIEVALUE + " TEXT NOT NULL," +
            "PRIMARY KEY (" + TABLE_COOKIE_COOKIEKEY + "," + TABLE_COOKIE_COOKIEDOMAIN + "))";

    public static final String TABLE_COOKIE_DROP = "DROP TABLE IF EXISTS " + TABLE_COOKIE_NAME;

//    Fields:
    private SQLiteDatabase db;

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
        if (db == null) {
            db = this.getWritableDatabase();
        }

//        Query
        Cursor cursor = db.query(
                TABLE_COOKIE_NAME, new String[]{ TABLE_COOKIE_COOKIEKEY, TABLE_COOKIE_COOKIEDOMAIN,
                        TABLE_COOKIE_COOKIEVALUE }, null, null, null,
                null, null
        );

//        Result initialize
        List<RestCookie> result = new ArrayList<>();

//        Getting
        while (cursor.moveToNext()) {
            result.add(
                    new RestCookie(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2)
                    )
            );
        }

//        Close cursor and db
        cursor.close();

        return result;
    }

    public RestCookie getCookie(String cookieKey, String cookieDomain) {
//        Get db
        if (db == null) {
            db = this.getWritableDatabase();
        }

//        Query
        Cursor cursor = db.query(
                        TABLE_COOKIE_NAME, new String[]{ TABLE_COOKIE_COOKIEKEY,
                        TABLE_COOKIE_COOKIEDOMAIN,
                        TABLE_COOKIE_COOKIEVALUE },
                TABLE_COOKIE_COOKIEKEY + "=? AND " + TABLE_COOKIE_COOKIEDOMAIN + "=?",
                new String[]{ cookieKey, cookieDomain },
                null,
                        null, null
                );

//        Target declaration
        RestCookie target = null;

//        Found case
        if (cursor.moveToNext()) {
            target = new RestCookie(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }

//        Close cursor and db
        cursor.close();

//        Return target
        return target;
    }

    public void insertCookie(RestCookie cookie) {
//        Get DB
        if (db == null) {
            db = this.getWritableDatabase();
        }

//        Create content values
        ContentValues values = new ContentValues();
        values.put(TABLE_COOKIE_COOKIEKEY, cookie.getCookieKey());
        values.put(TABLE_COOKIE_COOKIEDOMAIN, cookie.getCookieDomain());
        values.put(TABLE_COOKIE_COOKIEVALUE, cookie.getCookieValue());

//        Insert
        db.insert(TABLE_COOKIE_NAME, null, values);
    }

    public void updateCookie(RestCookie cookie) {
//        Get DB
        if (db == null) {
            db = this.getWritableDatabase();
        }

//        Create content values
        ContentValues values = new ContentValues();
        values.put(TABLE_COOKIE_COOKIEVALUE, cookie.getCookieValue());

//        Update
        db.update(TABLE_COOKIE_NAME, values,
                TABLE_COOKIE_COOKIEKEY + "=? AND " + TABLE_COOKIE_COOKIEDOMAIN + "=?",
                new String[]{ cookie.getCookieKey(), cookie.getCookieDomain() });
    }

    public void deleteCookie(String cookieKey, String cookieDomain) {
//        Get DB
        if (db == null) {
            db = this.getWritableDatabase();
        }

//        Deleting
        db.delete(TABLE_COOKIE_NAME,
                TABLE_COOKIE_COOKIEKEY + "=? AND " + TABLE_COOKIE_COOKIEDOMAIN + "=?",
                new String[]{ cookieKey, cookieDomain });
    }

    public void deleteCookie(RestCookie cookie) {
        deleteCookie(cookie.getCookieKey(), cookie.getCookieDomain());
    }

    public void saveCookie(RestCookie cookie) {
//        Get Target
        RestCookie target = getCookie(cookie.getCookieKey(), cookie.getCookieDomain());

//        Target null case
        if (target == null) {
            insertCookie(cookie);
        }
//        Target not null case
        else {
            updateCookie(cookie);
        }
    }

    public void shutdown() {
        if (db != null) {
            if (db.isOpen()) {
                db.close();
            }
        }
    }
}
