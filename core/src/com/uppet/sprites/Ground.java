package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uppet.MainGame;
import com.uppet.StandingListener;

import java.util.ArrayList;

public class Ground {
    public static final int GROUND_HEIGHT = 450;
    private static final int GRASS_HEIGHT = 370;
    private Vector2 position;
    private Texture texture;
    private Rectangle rectangle;
    private static ArrayList<StandingListener> standingListeners = new ArrayList<>();

    public Vector2 getPosition() {return position;}
    public Texture getTexture() {return texture;}

    public Ground() {
        texture = new Texture("ground1.png");
        position = new Vector2(MainGame.WIDTH/2-(texture.getWidth()/2), -(texture.getHeight()-GROUND_HEIGHT));

        rectangle = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight()-GRASS_HEIGHT);
    }

    public void update(Rectangle bounds)
    {
        position.set(MainGame.WIDTH/2-(texture.getWidth()/2), position.y);
        rectangle.setPosition(position.x,position.y);

        if (bounds.overlaps(rectangle))
        {
            for(StandingListener standingListener : standingListeners)
            {
                standingListener.onStand(getCurrentHeight());
            }
        }
    }

    public float getCurrentHeight(){
        return texture.getHeight() + position.y - GRASS_HEIGHT;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(rectangle);
    }



    public static void addStandingListener(StandingListener standingListener)
    {
        standingListeners.add(standingListener);
    }

    /*public boolean isVisible() {
        return (position.y >= -texture.getHeight());
    }*/
}
