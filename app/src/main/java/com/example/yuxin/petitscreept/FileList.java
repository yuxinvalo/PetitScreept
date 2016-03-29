package com.example.yuxin.petitscreept;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yuxin on 08/03/2016.
 */
public class FileList extends ListActivity {
    private List<File> fileNameList;
    private String filename;
    private Controller ctr = null;
    @Override
    protected void onCreate(Bundle savedInstenceState){
        super.onCreate(savedInstenceState);
        setContentView(R.layout.filelist);
        ctr = new Controller(this);
        initFileList();
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File file = fileNameList.get(position);
        if (file.isDirectory())
        {
            File[] f = file.listFiles();
            fill(f);
        }
        else {
            //get the absolute path of file
            filename = file.getAbsolutePath();
            getFileString(filename);//get the content of the file
            Intent i = new Intent(FileList.this, ActivityList.class);
            i.putExtra("word_token", 7);
            startActivity(i);
        }
    }

    private void getFileString(String filename) {
        try{
            FileInputStream fileInputStream = new FileInputStream(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String n;
            Toast.makeText(getApplicationContext(), "file chooser", Toast.LENGTH_LONG).show();
            while ((n = bufferedReader.readLine()) != null){
                String [] word_list = n.replace("[^a-zA-Z ]", "").toLowerCase().split(" ");
                getContent(word_list);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void initFileList() {
        File file = android.os.Environment.getExternalStorageDirectory();
        File[] f  = file.listFiles();
        fill(f);
    }

    private void fill(File[] files) {
        fileNameList = new ArrayList<File>();
        for (File file : files){
            if(isValidFileOrDirName(file)){
                fileNameList.add(file);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, fileToStrArr(fileNameList));
        setListAdapter(adapter);
    }


    //con
    private String[] fileToStrArr(List<File> fileNameList) {
        ArrayList<String> fileList = new ArrayList<String>();
        for(int i = 0; i < fileNameList.size(); ++i){
            fileList.add(fileNameList.get(i).getName());
        }
        return fileList.toArray(new String[0]);
    }


    //Check if the file name is valid like txt
    private boolean isValidFileOrDirName(File file){
        boolean bool = false;
        if(file.isDirectory()){
            bool = true;
        } else {
            String fileName = file.getName().toLowerCase();
            if(fileName.endsWith(".txt")){
                bool = true;
            }
        }
        return bool;
    }


    private void getContent(String[] word_list){
        Words word = new Words();
        List list = Arrays.asList(word_list);
        if(word != null){
            for (int i = 0; i < list.size(); i++) {
                word.setWord(list.get(i).toString());
                word.setMeaning("Search it by yourself, app have not yet dictionary.");
                word.setLevel(3);

              //  Toast.makeText(getApplicationContext(), "getContent", Toast.LENGTH_LONG).show();
                try {
                    boolean bool = ctr.save_to_tWORD(word);
                    if (bool == false) {
                        Toast.makeText(this, "Word: " + word.getWord() + "can't be pushed into DB",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "word is null", Toast.LENGTH_LONG).show();
        }
    }

}
