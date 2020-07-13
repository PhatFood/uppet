package com.uppet;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.awt.image.AreaAveragingScaleFilter;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount = 0;
    private int frame;

    public Animation(TextureRegion region, int frameCount, float cycleTime)
    {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth()/frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i*frameWidth,0,frameWidth,region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
        {
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }

    public TextureRegion getFrameAt(int pos)
    {
        return frames.get(pos);
    }

    public float getWidthFrame()
    {
        if (frameCount==0)
            return 0;
        return frames.get(0).getRegionWidth();
    }

    public float getHeightFrame()
    {
        if (frameCount==0)
        return 0;
        return frames.get(0).getRegionHeight();
    }
}
