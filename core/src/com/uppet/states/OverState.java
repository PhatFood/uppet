package com.uppet.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.uppet.MainGame;

public class OverState extends State {
    private Texture background;
    private Skin skin;
    private Stage stage;
    private Table table;

    private TextButton backToMenuButton;
    private Label gameOverLabel;
    private Label yourHighScoreLabel;
    private Label newHighScoreLabel;
    protected OverState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());

        background = gameResource.getBackGround();
        skin = gameResource.getSkin();

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center|Align.top);

        table.setPosition(0, Gdx.graphics.getHeight());

        backToMenuButton = new TextButton("Return to menu",skin);
        gameOverLabel = new Label("Game over",skin);
        yourHighScoreLabel = new Label("Your score: "+gameInfo.getCurrentScore(),skin);
        newHighScoreLabel = new Label("New high score!!!",skin);

        table.padTop(400);
        table.add(gameOverLabel).padBottom(30);
        table.row();
        if (gameInfo.getCurrentScore() >= gameInfo.getHighScore() )
        {
            table.add(newHighScoreLabel).padBottom(5);
            table.row();
        }
        table.add(yourHighScoreLabel).padBottom(60);
        table.row();
        table.add(backToMenuButton).padBottom(30);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        handleInput();
    }

    @Override
    protected void handleInput() {
        backToMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.pop();
                dispose();
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

    }

    @Override
    public void onContinue() {

    }
}
