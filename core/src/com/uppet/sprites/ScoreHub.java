package com.uppet.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;
import com.uppet.GameInfo;
import com.uppet.MainGame;
import com.uppet.listener.BirdPeckListener;
import com.uppet.listener.PlayerOverListener;
import com.uppet.listener.StartGameListener;
import com.uppet.sprites.Enemy.EnemyManager;
import com.uppet.sprites.MainPet.Balloon;
import com.uppet.states.PlayState;

public class ScoreHub implements BirdPeckListener, PlayerOverListener, StartGameListener {
    private Texture scoreTexture, scorePenTexture;
    private Animation scoreAnimation, scorePenAnimation;
    private int score;
    private float currentTime;
    private Vector2 position;
    private boolean justLostScore = false;
    private boolean isGameOver = false;
    private boolean isDisabledLostScore = false;
    private boolean isReadyToUnDisableLostScore = false;
    private boolean isStart = false;
    private GameInfo gameInfo;
    private float timeLevel = 0;
    private int scoreLostLevel = 0;

    public ScoreHub() {
        position = new Vector2(0, 0);
        gameInfo = GameInfo.getInstance();
        setLevel();
        score = 0;
        currentTime = 0;
        scoreTexture = new Texture("score.png");
        scorePenTexture = new Texture("scoresub.png");
        scorePenAnimation = new Animation(new TextureRegion(scorePenTexture), 10, 1f);
        scoreAnimation = new Animation(new TextureRegion(scoreTexture), 10, 1f);

        EnemyManager.addBirdPeckListener(this);
        Balloon.addOverListener(this);
        PlayState.addStartListener(this);
    }

    private void setLevel() {
        if(gameInfo.getLevel() == 1)
        {
            timeLevel = 2;
            scoreLostLevel = 2;
        }
        if(gameInfo.getLevel() == 2)
        {
            timeLevel = 1;
            scoreLostLevel = 3;
        }
        if(gameInfo.getLevel() == 3)
        {
            timeLevel = 0.5f;
            scoreLostLevel = 4;
        }
    }

    public void update(float dt, OrthographicCamera cam) {

        if(isStart) {
            position.x = cam.position.x - scoreAnimation.getWidthFrame() * ((float) 3 / 2);
            position.y = cam.position.y + (float) MainGame.WIDTH / 2 * ((float) 1);

            if (!isGameOver) {
                currentTime += dt;
                if (currentTime >= timeLevel) {
                    score++;
                    currentTime = 0;
                    if (isDisabledLostScore) {
                        justLostScore = true;
                        isReadyToUnDisableLostScore = true;
                    }
                    if (isReadyToUnDisableLostScore) {
                        justLostScore = false;
                        isDisabledLostScore = false;
                        isReadyToUnDisableLostScore = false;
                    }
                }
            }
        }
    }

    public void render(SpriteBatch sb) {
        if (isStart) {
            int hundred, tens, unit;
            hundred = score / 100;
            tens = (score - hundred) / 10;
            unit = score - hundred * 100 - tens * 10;

            if (justLostScore) {
                sb.draw(scorePenAnimation.getFrameAt(hundred), position.x, position.y);
                sb.draw(scorePenAnimation.getFrameAt(tens), scoreAnimation.getWidthFrame() + position.x, position.y);
                sb.draw(scorePenAnimation.getFrameAt(unit), scoreAnimation.getWidthFrame() * 2 + position.x, position.y);
            } else {
                sb.draw(scoreAnimation.getFrameAt(hundred), position.x, position.y);
                sb.draw(scoreAnimation.getFrameAt(tens), scoreAnimation.getWidthFrame() + position.x, position.y);
                sb.draw(scoreAnimation.getFrameAt(unit), scoreAnimation.getWidthFrame() * 2 + position.x, position.y);
            }
        }
    }

    @Override
    public void onPeckedRight() {
        lostScore();
    }

    @Override
    public void onPeckedLeft() {
        lostScore();
    }

    private void lostScore() {
        if (!isDisabledLostScore) {
            isDisabledLostScore = true;
            justLostScore = true;
            if (this.score > scoreLostLevel) {
                this.score -= scoreLostLevel;
            } else
                this.score = 0;
        }
    }

    @Override
    public void onOver() {
        isGameOver = true;
    }

    @Override
    public void isStart() {
        isStart = true;
    }
}
