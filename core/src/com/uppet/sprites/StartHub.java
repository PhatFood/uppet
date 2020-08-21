package com.uppet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.uppet.listener.StartGameListener;
import com.uppet.sprites.Enemy.Enemy;
import com.uppet.states.PlayState;

public class StartHub implements StartGameListener {
    private Texture tabToStartTexture, handTexture;
    private Vector2 posHand;
    private final float HAND_GAP = 150;
    private final float START_HAND_Y = 200;
    private final float END_HAND_Y = 130;
    private boolean isDown = true;
    private boolean isStart = false;

    public StartHub(){
        handTexture = new Texture("hand.png");
        posHand = new Vector2(120,START_HAND_Y);

        PlayState.addStartListener(this);
    }

    public void update(){
        if(!isStart) {
            if (isDown) {
                posHand.y -= 3;
                if (posHand.y < END_HAND_Y) {
                    isDown = false;
                }
            } else {
                posHand.y += 3;
                if (posHand.y > START_HAND_Y) {
                    isDown = true;
                }
            }
        }
    }

    public void render(SpriteBatch sb){
        if(!isStart) {
            sb.draw(handTexture, posHand.x, posHand.y);
            sb.draw(handTexture, posHand.x + HAND_GAP, posHand.y);
        }
    }

    @Override
    public void isStart() {
        if(!isStart)
        {
            isStart = true;
        }
    }
}
