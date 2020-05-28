package com.uppet.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.uppet.MainGame;
import com.uppet.sprites.Pet;

import sun.applet.Main;

public class PlayState extends State {
    private Pet pet;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        pet = new Pet();
        pet.setPosition(MainGame.WIDTH/2-(pet.getTexture().getWidth()/2),MainGame.HEIGHT/2-(pet.getTexture().getHeight()/2));
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            pet.jump(Gdx.input.getX(),Gdx.input.getY());
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        pet.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(pet.getTexture(),pet.getPosition().x,pet.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
