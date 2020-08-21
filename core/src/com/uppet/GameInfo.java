package com.uppet;

public class GameInfo {
    private static final GameInfo instance = new GameInfo();
    private int level;

    private GameInfo(){
        level = 1;
    }

    public static GameInfo getInstance(){
        return instance;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
