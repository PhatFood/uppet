package com.uppet.sprites.MainPet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;

public class Balloon {
    private Texture balloonTexture, balloonTexturePop;
    private Rectangle balloonBounds;
    private Vector3 balloonPos;
    private enum BalloonStates{green, yellow, red, pop};
    private BalloonStates balloonState;
    private float reviveTime;
    private float balloonCurrentTime;
    private Animation balloonAnimation, balloonAnimationPop;



    public Rectangle getBounds() {
        return balloonBounds;
    }

    public void over() {
        balloonState = BalloonStates.pop;
    }

    public boolean isOver(){
        return (balloonState == BalloonStates.pop);
    }

    public Balloon(float x, float y, float petWidth)
    {
        reviveTime = 0.5f;
        balloonCurrentTime = 0;

        balloonTexture = new Texture("balloonani.png");
        balloonAnimation = new Animation(new TextureRegion(balloonTexture),3,1);

        balloonTexturePop = new Texture("balloonanipop.png");
        balloonAnimationPop = new Animation(new TextureRegion(balloonTexturePop),7,0.7f);
        balloonAnimationPop.setLoop(false);

        balloonPos = new Vector3(x-(balloonAnimation.getWidthFrame()/2-petWidth/2),y+55,0);
        balloonBounds = new Rectangle(balloonPos.x, balloonPos.y, balloonAnimation.getWidthFrame(), balloonAnimation.getHeightFrame());
        balloonState = BalloonStates.green;
    }

    public void setPosition(float x, float y, float petWidth)
    {

        if(balloonState == BalloonStates.pop)
        {
            balloonPos.x = x - (balloonAnimationPop.getWidthFrame()/2-petWidth/2) - 5;
            balloonPos.y = y+45;
        }
        else {
            balloonPos.x = x - (balloonAnimation.getWidthFrame() / 2 - petWidth / 2);
            balloonPos.y = y + 55;
        }

        balloonBounds.setPosition(balloonPos.x,balloonPos.y);
    }

    public void update(float dt, float x, float y, float petWidth)
    {
        balloonCurrentTime += dt;
        if(balloonCurrentTime >= reviveTime)
        {
            reviveState();
            balloonCurrentTime = 0;
        }

        if(balloonState == BalloonStates.pop)
        {
            balloonAnimationPop.update(dt);
            balloonPos.x = x - (balloonAnimationPop.getWidthFrame()/2-petWidth/2) - 5;
            balloonPos.y = y+45;
        }
        else {
            balloonPos.x = x - (balloonAnimation.getWidthFrame() / 2 - petWidth / 2);
            balloonPos.y = y + 55;
        }

        balloonBounds.setPosition(balloonPos.x,balloonPos.y);
    }

    public void reviveState()
    {
        if(balloonState == BalloonStates.red)
            balloonState = BalloonStates.yellow;
        else if(balloonState == BalloonStates.yellow)
            balloonState = BalloonStates.green;
    }

    public void changeState()
    {
        balloonCurrentTime = 0;
        if(balloonState == BalloonStates.green)
            balloonState = BalloonStates.yellow;
        else if(balloonState == BalloonStates.yellow)
            balloonState = BalloonStates.red;
        else if(balloonState == BalloonStates.red)
            balloonState = BalloonStates.pop;
    }

    public TextureRegion getTexture() {
        if (balloonState == BalloonStates.green)
            return balloonAnimation.getFrameAt(0);
        if (balloonState == BalloonStates.yellow)
            return balloonAnimation.getFrameAt(1);
        if (balloonState == BalloonStates.red)
            return balloonAnimation.getFrameAt(2);
        if(balloonState == BalloonStates.pop)
            return balloonAnimationPop.getFrame();
        return null;
    }

    public Vector3 getPos() {
        return balloonPos;
    }
}
