package com.uppet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameResource {
    private Skin skin;
    private Texture backGround;

    private static final GameResource instance = new GameResource();

    private GameResource(){
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        backGround = new Texture("menubgr.png");
    }

    public static GameResource getInstance(){
        return instance;
    }

    public Skin getSkin(){
        return skin;
    }

    public Texture getBackGround(){
        return backGround;
    }
}
