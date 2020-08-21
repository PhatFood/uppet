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

public class GameLevelState extends State {
    private Texture background;
    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton hardButton, normalButton, easyButton, backButton;
    protected GameLevelState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());
        background = gameResource.getBackGround();
        skin = gameResource.getSkin();

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);

        table.setPosition(0,Gdx.graphics.getHeight());

        hardButton = new TextButton("Hard",skin);
        normalButton = new TextButton("Normal",skin);
        easyButton = new TextButton("Easy",skin);
        backButton = new TextButton("Back",skin);

        table.padTop(400);
        table.add(hardButton).padBottom(30);
        table.row();
        table.add(normalButton).padBottom(30);
        table.row();
        table.add(easyButton).padBottom(60);
        table.row();
        table.add(backButton);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        handleInput();
    }

    public void returnMenu(){
        gsm.pop();
        dispose();
    }

    @Override
    protected void handleInput() {
        hardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameInfo.setLevel(3);
                returnMenu();
            }
        });
        normalButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameInfo.setLevel(2);
                returnMenu();
            }
        });
        easyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameInfo.setLevel(1);
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
