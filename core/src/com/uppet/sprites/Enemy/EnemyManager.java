package com.uppet.sprites.Enemy;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.uppet.sprites.Enemy.Enemy;
import com.uppet.sprites.MainPet.Pet;

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

    public void update(OrthographicCamera cam,Float dt, Pet pet)
    {
        for(Enemy enemy:enemies)
        {
            if(enemy.collides(pet.getBalloonBounds()))
            {
                pet.over();
            }
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

}
