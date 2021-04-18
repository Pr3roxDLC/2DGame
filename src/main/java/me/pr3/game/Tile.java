package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import me.pr3.events.EventListener;
import me.pr3.events.RenderEvent;
import me.pr3.enums.RenderLayer;
import me.pr3.util.render.TextureUtils;

import java.awt.*;


@SuppressWarnings("UnstableApiUsage")
public class Tile extends EventListener {

    private String texture;
    private Point point;
    private RenderLayer renderLayer;

    public Tile(String texture, Point point, RenderLayer renderLayer){

    this.texture = texture;
    this.point = point;
    this.renderLayer = renderLayer;

    }



    @Subscribe
    public void onRender(RenderEvent e){

        if(e.getRenderLayer() == renderLayer) {

            e.getGraphics().drawImage(TextureUtils.textureMap.get(texture), point.x, point.y, 64, 64, null);

        }

    }

}
