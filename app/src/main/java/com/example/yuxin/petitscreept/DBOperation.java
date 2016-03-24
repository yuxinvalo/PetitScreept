package com.example.yuxin.petitscreept;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Yuxin on 23/02/2016.
 * RECEIVE THE DATA FROM CLASS CONTROLLER AND EXECUTE CMD OF SQL TO MANAGE SQL OF APP
 */
public class DBOperation {
    private DBHelpler database = null;

    public DBOperation(Context context){
        database = new DBHelpler(context);
    }

    public boolean save_tWord(Words word){
        SQLiteDatabase db = database.getWritableDatabase();
        //deleteAll();
        if (word != null){
            ContentValues value = new ContentValues();
            value.put("word", word.getWord());
            value.put("level", word.getLevel());
            value.put("lvl_master", word.getLvl_master());
            value.put("meaning", word.getMeaning());
            db.insertOrThrow("tWORD", null, value);
            db.close();
            return true;
        }else {
            return false;
        }
    }
    public boolean save(Words word){
        SQLiteDatabase db = database.getWritableDatabase();

        if(word != null){
            ContentValues value = new ContentValues();
            value.put("word", word.getWord());
            value.put("level", word.getLevel());
            value.put("lvl_master", word.getLvl_master());
            value.put("meaning", word.getMeaning());
            db.insertOrThrow("client_vocab", null, value);
            db.close();
            return true;
        } else {

            return false;
        }
    }


    public List get_tWord(){
        List list;
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from tWORD GROUP BY word";
        Cursor cursor = db.rawQuery(sql, null);

        list = new ArrayList();
        while(cursor.moveToNext()){
            Words word = new Words();
            word.setId(cursor.getInt(0));
            word.setWord(cursor.getString(1));
            word.setLevel(cursor.getInt(2));
            word.setLvl_master(cursor.getInt(3));
            word.setMeaning(cursor.getString(4));
            list.add(word);
        }
        cursor.close();
        db.close();
        return list;
    }
    public List getAll(){
        List list;
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from client_vocab order by word";
        Cursor cursor = db.rawQuery(sql, null);

        list = new ArrayList();
        while(cursor.moveToNext()){
            Words word = new Words();
            word.setId(cursor.getInt(0));
            word.setWord(cursor.getString(1));
            word.setLevel(cursor.getInt(2));
            word.setLvl_master(cursor.getInt(3));
            word.setMeaning(cursor.getString(4));
            list.add(word);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List getById_list(int id){
        List list = null;
        if (id > -1){
            SQLiteDatabase db = database.getReadableDatabase();
            String sql = "select * from tWORD where _id = ?";
            String[] para = new String[]{String.valueOf(id)};
            Cursor cursor = db.rawQuery(sql, para);

            list = new ArrayList();
            while (cursor.moveToNext()){
                Words word = new Words();
                word.setId(cursor.getInt(0));
                word.setWord(cursor.getString(1));
                word.setLevel(cursor.getInt(2));
                word.setLvl_master(cursor.getInt(3));
                word.setMeaning(cursor.getString(4));
                list.add(word);
            }
            cursor.close();
            db.close();
        }
        return  list;
    }

    public Words getById(int id){
        Words word = null;
        if(id > 0){
            SQLiteDatabase db = database.getReadableDatabase();
            String sql = "select * from tWORD where _id = ?";
            String[] para = new String[]{String.valueOf(id)};
            Cursor cursor = db.rawQuery(sql, para);
            if(cursor.moveToNext()){
                word = new Words();
                word.setId(cursor.getInt(0));
                word.setWord(cursor.getString(1));
                word.setLevel(cursor.getInt(2));
                word.setLvl_master(cursor.getInt(3));
                word.setMeaning(cursor.getString(4));
            }
            cursor.close();
            db.close();
        }
        return word;
    }

//    A modifier----------------
    public boolean update(Words word){
        boolean bool = false;
        SQLiteDatabase db = database.getWritableDatabase();
        if(word != null){
            ContentValues value = new ContentValues();
            value.put("word", word.getWord());
            value.put("level", word.getLevel());
            value.put("lvl_master", word.getLvl_master());
            value.put("meaning", word.getMeaning());
            db.update("tWORD", value, "id=?", new String[]{String.valueOf(word.getId())});
            db.close();
            bool = true;
        }
        return bool;
    }

    protected void deleteAll(){
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete("tWORD", null, null);
        //db.execSQL(sql);
        db.close();
    }
//    A modifier----------------------
    public void delete(int id){
        if(id > 0){
            SQLiteDatabase db = database.getWritableDatabase();
            String sql = "delete * from tWORD where _id = ?";
            Object[] para = new Object[]{String.valueOf(id)};
            db.execSQL(sql, para);
            db.close();
        }
    }
}
