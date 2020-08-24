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
import com.uppet.JsonMng;
import com.uppet.MainGame;

import javax.xml.soap.Text;

public class MenuState extends State {
    private Texture background;

    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton startButton, hardChooserButton, hightScoreButton, audioButton, quitGameButton;
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
        hightScoreButton = new TextButton("Hight Score",skin);
        audioButton = new TextButton("Audio",skin);
        quitGameButton = new TextButton("Quit game",skin);

        table.padTop(300);
        table.add(startButton).padBottom(30);
        table.row();
        table.add(hardChooserButton).padBottom(30);
        table.row();
        table.add(audioButton).padBottom(30);
        table.row();
        table.add(hightScoreButton).padBottom(50);
        table.row();
        table.add(quitGameButton).padBottom(70);

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

    private void audioSetting() {
        gsm.push(new SoundSettingState(gsm));
    }

    private void highScore() {
        gsm.push(new HighScoreState(gsm));
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
        audioButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                audioSetting();
            }
        });
        quitGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                quitGame();
            }
        });
        hightScoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                highScore();
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

    private void quitGame() {
        JsonMng.saveGameInfoInternal();
        Gdx.app.exit();
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
