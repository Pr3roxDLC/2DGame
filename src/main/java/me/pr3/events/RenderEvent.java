package me.pr3.events;

import me.pr3.enums.RenderLayer;

import java.awt.*;

public class RenderEvent {

    private Graphics graphics;
    private RenderLayer renderLayer;


    public Graphics getGraphics() {
        return graphics;
    }

    public RenderLayer getRenderLayer() {
        return renderLayer;
    }


    public RenderEvent(Graphics g, RenderLayer renderLayer){
    this.graphics = g;
    this.renderLayer = renderLayer;
    }






}
