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
    boolean leftPressed, rightPressed;
    private Viewport viewport;
    private Stage stage;

    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
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
        rightImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                rightPressed = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                rightPressed = false;
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
