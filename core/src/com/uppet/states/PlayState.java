package com.uppet.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uppet.MainGame;
import com.uppet.sprites.Cloud;
import com.uppet.sprites.Controller;
import com.uppet.sprites.Ground;
import com.uppet.sprites.Pet;

public class PlayState extends State  {
    private Pet pet;
    private Texture bg;
    private Cloud cloud;
    private Ground ground;
    private Controller controller;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        bg = new Texture("bg.png");
        cloud = new Cloud(100);
        ground = new Ground();
        pet = new Pet(Ground.GROUND_HEIGHT,0);
        pet.setPosition(MainGame.WIDTH/2-(pet.getTexture().getWidth()/2),(int)ground.getCurrentHeight());
        controller = new Controller(MainGame.batch);
    }

    @Override
    protected void handleInput() {
        if (controller.isRightPressed())
        {
            pet.flyingRight();
        }
        else if(controller.isLeftPressed())
        {
            pet.flyingLeft();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        pet.update(dt);

        if(ground.getCurrentHeight() >= 0) {
            ground.update();
            if(ground.collides(pet.getBounds()))
            {
                pet.setPosition((int)pet.getPosition().x,(int)ground.getCurrentHeight());
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,MainGame.WIDTH/2-(bg.getWidth()/2),MainGame.HEIGHT/2-(bg.getHeight()/2));
        sb.draw(pet.getTexture(),pet.getPosition().x,pet.getPosition().y);


        if(ground.getCurrentHeight()>=0)
            sb.draw(ground.getTexture(),ground.getPosition().x,ground.getPosition().y);
        //sb.draw(cloud.getLeftCloud(),cloud.getPosLeft().x,cloud.getPosLeft().y);
        //sb.draw(cloud.getRightCloud(),cloud.getPosRight().x,cloud.getPosRight().y);
        sb.end();

        controller.draw();
    }

    @Override
    public void dispose() {

    }
}
