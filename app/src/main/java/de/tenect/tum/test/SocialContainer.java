package de.tenect.tum.test;

import android.widget.ImageView;

/**
 * Created by Sören on 30.11.2014.
 */
public class SocialContainer {

    ImageView circle;
    boolean isActive;
    float positionX;
    float positionY;

    public SocialContainer(float positionX, float positionY, boolean isActive, ImageView circle){
        this.isActive = isActive;
        this.positionX = positionX;
        this.positionY = positionY;
        this.circle = circle;
    }
}
