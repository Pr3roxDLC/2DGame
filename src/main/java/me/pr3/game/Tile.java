package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import javafx.geometry.BoundingBox;
import me.pr3.events.EventListener;
import me.pr3.events.RenderEvent;
import me.pr3.enums.RenderLayer;
import me.pr3.util.render.TextureUtils;

import java.awt.*;


@SuppressWarnings("UnstableApiUsage")
public abstract class Tile extends EventListener {

    protected String texture;
    protected BoundingBox boundingBox;
    protected RenderLayer renderLayer;


    public Tile(String texture, BoundingBox boundingBox, RenderLayer renderLayer){

    this.texture = texture;
    this.boundingBox = boundingBox;
    this.renderLayer = renderLayer;

    }
}
