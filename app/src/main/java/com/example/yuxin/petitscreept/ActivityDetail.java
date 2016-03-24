package com.example.yuxin.petitscreept;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Yuxin on 25/02/2016.
 */
public class ActivityDetail extends AppCompatActivity {
    private TextView text_word = null;
    private TextView meaning = null;
    private Spinner spinner_diff = null;
    private Spinner spinner_lvlmaster = null;
    private int[] diff = {1, 2 , 3, 4, 5};
    private int[] lvlmaster = {-1, 0, 1};

    private Controller ctr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ctr = new Controller(this);
        init();

        //Intent to receive the ID of word
    }

    private void init(){
        text_word = (TextView) findViewById(R.id.word_text);
        meaning = (TextView) findViewById(R.id.word_meaning);
        spinner_diff = (Spinner) findViewById(R.id.spinner_diff);
        spinner_lvlmaster = (Spinner) findViewById(R.id.spinner_lvlmaster);
    }

}
