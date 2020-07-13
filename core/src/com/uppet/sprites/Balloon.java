package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Balloon {
    private int BUBBLE_MAX_STATE = 3;
    private Texture balloonTexture;
    private Rectangle balloonBounds;
    private Vector3 balloonPos;

    public Balloon(float x, float y, float petWidth)
    {
        balloonTexture = new Texture("bubble.png");
        balloonPos = new Vector3(x-(balloonTexture.getWidth()/2-petWidth/2),y+55,0);
        balloonBounds = new Rectangle(balloonPos.x, balloonPos.y, balloonTexture.getWidth(), balloonTexture.getHeight());
    }

    public void setPosition(float x, float y, float petWidth)
    {
        balloonPos.x = x - (balloonTexture.getWidth()/2-petWidth/2);
        balloonPos.y = y+55;
        balloonBounds.setPosition(balloonPos.x,balloonPos.y);
    }

    public void update(float dt, float x, float y, float petWidth)
    {
        balloonPos.x = x - (balloonTexture.getWidth()/2-petWidth/2);
        balloonPos.y = y+55;
        balloonBounds.setPosition(balloonPos.x,balloonPos.y);
    }

    public Texture getTexture() {
        return balloonTexture;
    }

    public Vector3 getPos() {
        return balloonPos;
    }
}
