package com.uppet.sprites.Enemy;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uppet.listener.BirdPeckListener;
import com.uppet.listener.PlayerOverListener;
import com.uppet.sprites.MainPet.Pet;
import com.uppet.sprites.Sprite;
import com.uppet.sprites.Visitor;

import java.util.ArrayList;

public class EnemyManager implements Sprite {

    private  final int ENEMY_SPACING = 300;
    private  final int ENEMY_COUNT = 3;

    private ArrayList<Enemy> enemies;
    private static ArrayList<PlayerOverListener> playerOverListeners = new ArrayList<>();
    private static ArrayList<BirdPeckListener> birdPeckListeners = new ArrayList<>();

    public EnemyManager()
    {
        enemies = new ArrayList<Enemy>();
        for(int i = 1; i<=ENEMY_COUNT;i++)
        {
            Enemy enemy = new Enemy(i*(ENEMY_SPACING+Enemy.ENEMY_HEIGHT));
            enemies.add(enemy);
        }
    }

    public void update(OrthographicCamera cam, Float dt, Pet pet)
    {
        for(Enemy enemy:enemies)
        {
            if(enemy.collides(pet.getBalloonBounds()))
            {
                for(PlayerOverListener playerOverListener : playerOverListeners)
                {
                    playerOverListener.onOver();
                }
            }
            else if(enemy.collides(pet.getBounds()))
            {
                for(BirdPeckListener birdPeckListener : birdPeckListeners)
                {
                    if (enemy.getFlyWay() == Enemy.FlyWay.flyLeft)
                    {
                        birdPeckListener.onPeckedLeft();
                    }
                    else {
                        birdPeckListener.onPeckedRight();
                    }
                }
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

    public static void addOverListener(PlayerOverListener playerOverListener)
    {
        playerOverListeners.add(playerOverListener);
    }

    public static void addBirdPeckListener(BirdPeckListener birdPeckListener)
    {
        birdPeckListeners.add(birdPeckListener);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
