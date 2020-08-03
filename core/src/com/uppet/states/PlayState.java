package com.uppet.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uppet.MainGame;
import com.uppet.listener.SitingListener;
import com.uppet.listener.TapListener;
import com.uppet.sprites.Cloud.CloudManager;
import com.uppet.sprites.Controller;
import com.uppet.sprites.Enemy.EnemyManager;
import com.uppet.sprites.Ground;
import com.uppet.sprites.MainPet.Pet;
import com.uppet.sprites.ScoreHub;

import java.util.ArrayList;

public class PlayState extends State  {
    private boolean isGameStarted = false;

    private Pet pet;
    private Texture bg;
    private Ground ground;
    private Controller controller;

    private EnemyManager enemyManager;
    private CloudManager cloudManager;

    private ScoreHub scoreHub;

    private static ArrayList<TapListener> tapListeners = new ArrayList<>();
    private static ArrayList<SitingListener> sitingListeners = new ArrayList<>();

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        bg = new Texture("bg.png");
        ground = new Ground();
        pet = new Pet(Ground.GROUND_HEIGHT,0);
        pet.setPosition(MainGame.WIDTH/2-(pet.getPetTexture().getRegionWidth()/2),(int)ground.getCurrentHeight());
        controller = new Controller(MainGame.batch);
        cloudManager = new CloudManager();
        enemyManager = new EnemyManager();
        scoreHub = new ScoreHub();

    }

    @Override
    protected void handleInput() {
        if (controller.isRightPressed())
        {
            isGameStarted = true;
            for(TapListener tapListener : tapListeners)
            {
                tapListener.onTapRight();
            }
        }
        else if(controller.isLeftPressed())
        {
            isGameStarted = true;
            for(TapListener tapListener : tapListeners)
            {
                tapListener.onTapLeft();
            }
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        pet.update(dt);

        if(ground.getCurrentHeight() >= 0) {
            ground.update(dt,pet.getBounds());
        }

        cloudManager.update(cam, pet.getFootBounds(),pet.isFalling());

        enemyManager.update(cam,dt,pet);

        scoreHub.update(dt,cam);

        if (isGameStarted) {
            cam.position.y += 1.5;
            cam.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,MainGame.WIDTH/2-(bg.getWidth()/2) + cam.position.x-cam.viewportWidth/2,MainGame.HEIGHT/2-(bg.getHeight()/2)+cam.position.y-cam.viewportHeight/2);
        if(ground.getCurrentHeight()>=0)
            sb.draw(ground.getTexture(),ground.getPosition().x,ground.getPosition().y);

        pet.render(sb);

        cloudManager.render(sb);

        enemyManager.render(sb);

        scoreHub.render(sb);

        sb.end();

        controller.draw();
    }

    @Override
    public void dispose() {

    }

    public static void addTapListener(TapListener tapListener){
        tapListeners.add(tapListener);
    }
}
