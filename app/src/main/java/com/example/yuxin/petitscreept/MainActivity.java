package com.example.yuxin.petitscreept;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText entrytext = null;
    private Button token = null;
    private Controller ctr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scpt);
        entrytext = (EditText) findViewById(R.id.entrytext);

        findViewById(R.id.button_token).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    if (entrytext.getResources().toString() == null) {
                        Toast.makeText(getApplicationContext(),
                                "nothing to be tokenized.", Toast.LENGTH_LONG).show();
                    } else {
                        //tokenize the text by space
                        String n = "";
                        n = entrytext.getResources().toString();
                        String[] word_list = n.split(" ");

                        //catch the list of word tokenized and push into DB
                        getContent(word_list);

//                        Toast.makeText(getApplicationContext(),
//                                "nothing to be tokenized.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Words getContent(String[] word_list){
        Words word = new Words();
        List list = Arrays.asList(word_list);
        for (int i = 0; i < list.size(); i++){
            word.setWord(list.get(i).toString());
            word.setMeaning("Search it by yourself, app have not yet dictionary.");
            word.setLevel(3);
            word.setLvl_master(-1);
            boolean bool = ctr.save(word);
            if(bool == false){
                Toast.makeText(this, "Word: "+ word.getWord() + "can't be pushed into DB",
                        Toast.LENGTH_LONG).show();
            }
        }
        Toast.makeText(this, "YOLO, finish getContent()", Toast.LENGTH_LONG).show();
        return word;
    }
}