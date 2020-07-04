package com.uppet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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



	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0,0,0,1);
		gsm.push(new PlayState(gsm));

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
	}
}
