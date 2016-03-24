package com.example.yuxin.petitscreept;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;


/**
 * Created by Yuxin on 24/02/2016.
 * INTERFACE TO CONNECT AND BUNDLE THE DATA FROM DB
 */
public class Controller {
    private DBOperation ctrller = null;

    public Controller(Context context){ctrller = new DBOperation(context);}

    public boolean save(Words word){
        boolean bool = ctrller.save(word);
        return bool;
    }
    public boolean save_to_tWORD (Words word){
        boolean bool = ctrller.save_tWord(word);
        return bool;
    }

    public List get_tWord(){
        List list = ctrller.get_tWord();
        return list;
    }
    public Words getById(int id){
        Words word = ctrller.getById(id);
        return word;
    }

    public List getAll(){
        List list = ctrller.getAll();
        return list;
    }

    public void delete(int id){
        ctrller.delete(id);
    }
}
