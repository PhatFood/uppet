package com.uppet.sprites.Cloud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uppet.MainGame;

import java.util.Random;

public class Cloud {
    public static final int CLOUD_HEIGHT = 300;

    private Texture Cloud;
    private Vector2 pos;
    private Random rand;
    private Rectangle rectangle;

    public Cloud(float y)
    {
        Cloud = new Texture("cloud.png");
        rand = new Random();

        pos = new Vector2(rand.nextInt(MainGame.WIDTH-Cloud.getWidth()),y+400);
        rectangle = new Rectangle(pos.x,pos.y + Cloud.getHeight()-10,Cloud.getWidth(),5);
    }

    public float getHeightStand(){
        return rectangle.getHeight()+rectangle.y;
    }

    public Texture getCloud() {
        return Cloud;
    }

    public Vector2 getPos() {
        return pos;
    }


    public void reposition(float y){
     pos.set(rand.nextInt(MainGame.WIDTH-Cloud.getWidth()) ,y);
     rectangle.setPosition(pos.x,pos.y+Cloud.getHeight()-10);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(rectangle);
    }
}
