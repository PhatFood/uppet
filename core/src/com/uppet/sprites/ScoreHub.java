package com.uppet.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;
import com.uppet.MainGame;
import com.uppet.listener.BirdPeckListener;
import com.uppet.sprites.Enemy.EnemyManager;

public class ScoreHub implements BirdPeckListener {
    private Texture scoreTexture, scorePenTexture;
    private Animation scoreAnimation, scorePenAnimation;
    private int score;
    private float currentTime;
    private Vector2 position;
    private boolean justLostScore = false;

    public ScoreHub(){
        position = new Vector2(0,0);
        score = 0;
        currentTime = 0;
        scoreTexture = new Texture("score.png");
        scorePenTexture = new Texture("scoresub.png");
        scorePenAnimation = new Animation(new TextureRegion(scorePenTexture),10,1f);
        scoreAnimation = new Animation(new TextureRegion(scoreTexture),10,1f);

        EnemyManager.addBirdPeckListener(this);
    }

    public void update(float dt, OrthographicCamera cam)
    {
        position.x = cam.position.x - scoreAnimation.getWidthFrame()*((float)3/2);
        position.y = cam.position.y + (float)MainGame.WIDTH/2 *((float)1);
        currentTime += dt;
        if (currentTime >= 1)
        {
            score++;
            currentTime = 0;
        }

    }

    public void render(SpriteBatch sb)
    {
        int hundred, tens, unit;
        hundred = score / 100;
        tens = (score-hundred) / 10;
        unit = score - hundred*100 - tens*10;

        if(justLostScore)
        {
            sb.draw(scorePenAnimation.getFrameAt(hundred),position.x,position.y);
            sb.draw(scorePenAnimation.getFrameAt(tens),scoreAnimation.getWidthFrame() + position.x,position.y);
            sb.draw(scorePenAnimation.getFrameAt(unit),scoreAnimation.getWidthFrame()*2 + position.x,position.y);
            justLostScore = false;
        }
        else
        {
            sb.draw(scoreAnimation.getFrameAt(hundred),position.x,position.y);
            sb.draw(scoreAnimation.getFrameAt(tens),scoreAnimation.getWidthFrame() + position.x,position.y);
            sb.draw(scoreAnimation.getFrameAt(unit),scoreAnimation.getWidthFrame()*2 + position.x,position.y);
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
        justLostScore = true;
        if (this.score > 5)
        {
            this.score -= 5;
        }
        else
            this.score = 0;
    }
}
