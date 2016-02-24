package com.example.yuxin.petitscreept;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Yuxin on 23/02/2016.
 */
public class DBHelpler extends SQLiteOpenHelper{
    private  static  final  String TAG = "DBTag";
    private static final int DATABASE_VERSION = 1; //make easier to update
    private  static  final  String DATABASE_NAME = "word.db";
    private static final String sql = "CREATE TABLE tWORD (" +
                                        "_id integer primary key autoincrement," +
                                        "word text" + "level integer" + "lvl_master integer"
                                        + "meaning text)";

    public DBHelpler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
