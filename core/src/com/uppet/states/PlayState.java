package com.uppet.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uppet.JsonMng;
import com.uppet.MainGame;
import com.uppet.listener.PlayerOverListener;
import com.uppet.listener.SitingListener;
import com.uppet.listener.StartGameListener;
import com.uppet.listener.TapListener;
import com.uppet.sprites.Cloud.Cloud;
import com.uppet.sprites.Cloud.CloudManager;
import com.uppet.sprites.Controller;
import com.uppet.sprites.Enemy.Enemy;
import com.uppet.sprites.Enemy.EnemyManager;
import com.uppet.sprites.Ground;
import com.uppet.sprites.MainPet.Balloon;
import com.uppet.sprites.MainPet.Pet;
import com.uppet.sprites.ScoreHub;
import com.uppet.sprites.StartHub;
import com.uppet.sprites.Visitor;

import java.util.ArrayList;

public class PlayState extends State implements PlayerOverListener {
    private boolean isGameStarted = false;
    private boolean isGameOver = false;
    private boolean isJustOver = false;
    private float currentTime = 0;

    private Pet pet;
    private Texture bg;
    private Ground ground;
    private Controller controller;

    private EnemyManager enemyManager;
    private CloudManager cloudManager;

    private ScoreHub scoreHub;
    private StartHub startHub;

    private static ArrayList<TapListener> tapListeners = new ArrayList<>();
    private static ArrayList<SitingListener> sitingListeners = new ArrayList<>();
    private static ArrayList<StartGameListener> startGameListeners = new ArrayList<>();

    private VisitorUpdate visitorUpdate;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        bg = new Texture("bg.png");
        ground = new Ground();
        pet = new Pet(Ground.GROUND_HEIGHT, 0);
        pet.setPosition(MainGame.WIDTH / 2 - (pet.getPetTexture().getRegionWidth() / 2), (int) ground.getCurrentHeight());
        controller = new Controller(MainGame.batch);
        cloudManager = new CloudManager();
        enemyManager = new EnemyManager();
        scoreHub = new ScoreHub();
        startHub = new StartHub();
        visitorUpdate = new VisitorUpdate();

        Balloon.addOverListener(this);
    }

    @Override
    protected void handleInput() {
        if (controller.isRightPressed()) {
            isGameStarted = true;
            for (TapListener tapListener : tapListeners) {
                tapListener.onTapRight();
            }
            noticeStart();
        } else if (controller.isLeftPressed()) {
            isGameStarted = true;
            for (TapListener tapListener : tapListeners) {
                tapListener.onTapLeft();
            }
            noticeStart();
        }


    }

    @Override
    public void update(float dt) {
        if (!isGameOver) {
            handleInput();
            visitorUpdate.update(dt);
            pet.accept(visitorUpdate);
            ground.accept(visitorUpdate);
            cloudManager.accept(visitorUpdate);
            enemyManager.accept(visitorUpdate);
            startHub.accept(visitorUpdate);
            scoreHub.accept(visitorUpdate);

            if (isJustOver)
            {
                currentTime += dt;
                if (currentTime >= 1.3)
                {
                    isGameOver = true;
                }
            }

            if (isGameStarted) {
                cam.position.y += 1.5;
                cam.update();
            }
        }
        if (isGameOver){
            gsm.set(new OverState(gsm));
            gameInfo.checkHighScore();
            JsonMng.saveGameInfoInternal();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, MainGame.WIDTH / 2 - (bg.getWidth() / 2) + cam.position.x - cam.viewportWidth / 2, MainGame.HEIGHT / 2 - (bg.getHeight() / 2) + cam.position.y - cam.viewportHeight / 2);
        if (ground.getCurrentHeight() >= 0)
            sb.draw(ground.getTexture(), ground.getPosition().x, ground.getPosition().y);

        pet.render(sb);

        cloudManager.render(sb);

        enemyManager.render(sb);

        scoreHub.render(sb);

        startHub.render(sb);

        sb.end();

        controller.draw();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void onContinue() {

    }

    public static void addTapListener(TapListener tapListener) {
        tapListeners.add(tapListener);
    }

    public static void addStartListener(StartGameListener startGameListener) {
        startGameListeners.add(startGameListener);
    }

    public void noticeStart() {
        for (StartGameListener startGameListener : startGameListeners) {
            startGameListener.isStart();
        }
    }

    @Override
    public void onOver() {
        isJustOver = true;
    }

    public class VisitorUpdate implements Visitor {
        private float dt;

        public void update (float dt)
        {
            this.dt = dt;
        }

        @Override
        public void visit(Enemy enemy) {

        }

        @Override
        public void visit(EnemyManager enemyManager) {
            enemyManager.update(cam, dt, pet);
        }

        @Override
        public void visit(Cloud cloud) {

        }

        @Override
        public void visit(CloudManager cloudManager) {
            cloudManager.update(cam, pet.getFootBounds(), pet.isFalling());
        }

        @Override
        public void visit(Pet pet) {
            pet.update(dt,cam);
        }

        @Override
        public void visit(ScoreHub scoreHub) {
            scoreHub.update(dt, cam);
        }

        @Override
        public void visit(StartHub startHub) {
            startHub.update();
        }

        @Override
        public void visit(Ground ground) {
            if (ground.getCurrentHeight() >= 0) {
                ground.update(dt, pet.getBounds());
            }
        }
    }
}
