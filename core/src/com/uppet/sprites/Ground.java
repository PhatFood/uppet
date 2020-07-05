package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uppet.MainGame;

public class Ground {
    public static final int GROUND_HEIGHT = 200;
    private static final int GRASS_HEIGHT = 28;
    private float gravity = 0;
    private Vector2 position;
    private Texture texture;
    private Rectangle rectangle;

    public Vector2 getPosition() {return position;}
    public Texture getTexture() {return texture;}

    public Ground() {
        texture = new Texture("ground.png");
        position = new Vector2(MainGame.WIDTH/2-(texture.getWidth()/2), -(texture.getHeight()-GROUND_HEIGHT));

        rectangle = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight()-GRASS_HEIGHT);
    }

    public void update()
    {
        position.set(MainGame.WIDTH/2-(texture.getWidth()/2), position.y);
        rectangle.setPosition(position.x,position.y);
    }

    public float getCurrentHeight(){
        return texture.getHeight() + position.y - GRASS_HEIGHT;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(rectangle);
    }

    /*public boolean isVisible() {
        return (position.y >= -texture.getHeight());
    }*/
}
