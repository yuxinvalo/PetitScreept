package com.example.yuxin.petitscreept;

import android.content.Context;


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

    public Words getById(int id){
        Words word = ctrller.getById(id);
        return word;
    }

    public void delete(int id){
        ctrller.delete(id);
    }
}
