package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;
import com.uppet.MainGame;

public class Pet {
    private int BUBBLE_MAX_STATE = 3;
    private float gravity = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texturePetAni;

    private Texture bubbleTexture;
    private Rectangle bubbleBounds;
    private Vector3 bubblePos;

    private Animation petAnimation;

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getPetTexture() {
        return petAnimation.getFrame();
    }

    public Vector3 getBubblePos(){return bubblePos; }

    public Texture getBubbleTexture(){return  bubbleTexture; }

    public Pet(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        texturePetAni = new Texture("petani1.png");
        petAnimation = new Animation(new TextureRegion(texturePetAni),5,0.7f);

        bounds = new Rectangle(x,y, petAnimation.getWidthFrame(), petAnimation.getHeightFrame());

        bubbleTexture = new Texture("bubble.png");
        bubblePos = new Vector3(x-(bubbleTexture.getWidth()/2-petAnimation.getWidthFrame()/2),y+55,0);
        bubbleBounds = new Rectangle(bubblePos.x,bubblePos.y,bubbleTexture.getWidth(),bubbleTexture.getHeight());
    }
    public void setPosition(float x, float y)
    {
        position.x = x;
        position.y = y;

        bounds.setPosition(position.x,position.y);
        bubblePos.x = position.x - (bubbleTexture.getWidth()/2-petAnimation.getWidthFrame()/2);
        bubblePos.y = position.y+55;
        bubbleBounds.setPosition(bubblePos.x,bubblePos.y);

        velocity.y = 0;
        gravity = 0;
    }

    public void update(float dt){
        petAnimation.update(dt);
        velocity.add(0,gravity,0);
        gravity -= 0.1;
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,velocity.z);
        if(position.x < 0)
        {
            position.x = 0;
        }
        if(position.x > MainGame.WIDTH- petAnimation.getWidthFrame())
        {
            position.x = MainGame.WIDTH- petAnimation.getWidthFrame();
        }


        if(velocity.x < 0)
        {
            velocity.add(0.01f,0,0);
        }
        else if(velocity.x > 0)
        {
            velocity.add(-0.01f,0,0);
        }

        if (position.x == (0) || position.x == (MainGame.WIDTH- petAnimation.getWidthFrame()))
        {
            bouncing();
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x,position.y);
        bubblePos.x = position.x - (bubbleTexture.getWidth()/2-petAnimation.getWidthFrame()/2);
        bubblePos.y = position.y+55;
        bubbleBounds.setPosition(bubblePos.x,bubblePos.y);
    }

    private void bouncing()
    {
        if (position.x == (0))
        {
            velocity.x = 2.5f;
        }
        else if (position.x == (MainGame.WIDTH- petAnimation.getWidthFrame()))
        {
            velocity.x = -2.5f;
        }
        gravity = -2;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void flyingLeft(){
        velocity.y = 200;
        velocity.x = -150;
        gravity = 0;
    }

    public void flyingRight()    {
        velocity.y = 200;
        velocity.x = 150;
        gravity = 0;
    }
}
