package com.uppet.sprites.Cloud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.uppet.listener.SitingListener;

import java.util.ArrayList;

public class CloudManager {
    private static final int CLOUD_SPACING = 300;
    private static int CLOUD_COUNT = 3;

    private ArrayList<Cloud> clouds;
    private static ArrayList<SitingListener> sitingListeners = new ArrayList<>();

    public CloudManager()
    {
        clouds = new ArrayList<Cloud>();
        for(int i = 1; i <= CLOUD_COUNT; i++)
        {
            clouds.add(new Cloud(i*(CLOUD_SPACING+Cloud.CLOUD_HEIGHT)));
        }
    }

    public void update(OrthographicCamera cam, Rectangle player, boolean falling)
    {
        for(Cloud cloud:clouds)
        {
            if(cam.position.y - (cam.viewportHeight / 2) > cloud.getPos().y + cloud.getCloud().getHeight()){
                cloud.reposition(cloud.getPos().y + ((Cloud.CLOUD_HEIGHT + CLOUD_SPACING) * CLOUD_COUNT));
            }

            if(cloud.collides(player) && !falling) {
                for(SitingListener sitingListener : sitingListeners)
                {
                    sitingListener.onSit(cloud.getHeightStand());
                }
            }
        }
    }

    public void render(SpriteBatch sb)
    {
        for(Cloud cloud : clouds){
            sb.draw(cloud.getCloud(),cloud.getPos().x,cloud.getPos().y);
        }
    }

    public static void addStandingListener(SitingListener sitingListener)
    {
        sitingListeners.add(sitingListener);
    }
}
