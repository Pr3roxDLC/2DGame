package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import javafx.geometry.BoundingBox;
import me.pr3.enums.RenderLayer;
import me.pr3.events.EventListener;
import me.pr3.events.RenderEvent;
import me.pr3.util.render.TextureUtils;

import java.awt.*;

@SuppressWarnings("UnstableApiUsage")
public class Wall extends Tile {

    public Wall (String texture, BoundingBox boundingBox, RenderLayer renderLayer) {
        super(texture, boundingBox, renderLayer);

    }

    @Subscribe
    public void onRender(RenderEvent e) {

        if (e.getRenderLayer() == RenderLayer.BACKGROUND) {
            e.getGraphics().drawImage(TextureUtils.textureMap.get("wall"), (int) boundingBox.getMinX(), (int) boundingBox.getMinY(), null);
        }
    }
}