package com.uppet.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class EnemyManager {

    private static final int ENEMY_SPACING = 300;
    private static final int ENEMY_COUNT = 3;

    private Array<Enemy> enemies;

    public EnemyManager()
    {
        enemies = new Array<Enemy>();
        for(int i = 1; i<=ENEMY_COUNT;i++)
        {
            enemies.add(new Enemy(i*(ENEMY_SPACING+Enemy.ENEMY_HEIGHT)));
        }
    }

    public void update(OrthographicCamera cam,Float dt)
    {
        for(Enemy enemy:enemies)
        {
            enemy.update(dt);
            if(cam.position.y - (cam.viewportHeight / 2) > enemy.getPosition().y + enemy.getBirdTexture().getRegionHeight()){
                enemy.reposition(enemy.getPosition().y + ((Enemy.ENEMY_HEIGHT + ENEMY_SPACING) * ENEMY_COUNT));
            }
        }
    }

    public void render(SpriteBatch sb)
    {
        for(Enemy enemy:enemies){
            sb.draw(enemy.getBirdTexture(),enemy.getPosition().x,enemy.getPosition().y);
        }
    }

    /*
    private static final int CLOUD_SPACING = 300;
    private static int CLOUD_COUNT = 3;

    private Array<Cloud> clouds;

    public CloudManager()
    {
        clouds = new Array<Cloud>();
        for(int i = 1; i <= CLOUD_COUNT; i++)
        {
            clouds.add(new Cloud(i*(CLOUD_SPACING+Cloud.CLOUD_HEIGHT)));
        }
    }

    public void update(OrthographicCamera cam)
    {
        for(Cloud cloud:clouds)
        {
            if(cam.position.y - (cam.viewportHeight / 2) > cloud.getPosLeft().y + cloud.getLeftCloud().getHeight()){
                cloud.reposition(cloud.getPosLeft().y + ((Cloud.CLOUD_HEIGHT + CLOUD_SPACING) * CLOUD_COUNT));
            }
        }
    }

    public void render(SpriteBatch sb)
    {
        for(Cloud cloud : clouds){
            sb.draw(cloud.getLeftCloud(),cloud.getPosLeft().x,cloud.getPosLeft().y);
            sb.draw(cloud.getRightCloud(),cloud.getPosRight().x,cloud.getPosRight().y);
        }
    }*/
}
