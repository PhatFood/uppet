package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.uppet.Animation;
import com.uppet.MainGame;
import com.uppet.listener.SitingListener;

import java.util.ArrayList;

public class Ground {
    public static final int GROUND_HEIGHT = 450;
    private static final int GRASS_HEIGHT = 370;
    private Vector2 position;
    private Texture texture;
    private Rectangle rectangle;
    private static ArrayList<SitingListener> sitingListeners = new ArrayList<>();
    private Animation groundAnimation;

    public Vector2 getPosition() {return position;}
    public TextureRegion getTexture() {return groundAnimation.getFrame();}

    public Ground() {
        texture = new Texture("groundani.png");
        groundAnimation = new Animation(new TextureRegion(texture),4,0.7f);
        position = new Vector2(MainGame.WIDTH/2-(groundAnimation.getWidthFrame()/2), -(groundAnimation.getHeightFrame()-GROUND_HEIGHT));

        rectangle = new Rectangle(position.x,position.y,groundAnimation.getWidthFrame(),groundAnimation.getHeightFrame()-GRASS_HEIGHT);
    }

    public void update(float dt, Rectangle bounds)
    {
        groundAnimation.update(dt);
        position.set(MainGame.WIDTH/2-(groundAnimation.getWidthFrame()/2), position.y);
        rectangle.setPosition(position.x,position.y);

        if (bounds.overlaps(rectangle))
        {
            for(SitingListener sitingListener : sitingListeners)
            {
                sitingListener.onSit(getCurrentHeight());
            }
        }
    }

    public float getCurrentHeight(){
        return texture.getHeight() + position.y - GRASS_HEIGHT;
    }



    public static void addStandingListener(SitingListener sitingListener)
    {
        sitingListeners.add(sitingListener);
    }

    /*public boolean isVisible() {
        return (position.y >= -texture.getHeight());
    }*/
}
