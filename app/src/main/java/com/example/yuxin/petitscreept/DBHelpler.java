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
                                        "_id integer primary key autoincrement, " +
                                        "word text, " + "level integer, " +
                                        "lvl_master integer, " + "meaning text)";
    private static final String sql_client = "CREATE TABLE client_vocab (" +
            "_id integer primary key autoincrement, " +
            "word text unique, " + "level integer, " +
            "lvl_master integer, " + "meaning text)";

    private static final String sql_client_vocab = "" +
            "INSERT INTO  client_vocab VALUES(1, 'my', 0, 1, 'meaning'), " +
            "(2, 'to', 0, 1, 'meaning'), (3, 'you', 0, 1, 'meaning'), " +
            "(4, 'your', 0, 1, 'meaning'), (5, 'i', 0, 1, 'meaning'), " +
            "(6, 'boy', 0, 1, 'meaning'), (7, 'girl', 0, 1, 'meaning'), " +
            "(8, 'man', 0, 1, 'meaning'), (9, 'woman', 0, 1, 'meaning'),   " +
            "(10, 'fish', 0, 1, 'meaning'), (11, 'cat', 0, 1, 'meaning'), " +
            "(12, 'unfair', 2, 0, 'meaning'), (13, 'child', 0, 0, 'meaning'), " +
            "(14, 'capital', 2, 0, 'meaning'), (15, 'provider', 3, -1, 'meaning'), " +
            "(16, 'repeat', 1, -1, 'meaning'), (17, 'a', 0, 1, 'meaning')," +
            "(18, 'new', 0, 1, 'meaning'), (19, 'scheduler', 3, -1, 'meaning'), " +
            "(20, 'document', 2, 0, 'meaning'), (21, 'standard', 2, 0, 'meaning');";

    public DBHelpler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(sql_client);
        db.execSQL(sql_client_vocab);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){

        }
    }
}