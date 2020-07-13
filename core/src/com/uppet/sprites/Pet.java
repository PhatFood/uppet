package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;
import com.uppet.MainGame;

public class Pet {
    private float gravity = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texturePetAni;

    private Balloon balloon;

    private Animation petAnimation;

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getPetTexture() {
        return petAnimation.getFrame();
    }

    public Pet(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        texturePetAni = new Texture("petani1.png");
        petAnimation = new Animation(new TextureRegion(texturePetAni),5,0.7f);

        bounds = new Rectangle(x,y, petAnimation.getWidthFrame(), petAnimation.getHeightFrame());

        balloon = new Balloon(x,y,petAnimation.getWidthFrame());
    }

    public void setPosition(float x, float y)
    {
        position.x = x;
        position.y = y;

        bounds.setPosition(position.x,position.y);

        balloon.setPosition(x,y,petAnimation.getWidthFrame());

        velocity.y = 0;
        gravity = 0;
    }

    public void render(SpriteBatch sb)
    {
        sb.draw(balloon.getTexture(),balloon.getPos().x,balloon.getPos().y);
        sb.draw(getPetTexture(),position.x,position.y);
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

        balloon.update(dt,position.x,position.y,petAnimation.getWidthFrame());
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
