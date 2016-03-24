package com.example.yuxin.petitscreept;

/**
 * Created by Yuxin on 23/02/2016.
 */
public class Words {
    private int id;
    private String word;
    private int level;
    private int lvl_master;
    private String meaning;

    public Words(){
        id = 0;
        word = "";
        level = 3;
        lvl_master = -1;
        meaning = "";
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLvl_master() {
        return lvl_master;
    }

    public void setLvl_master(int lvl_master) {
        this.lvl_master = lvl_master;
    }
}
