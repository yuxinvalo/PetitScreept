package com.example.yuxin.petitscreept;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yuxin on 25/02/2016.
 */
public class ActivityList extends Activity {
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
        Log.i("WTF", percent);
        Toast.makeText(this,"percentage of familly word: " + percent, Toast.LENGTH_SHORT).show();

        //set adapter
        setContent();
        //Toast.makeText(this, "YOLO, setContent() finish", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplication(), "go to tWORD", Toast.LENGTH_LONG).show();
                intent.putExtra("id", word.getId());
                startActivity(intent);
                }
            }
        });
    }
}
