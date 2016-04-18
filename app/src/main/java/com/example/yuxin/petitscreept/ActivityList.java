package com.example.yuxin.petitscreept;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.view.WindowManager;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yuxin on 25/02/2016.
 */
public class ActivityList extends Activity implements View.OnClickListener {
    private ListView list_word = null;
    private Controller ctr = null;
    private List word_list = null;
    private Words word = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listword);
        list_word = (ListView) findViewById(R.id.listword);
        ctr = new Controller(this);
        String percent = ctr.percentKnowlege();

        //set Dialog
        dialog(percent);

        //set adapter
        setContent();
        }

    private void dialog(String percentage) {
        AlertDialog dialog = new AlertDialog.Builder(this).setIcon(android.R.drawable.btn_star)
                .setMessage("The percetage of the words you master: " + percentage)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    //Set adapter
    private void setContent() {
        ctr = new Controller(this);

        final List words = new ArrayList();
        final Intent intent = getIntent();
        final int temp = intent.getIntExtra("words_token", 0);
        Toast.makeText(getApplicationContext(), "intent value : " + temp, Toast.LENGTH_SHORT).show();
        if (temp == 5) {
            word_list = ctr.getAll();
        }else {
            word_list = ctr.get_tWord();
        }
        if(word_list != null){
            for(int i = 0; i < word_list.size(); i++ ){
                //word_list is a list type and word is a Words type
                word = (Words) word_list.get(i);
                HashMap map = new HashMap();
                map.put("word", word.getWord());
                map.put("meaning", word.getMeaning());
                map.put("level", word.getLvl_master());
                words.add(map);
            }
        }else {
            Toast.makeText(getApplicationContext(), "DB is null", Toast.LENGTH_LONG).show();
            finish();
        }

        SimpleAdapter adapter = new SimpleAdapter(this, words, R.layout.activity_list_item,
                new String[] {"word", "meaning", "level"},
                new int[] {R.id.word_item, R.id.mean_item, R.id.level_item});
        list_word.setAdapter(adapter);
        list_word.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paret, View view, int position, long id) {
                word = (Words) word_list.get(position);
                Intent intent = null;
                if (temp == 5){
                    intent = new Intent(ActivityList.this, ActivityMywordDetail.class);
                    Toast.makeText(ActivityList.this, "go to client_vocab", Toast.LENGTH_SHORT).show();
                    intent.putExtra("id", word.getId());
                    startActivityForResult(intent, 0);
                } else {
                intent = new Intent(ActivityList.this, ActivityDetail.class);
                //Toast.makeText(getApplication(), "go to tWORD", Toast.LENGTH_LONG).show();
                intent.putExtra("id", word.getId());
                startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
//        popUp.dismiss();
     }
}
