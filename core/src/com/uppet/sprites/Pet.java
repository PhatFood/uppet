package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Pet {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    private Texture texture;
    public Pet(){
        position = new Vector3(0,0,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("pet.png");
    }
    public void setPosition(int x, int y)
    {
        position.x = x;
        position.y = y;
    }

    public void update(float dt){
        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,velocity.z);

        velocity.scl(1/dt);
    }

    public void jump(int x, int y){
        velocity.y = 250;
        if (x>position.x)
        {
            velocity.x = -250;
        }
        else
        {
            velocity.x = 250;
        }
    }

}
