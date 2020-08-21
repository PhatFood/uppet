package com.uppet.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.uppet.GameResource;
import com.uppet.MainGame;

public class MenuState extends State {
    private Texture background;

    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton startButton, hardChooserButton;
    public MenuState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());

        background = gameResource.getBackGround();
        skin = gameResource.getSkin();

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);

        table.setPosition(0,Gdx.graphics.getHeight());

        startButton = new TextButton("New Game",skin);
        hardChooserButton = new TextButton("Game Level",skin);

        table.padTop(400);
        table.add(startButton).padBottom(30);
        table.row();
        table.add(hardChooserButton).padBottom(30);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        handleInput();

    }

    private void playGame() {
        gsm.push(new PlayState(gsm));
    }

    private void hardChoose(){
        gsm.push(new GameLevelState(gsm));
    }

    @Override
    public void handleInput() {
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                playGame();
            }
        });
        hardChooserButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hardChoose();
            }
        });
    }

    @Override
    public void update(float dt) {
        //handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        /*sb.begin();
        sb.draw(background,0,0, MainGame.WIDTH,MainGame.HEIGHT);
        sb.draw(playBtn,MainGame.WIDTH/2-(playBtn.getWidth()/2),MainGame.HEIGHT/2-(playBtn.getHeight()/2));
        sb.end();*/



        sb.begin();
        sb.draw(background,0,0, MainGame.WIDTH,MainGame.HEIGHT);
        sb.end();


        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        /*stage.dispose();
        background.dispose();*/
    }

    @Override
    public void onContinue() {
        Gdx.input.setInputProcessor(stage);
    }
}
