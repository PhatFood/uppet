package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Cloud {
    public static final int CLOUD_HEIGHT = 300;

    private static final int FLUCTUATION = 150;
    private static final int CLOUD_GAP = 150;
    private static final int LOWEST_RIGHT = 50;
    private Texture leftCloud, rightCloud;
    private Vector2 posLeft, posRight;
    private Random rand;

    public Cloud(float y)
    {
        leftCloud = new Texture("cloud.png");
        rightCloud = new Texture("cloud.png");
        rand = new Random();

        posRight = new Vector2(rand.nextInt(FLUCTUATION) + CLOUD_GAP + LOWEST_RIGHT + rand.nextInt(25),y+400);
        posLeft = new Vector2(posRight.x - CLOUD_GAP - leftCloud.getWidth() - rand.nextInt(25) , y+400);
    }

    public Texture getLeftCloud() {
        return leftCloud;
    }

    public Texture getRightCloud() {
        return rightCloud;
    }

    public Vector2 getPosLeft() {
        return posLeft;
    }

    public Vector2 getPosRight() {
        return posRight;
    }

    public void reposition(float y){
     posRight.set(rand.nextInt(FLUCTUATION) + CLOUD_GAP + LOWEST_RIGHT,y);
     posLeft.set(posRight.x - CLOUD_GAP - leftCloud.getWidth(), y);
    }
}
