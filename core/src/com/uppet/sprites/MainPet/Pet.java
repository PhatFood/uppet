package com.uppet.sprites.MainPet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.uppet.Animation;
import com.uppet.listener.BirdPeckListener;
import com.uppet.MainGame;
import com.uppet.listener.PlayerOverListener;
import com.uppet.listener.SitingListener;
import com.uppet.listener.TapListener;
import com.uppet.sprites.Cloud.CloudManager;
import com.uppet.sprites.Enemy.EnemyManager;
import com.uppet.sprites.Ground;
import com.uppet.states.PlayState;

public class Pet implements TapListener, SitingListener, PlayerOverListener, BirdPeckListener {
    private float gravity = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Rectangle footBounds;
    private Texture textureFlying;
    private Texture textureSiting;
    private Balloon balloon;
    private Animation animationFlying;
    private Animation animationSiting;
    private boolean isOver;
    private enum PetState {flying,siting};
    private PetState currentState;


    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getPetTexture() {
        if(currentState == PetState.flying)
            return animationFlying.getFrame();
        else return animationSiting.getFrame();
    }

    public Pet(int x, int y){
        currentState = PetState.siting;
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        isOver = false;

        textureFlying = new Texture("petanishiba.png");
        textureSiting = new Texture("petanishibasit.png");
        animationFlying = new Animation(new TextureRegion(textureFlying),5,0.7f);
        animationSiting = new Animation(new TextureRegion(textureSiting),5,0.7f);

        bounds = new Rectangle(x,y, animationFlying.getWidthFrame(), animationFlying.getHeightFrame());
        footBounds = new Rectangle(x+ animationFlying.getWidthFrame()/4,y, animationFlying.getWidthFrame()/2, 1);

        balloon = new Balloon(x,y, animationFlying.getWidthFrame());

        PlayState.addTapListener(this);
        Ground.addStandingListener(this);
        EnemyManager.addOverListener(this);
        EnemyManager.addBirdPeckListener(this);
        Balloon.addOverListener(this);
        CloudManager.addStandingListener(this);
    }

    public void setPosition(float x, float y)
    {
        position.x = x;
        position.y = y;

        bounds.setPosition(position.x,position.y);

        footBounds.setPosition(position.x+ animationFlying.getWidthFrame()/4,position.y);

        balloon.setPosition(x,y, animationFlying.getWidthFrame());

        velocity.y = 0;
        gravity = 0;
    }

    public void render(SpriteBatch sb)
    {
        if(balloon.getTexture()!=null)
            sb.draw(balloon.getTexture(),balloon.getPos().x,balloon.getPos().y);
        sb.draw(getPetTexture(),position.x,position.y);
    }

    public void over()
    {
        isOver = true;
        balloon.over();
    }

    public void update(float dt){
        animationFlying.update(dt);
        animationSiting.update(dt);
        velocity.add(0,gravity,0);
        gravity -= 0.1;
        velocity.scl(dt);
        position.add(velocity.x,velocity.y,velocity.z);
        if(position.x < 0)
        {
            position.x = 0;
        }
        if(position.x > MainGame.WIDTH- animationFlying.getWidthFrame())
        {
            position.x = MainGame.WIDTH- animationFlying.getWidthFrame();
        }


        if(velocity.x < 0)
        {
            velocity.add(0.01f,0,0);
        }
        else if(velocity.x > 0)
        {
            velocity.add(-0.01f,0,0);
        }

        if (position.x == (0) || position.x == (MainGame.WIDTH- animationFlying.getWidthFrame()))
        {
            bouncing();
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x,position.y);
        footBounds.setPosition(position.x+ animationFlying.getWidthFrame()/4,position.y);
        balloon.update(dt,position.x,position.y, animationFlying.getWidthFrame());
        if(balloon.isOver())
            isOver = true;
    }

    private void bouncing()
    {
        if (position.x == (0))
        {
            velocity.x = 2.5f;
        }
        else if (position.x == (MainGame.WIDTH- animationFlying.getWidthFrame()))
        {
            velocity.x = -2.5f;
        }
        gravity = -2;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public Rectangle getFootBounds() { return footBounds;}

    public Rectangle getBalloonBounds(){return balloon.getBounds();}

    public boolean isFalling(){
        return (velocity.y > 0);
    }

    public void flyingLeft(){
        if(!isOver)
        { currentState = PetState.flying;
        velocity.y = 200;
        velocity.x = -150;
        gravity = 0;
        }
    }

    public void flyingRight(){
        if(!isOver) {
            currentState = PetState.flying;
            velocity.y = 200;
            velocity.x = 150;
            gravity = 0;
        }
    }

    @Override
    public void onTapRight() {
        flyingRight();
    }

    @Override
    public void onTapLeft() {
        flyingLeft();
    }

    @Override
    public void onSit(float y) {
        currentState = PetState.siting;
        position.y = y;
        bounds.setPosition(position.x,position.y);
        balloon.setPosition(y, animationFlying.getWidthFrame());

        velocity.y = 0;
        gravity = 0;
    }

    @Override
    public void onOver() {
        over();
    }

    @Override
    public void onPeckedRight() {
        currentState = PetState.flying;
        velocity.y = 250;
        velocity.x = 150;
    }

    @Override
    public void onPeckedLeft() {
        currentState = PetState.flying;
        velocity.y = 200;
        velocity.x = -150;
    }
}
