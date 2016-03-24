package com.example.yuxin.petitscreept;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText entrytext = null;
    private Controller ctr = null;
    Intent intent = null;
    private static final int REQUEST_PATH = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scpt);
        entrytext = (EditText) findViewById(R.id.entrytext);
        ctr = new Controller(this);
        intent = new Intent(MainActivity.this, ActivityList.class);

        /*Button File selector*/
        findViewById(R.id.button_filechoose).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              try {
                    Intent i = new Intent(MainActivity.this, FileList.class);

                    startActivityForResult(i, REQUEST_PATH);
              } catch (Exception e){
                  e.printStackTrace();
              }
            }
        });
        /*Button Start tokenize*/
        findViewById(R.id.button_words).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                        Intent intent = new Intent(MainActivity.this, ActivityList.class);
                        intent.putExtra("words_token", 5);//5 get all the words from client vocab
                        startActivity(intent);
                    }
        });

        /*Button Start tokenize*/
        findViewById(R.id.button_token).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    if (entrytext.getText().toString() == "") {
                        Toast.makeText(getApplicationContext(),
                                "nothing to be tokenized.", Toast.LENGTH_LONG).show();
                    } else {

                        //tokenize the text by space
                        String n = "";
                        n = entrytext.getText().toString();
                        //tokenize and elimit the punctuation
                        String[] word_list = n.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");

                        //catch the list of word tokenized and push into DB
                        getContent(word_list);
                        //set layout of layout item
                        Intent intent = new Intent(MainActivity.this, ActivityList.class);
                        intent.putExtra("words_token", 6);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

     /*----------------select file function--------------------------------------*/
    

    /*----------------tokenize function--------------------------------------*/

    //Save the data in DB
    private void getContent(String[] word_list){
        Words word = new Words();
        List list = Arrays.asList(word_list);
        if(word != null){
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).toString() != " "){
                word.setWord(list.get(i).toString());
                word.setMeaning("Search it by yourself, app have not yet dictionary.");
                word.setLevel(3);
                word.setLvl_master(-1);
                try {
                    boolean bool = ctr.save_to_tWORD(word);
                    Log.i("SAVE", "save word" + word.getWord().toString());
                    if (bool == false) {
                        Toast.makeText(this, "Word: " + word.getWord() + "the word " + list.get(i).toString()
                                + " already exists ", Toast.LENGTH_LONG).show();
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "word is null", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this, "YOLO, getContent() finish", Toast.LENGTH_LONG).show();
    }

}
