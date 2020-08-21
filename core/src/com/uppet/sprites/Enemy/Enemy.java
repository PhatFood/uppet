package com.uppet.sprites.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;
import com.uppet.GameInfo;
import com.uppet.MainGame;

import java.util.Random;


public class Enemy {

    public static final int ENEMY_HEIGHT = 150;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle rostrumRectangle, backRectangle;
    private static Texture textureBird;
    private static Texture textureBirdFlyLeft;
    private static Animation birdAnimation;
    private static Animation birdAnimationFlyLeft;
    public static enum FlyWay{flyRight, flyLeft;}
    private FlyWay flyWay;
    private float birdSpeed = 1;
    private Random rand;
    private GameInfo gameInfo;

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
        gameInfo = GameInfo.getInstance();
        setLevel();
        if(textureBird == null)
            textureBird = new Texture("bird.png");
        if(textureBirdFlyLeft == null)
            textureBirdFlyLeft = new Texture("birdrev.png");
        if(birdAnimation == null)
            birdAnimation = new Animation(new TextureRegion(textureBird),4,0.6f);
        if(birdAnimationFlyLeft == null)
            birdAnimationFlyLeft = new Animation(new TextureRegion(textureBirdFlyLeft),4,0.6f);

        rand = new Random();
        position = new Vector3(rand.nextInt(MainGame.WIDTH),y+600,0);
        velocity = new Vector3(0,0,0);

        backRectangle = new Rectangle(position.x+10, position.y+birdAnimation.getHeightFrame()-5, birdAnimation.getWidthFrame()-20,5);
        if(rand.nextInt(1) == 1)
        {
            flyWay = FlyWay.flyLeft;
            rostrumRectangle = new Rectangle(position.x,position.y+birdAnimation.getHeightFrame()/4,10,birdAnimation.getHeightFrame()/2);
        }
        else {
            rostrumRectangle = new Rectangle(position.x+birdAnimation.getWidthFrame(),position.y+birdAnimation.getHeightFrame()/4,5,birdAnimation.getHeightFrame()/2);
            flyWay = FlyWay.flyRight;
        }
    }

    private void setLevel() {
        if (gameInfo.getLevel() == 1)
        {
            birdSpeed = 2;
        }
        else if(gameInfo.getLevel() == 2)
        {
            birdSpeed = 3;
        }
        else if (gameInfo.getLevel() == 3)
        {
            birdSpeed = 5;
        }
    }

    public void update(float dt)
    {
        if(flyWay == FlyWay.flyLeft) {
            birdAnimationFlyLeft.update(dt);
        }
        else {
            birdAnimation.update(dt);
        }
        velocity.add(birdSpeed, 0,0);
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,velocity.z);

        rostrumRectangle.y = position.y+birdAnimation.getHeightFrame()/4;

        if(flyWay == FlyWay.flyLeft) {
            rostrumRectangle.x = position.x;
        }
        else {

            rostrumRectangle.x = position.x + birdAnimation.getWidthFrame()- rostrumRectangle.width;
        }

        if(position.x <= 0)
        {
            position.x = 0;
            birdSpeed = -birdSpeed;
            velocity.x = birdSpeed;
            flyWay = FlyWay.flyRight;
        }

        if(position.x >= (MainGame.WIDTH - birdAnimation.getWidthFrame()))
        {
            position.x = (MainGame.WIDTH - birdAnimation.getWidthFrame());
            birdSpeed = -birdSpeed;
            velocity.x = birdSpeed;
            flyWay = FlyWay.flyLeft;
        }

        velocity.scl(1/dt);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(rostrumRectangle);
    }
}
