package com.uppet.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uppet.MainGame;
import com.uppet.sprites.Cloud;
import com.uppet.sprites.Controller;
import com.uppet.sprites.Pet;

public class PlayState extends State  {
    private Pet pet;
    private Texture bg;
    private Cloud cloud;
    private Controller controller;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        pet = new Pet();
        pet.setPosition(MainGame.WIDTH/2-(pet.getTexture().getWidth()/2),MainGame.HEIGHT/2-(pet.getTexture().getHeight()/2));
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        bg = new Texture("bg.png");
        cloud = new Cloud(100);
        controller = new Controller(MainGame.batch);
    }

    @Override
    protected void handleInput() {
        /*if(Gdx.input.justTouched())
        {
            pet.jump(Gdx.input.getX(),Gdx.input.getY());
        }*/
        if (controller.isUpPressed())
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
        sb.draw(bg,MainGame.WIDTH/2-(bg.getWidth()/2),MainGame.HEIGHT/2-(bg.getHeight()/2));
        sb.draw(pet.getTexture(),pet.getPosition().x,pet.getPosition().y);
        sb.draw(cloud.getLeftCloud(),cloud.getPosLeft().x,cloud.getPosLeft().y);
        sb.draw(cloud.getRightCloud(),cloud.getPosRight().x,cloud.getPosRight().y);
        sb.end();

        controller.draw();
    }

    @Override
    public void dispose() {

    }
}
