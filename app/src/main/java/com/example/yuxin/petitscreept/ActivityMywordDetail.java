package com.example.yuxin.petitscreept;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tearsyu on 16-3-25.
 */
public class ActivityMywordDetail extends AppCompatActivity {
    private TextView text_word = null;
    private TextView meaning = null;
    private Spinner spinner_diff = null;
    private Spinner spinner_lvlmaster = null;
    private Integer[] diff = {1, 2 , 3, 4, 5};//Pay attention to type of spinner content
    private Integer[] lvlmaster = {-1, 0, 1};
    private Words word = null;
    private Controller ctr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myword_modify);
        word = new Words();
        init();

        //Init spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, diff);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_diff.setAdapter(adapter);

        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item,lvlmaster);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_lvlmaster.setAdapter(adapter1);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", -1);
        Toast.makeText(getApplication(), "receive id : " + id, Toast.LENGTH_SHORT).show();

        if(id == -1){
            Toast.makeText(getApplication(), "id is -1, exit" + id, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            ctr = new Controller(this);
            word = ctr.getVocabById(id);
            text_word.setText(word.getWord());
            meaning.setText(word.getMeaning());
            int pos_diff = adapter.getPosition(word.getLevel());
            spinner_diff.setSelection(pos_diff);
            int pos_lvlmaster = adapter1.getPosition(word.getLvl_master());
            spinner_lvlmaster.setSelection(pos_lvlmaster);

            //final Words finalWord = word;
            //Button action

            findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        boolean bool = ctr.update(getContent());
                        Log.i("UPDATE", "Update word " + word.getWord());

                        if (bool == true){
                            Toast.makeText(getApplication(), "Update Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplication(), "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    try{
                        ctr.delete(id);
                        //Come back last activity
                        Log.i("DELETE", "delete word: " + word.getWord());
                        Intent comeback = new Intent();
                        comeback.putExtra("words_token", 8);
                        startActivity(comeback);
                    } catch (Exception e){e.printStackTrace();}
                }
            });
        }

    }

    private void init() {
        text_word = (TextView) findViewById(R.id.word_text_m);
        meaning = (TextView) findViewById(R.id.word_meaning_m);
        spinner_diff = (Spinner) findViewById(R.id.spinner_diff_m);
        spinner_lvlmaster = (Spinner) findViewById(R.id.spinner_lvlmaster_m);
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
