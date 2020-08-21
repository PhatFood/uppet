package com.uppet.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.uppet.GameInfo;
import com.uppet.GameResource;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected GameInfo gameInfo;
    protected GameResource gameResource;

    protected State(GameStateManager gsm)
    {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
        gameInfo = GameInfo.getInstance();
        gameResource = GameResource.getInstance();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

    public abstract void onContinue();
}
