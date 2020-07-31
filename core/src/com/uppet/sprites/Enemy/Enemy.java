package com.uppet.sprites.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;
import com.uppet.MainGame;

import java.util.Random;


public class Enemy {

    public static final int ENEMY_HEIGHT = 150;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle rectangle;
    private static Texture textureBird;
    private static Texture textureBirdFlyLeft;
    private static Animation birdAnimation;
    private static Animation birdAnimationFlyLeft;
    public static enum FlyWay{flyRight, flyLeft;}
    private FlyWay flyWay;
    private float birdSpeed = 5;
    private Random rand;

    public Vector3 getPosition(){return position;};

    public void reposition(float y){
        position.set(rand.nextInt(MainGame.WIDTH),y,0);
    }

    public TextureRegion getBirdTexture(){
        if(flyWay == FlyWay.flyLeft)
            return birdAnimationFlyLeft.getFrame();
        else
            return birdAnimation.getFrame();
    }

    public FlyWay getFlyWay(){
        return flyWay;
    }

    public Enemy(float y)
    {
        if(textureBird == null)
            textureBird = new Texture("bird.png");
        if(textureBirdFlyLeft == null)
            textureBirdFlyLeft = new Texture("birdrev.png");
        if(birdAnimation == null)
            birdAnimation = new Animation(new TextureRegion(textureBird),3,0.7f);
        if(birdAnimationFlyLeft == null)
            birdAnimationFlyLeft = new Animation(new TextureRegion(textureBirdFlyLeft),3,0.7f);

        rand = new Random();
        position = new Vector3(rand.nextInt(MainGame.WIDTH),y+600,0);
        velocity = new Vector3(0,0,0);
        rectangle = new Rectangle(position.x, position.y, birdAnimation.getWidthFrame(),birdAnimation.getHeightFrame());

        if(rand.nextInt(1) == 1)
        {
            flyWay = FlyWay.flyLeft;
        }
        else flyWay = FlyWay.flyRight;
    }

    public void update(float dt)
    {
        if(flyWay == FlyWay.flyLeft)
            birdAnimationFlyLeft.update(dt);
        else
            birdAnimation.update(dt);
        velocity.add(birdSpeed, 0,0);
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,velocity.z);

        if(position.x <= 0)
        {
            position.x = 0;
            birdSpeed = 3;
            velocity.x = birdSpeed;
            flyWay = FlyWay.flyRight;
        }

        if(position.x >= (MainGame.WIDTH - birdAnimation.getWidthFrame()))
        {
            position.x = (MainGame.WIDTH - birdAnimation.getWidthFrame());
            birdSpeed = -3;
            velocity.x = birdSpeed;
            flyWay = FlyWay.flyLeft;
        }
        rectangle.setPosition(position.x,position.y);

        velocity.scl(1/dt);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(rectangle);
    }
}
