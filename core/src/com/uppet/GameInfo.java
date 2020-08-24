package com.uppet;

public class GameInfo {
    private static final GameInfo instance = new GameInfo();
    private int level;
    private int highScore;
    private int currentScore;
    private boolean musicOn;

    private GameInfo(){
        level = 1;
        highScore = 0;
        currentScore = 0;
        musicOn = true;
    }

    public static GameInfo getInstance(){
        return instance;
    }

    public void setMusic(boolean check)
    {
        musicOn = check;
    }

    public boolean isMusicOn(){
        return musicOn;
    }

    public String getAudioSetting(){
        if (musicOn)
        {
            return "on";
        }
        return "off";
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void setHighScore(int highScore){
        this.highScore = highScore;
    }

    public int getCurrentScore(){
        return currentScore;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public int getHighScore(){return highScore;}

    public void checkHighScore() {
        if (currentScore > highScore)
        {
            highScore = currentScore;
        }
    }
}
