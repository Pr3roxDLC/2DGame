package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import me.pr3.enums.RenderLayer;
import me.pr3.events.EventListener;
import me.pr3.events.RenderEvent;

@SuppressWarnings("UnstableApiUsage")
public class Wall extends EventListener {

    private int posX;
    private int posY;
    private String texture;
    private boolean hasCollision;


    @Subscribe
    public void onRender(RenderEvent e){

    if(e.getRenderLayer() == RenderLayer.WALLS){



    }

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public boolean isHasCollision() {
        return hasCollision;
    }

    public void setHasCollision(boolean hasCollision) {
        this.hasCollision = hasCollision;
    }



    public Wall(int posX, int posY, String texture, boolean hasCollision) {

        this.hasCollision = hasCollision;
        this.posX = posX;
        this.posY = posY;
        this.texture = texture;

    }

}
