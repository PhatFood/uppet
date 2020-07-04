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
    boolean upPressed, downPressed, leftPressed, rightPressed;
    private Viewport viewport;
    private Stage stage;

    public boolean isUpPressed() {
        return upPressed;
    }

    public Controller(SpriteBatch sb){
        viewport = new FitViewport(MainGame.WIDTH,MainGame.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);


        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        Image upImg = new Image(new Texture("up.png"));
        upImg.setSize(50,50);
        upImg.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                upPressed = true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                upPressed = false;
            }
        });

        //table.add();
        table.add(upImg).size(upImg.getWidth(),upImg.getHeight());
        //table.add();

        stage.addActor(table);

    }

    public void draw(){
        stage.draw();
    }

}
