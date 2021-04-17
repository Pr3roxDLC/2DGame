package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import me.pr3.events.EventListener;
import me.pr3.events.RenderEvent;
import me.pr3.enums.RenderLayer;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("UnstableApiUsage")
public class Tile extends EventListener {

    private BufferedImage texture;
    private Point point;
    private RenderLayer renderLayer;

    public Tile(BufferedImage texture, Point point, RenderLayer renderLayer){

    this.texture = texture;
    this.point = point;
    this.renderLayer = renderLayer;

    }



    @Subscribe
    public void onRender(RenderEvent e){

        if(e.getRenderLayer() == renderLayer) {

            e.getGraphics().drawImage(texture, point.x, point.y, 64, 64, null);

        }

    }

}
