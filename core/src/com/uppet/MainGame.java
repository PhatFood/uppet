package com.uppet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uppet.states.GameStateManager;
import com.uppet.states.MenuState;
import com.uppet.states.PlayState;

import javax.swing.text.View;

public class MainGame extends Game {
	public static final int WIDTH = 430;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Uppet";
	private GameStateManager gsm;
	public static SpriteBatch batch;
	public static Viewport viewport;

	private Music music;
	private GameInfo gameInfo;
	private boolean justChangeMusic;


	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0,0,0,1);
		gsm.push(new MenuState(gsm));
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		justChangeMusic = true;
		JsonMng.readGameInfoInternal();
		gameInfo = GameInfo.getInstance();

		viewport = new FitViewport(WIDTH,HEIGHT);
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

		if(justChangeMusic != gameInfo.isMusicOn())
        {
            if(gameInfo.isMusicOn() == false)
            {
                music.stop();
            }
            else {
                music.play();
            }
            justChangeMusic = gameInfo.isMusicOn();
        }
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
