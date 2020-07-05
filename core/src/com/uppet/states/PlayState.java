package com.uppet.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.uppet.MainGame;
import com.uppet.sprites.Cloud;
import com.uppet.sprites.Controller;
import com.uppet.sprites.Ground;
import com.uppet.sprites.Pet;

public class PlayState extends State  {
    private static final int CLOUD_SPACING = 300;
    private static final int CLOUD_COUNT = 3;


    private Pet pet;
    private Texture bg;
    private Ground ground;
    private Controller controller;

    private Array<Cloud> clouds;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        bg = new Texture("bg.png");
        ground = new Ground();
        pet = new Pet(Ground.GROUND_HEIGHT,0);
        pet.setPosition(MainGame.WIDTH/2-(pet.getPetTexture().getRegionWidth()/2),(int)ground.getCurrentHeight());
        controller = new Controller(MainGame.batch);

        clouds = new Array<Cloud>();

        for(int i = 1; i <= CLOUD_COUNT; i++)
        {
            clouds.add(new Cloud(i*(CLOUD_SPACING+Cloud.CLOUD_HEIGHT)));
        }
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

        for(Cloud cloud:clouds)
        {
            if(cam.position.y - (cam.viewportHeight / 2) > cloud.getPosLeft().y + cloud.getLeftCloud().getHeight()){
                cloud.reposition(cloud.getPosLeft().y + ((Cloud.CLOUD_HEIGHT + CLOUD_SPACING) * CLOUD_COUNT));
            }
        }

        cam.position.y += 1;
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,MainGame.WIDTH/2-(bg.getWidth()/2) + cam.position.x-cam.viewportWidth/2,MainGame.HEIGHT/2-(bg.getHeight()/2)+cam.position.y-cam.viewportHeight/2);
        sb.draw(pet.getBubbleTexture(),pet.getBubblePos().x,pet.getBubblePos().y);
        sb.draw(pet.getPetTexture(),pet.getPosition().x,pet.getPosition().y);

        if(ground.getCurrentHeight()>=0)
            sb.draw(ground.getTexture(),ground.getPosition().x,ground.getPosition().y);

        for(Cloud cloud : clouds){
            sb.draw(cloud.getLeftCloud(),cloud.getPosLeft().x,cloud.getPosLeft().y);
            sb.draw(cloud.getRightCloud(),cloud.getPosRight().x,cloud.getPosRight().y);
        }

        sb.end();

        controller.draw();
    }

    @Override
    public void dispose() {

    }
}
