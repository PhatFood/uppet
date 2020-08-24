package com.uppet.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.uppet.MainGame;

public class SoundSettingState extends State {
    private Texture background;
    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton onButton, offButton, backButton;
    protected SoundSettingState(GameStateManager gsm) {
        super(gsm);


        stage = new Stage(new ScreenViewport());
        background = gameResource.getBackGround();
        skin = gameResource.getSkin();

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);

        table.setPosition(0, Gdx.graphics.getHeight());

        onButton = new TextButton("On",skin);
        offButton = new TextButton("Off",skin);
        backButton = new TextButton("Back",skin);

        table.padTop(400);
        table.add(onButton).padBottom(30);
        table.row();
        table.add(offButton).padBottom(60);
        table.row();
        table.add(backButton);


        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        handleInput();
    }

    @Override
    protected void handleInput() {
        onButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameInfo.setMusic(true);
                returnMenu();
            }
        });
        offButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameInfo.setMusic(false);
                returnMenu();
            }
        });
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                returnMenu();
            }
        });
    }

    private void returnMenu() {
        gsm.pop();
        dispose();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, MainGame.WIDTH,MainGame.HEIGHT);
        sb.end();


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void onContinue() {

    }
}
