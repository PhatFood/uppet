package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.uppet.MainGame;

public class Pet {
    private float gravity = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Rectangle bounds;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public Pet(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("pet.png");
        bounds = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
    }
    public void setPosition(int x, int y)
    {
        position.x = x;
        position.y = y;
    }

    public void update(float dt){
        velocity.add(0,gravity,0);
        gravity -= 0.1;
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,velocity.z);

        if (position.y < 0)
        {
            position.y = 0;
        }

        if (position.x < (0) || position.x > (MainGame.WIDTH-texture.getWidth()))
        {
            bouncing();
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x,position.y);
    }

    private void bouncing()
    {
        velocity.y = 1;
        if (position.x < (0))
        {
            velocity.x = 2;
        }
        else if (position.x > (MainGame.WIDTH-texture.getWidth()))
        {
            velocity.x = -2;
        }
        gravity = -2;
    }

    public void jump(int x, int y){
        velocity.y = 150;
        if (x>position.x+texture.getWidth()/2)
        {
            velocity.x = -100;
        }
        else
        {
            velocity.x = 100;
        }
        gravity = 0;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void flyingLeft(){
        velocity.y = 150;
        velocity.x = -100;
        gravity = 0;
    }

    public void flyingRight()    {
        velocity.y = 150;
        velocity.x = 100;
        gravity = 0;
    }
}
