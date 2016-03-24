package com.example.yuxin.petitscreept;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

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

        //set adapter
        setContent();
        Toast.makeText(this, "YOLO, setContent() finish", Toast.LENGTH_LONG).show();
        }

    //Set adapter
    private void setContent() {
        ctr = new Controller(this);

        List words = new ArrayList();
        Intent intent = getIntent();
        int temp = intent.getIntExtra("words_token", 0);
        Toast.makeText(getApplicationContext(), "intent value : " + temp, Toast.LENGTH_SHORT).show();
        if (temp == 5) {
            word_list = ctr.getAll();
        }else {
            //word_list = ctr.getAll();
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
    }


    private class ViewItemListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id){
            //跳转到ACTIVITY_DETAIL
        }

    }
}
