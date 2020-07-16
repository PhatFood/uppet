package com.uppet.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uppet.MainGame;

public class Controller {
    private boolean leftPressed, rightPressed, leftReleased, rightReleased;
    private Viewport viewport;
    private Stage stage;

    public boolean isLeftPressed() {
        if(leftPressed)
        {
            leftPressed = false;
            return true;
        }
        return false;
    }
    public boolean isLeftReleased() {
        if(leftReleased)
        {
            leftReleased = false;
            return true;
        }
        return false;
    }
    public boolean isRightPressed() {
        if(rightPressed)
        {
            rightPressed = false;
            return true;
        }
        return false;
    }
    public boolean isRightReleased() {
        if(rightReleased)
        {
            rightReleased = false;
            return true;
        }
        return false;
    }


    public Controller(SpriteBatch sb){
        viewport = new FitViewport(MainGame.WIDTH,MainGame.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);


        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.bottom();

        Image rightImg = new Image(new Texture("right.png"));
        rightImg.setSize(100,100);
        rightPressed = false; rightReleased = false; leftPressed = false; leftReleased = false;
        rightImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                rightPressed = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                rightPressed = false;
                rightReleased = true;
            }
        });

        Image leftImg = new Image(new Texture("left.png"));
        leftImg.setSize(100,100);
        leftImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                leftPressed = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                leftPressed = false;
                leftReleased = true;
            }
        });

        table.row().pad(10,10,20,10);
        table.add();
        table.add(leftImg).size(leftImg.getWidth(),leftImg.getHeight());
        table.add();
        table.add(rightImg).size(rightImg.getWidth(),rightImg.getHeight());
        table.add();

        stage.addActor(table);

    }

    public void draw(){
        stage.draw();
    }

}
