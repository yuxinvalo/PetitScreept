package com.example.yuxin.petitscreept;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Yuxin on 25/02/2016.
 * THIS CLASS IS USED TO SHOW THE DETAIL OF THE WORD CHOOSEN AND CONFIGURE THE WORD
 */
public class ActivityDetail extends AppCompatActivity {
    private TextView text_word = null;
    private TextView meaning = null;
    private Spinner spinner_diff = null;
    private Spinner spinner_lvlmaster = null;
    private Integer[] diff = {1, 2 , 3, 4, 5};//Pay attention to type of spinner content
    private Integer[] lvlmaster = {-1, 0, 1};
    private Words word=null;
    private Controller ctr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        word = new Words();

        init();
        //Adapter spinner difficulity and spinner level master
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, diff);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_diff.setAdapter(adapter);

        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, lvlmaster);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_lvlmaster.setAdapter(adapter1);

        //Intent to receive the ID of word
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        Toast.makeText(getApplication(), "receive id : " + id, Toast.LENGTH_SHORT).show();
        if (id == -1){
            finish();
        } else {
            ctr = new Controller(this);
            word = ctr.getById(id);
            text_word.setText(word.getWord());
            meaning.setText(word.getMeaning());
            int pos_diff = adapter.getPosition(word.getLevel());
            spinner_diff.setSelection(pos_diff);
            int pos_lvlmaster = adapter1.getPosition(word.getLvl_master());
            spinner_lvlmaster.setSelection(pos_lvlmaster);

            //inal Words finalWord = word;
            findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        boolean bool = ctr.save(getContent());
                        Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_SHORT).show();
                        if(bool == true){
                            Toast.makeText(getApplicationContext(), "Save successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Can't save this word.", Toast.LENGTH_SHORT).show();
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        //Add come back list to title bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init(){
        text_word = (TextView) findViewById(R.id.word_text);
        meaning = (TextView) findViewById(R.id.word_meaning);
        spinner_diff = (Spinner) findViewById(R.id.spinner_diff);
        spinner_lvlmaster = (Spinner) findViewById(R.id.spinner_lvlmaster);
        //button_add = (Button)findViewById(R.id.button_add); I used lambda to create the button
    }

    private Words getContent(){
        Words w = new Words();
        w.setId(word.getId());
        w.setWord(word.getWord().toString());
        w.setMeaning(word.getMeaning().toString());
        w.setLevel((Integer) spinner_diff.getSelectedItem());
        w.setLvl_master((Integer) spinner_lvlmaster.getSelectedItem());
        return w;
    }

}
