package me.pr3.game.entity;

import com.google.common.eventbus.Subscribe;
import com.sun.javafx.geom.Vec2f;
import me.pr3.enums.Direction;
import me.pr3.enums.RenderLayer;
import me.pr3.events.EventListener;
import me.pr3.events.RenderEvent;
import me.pr3.util.render.TextureUtils;

import java.util.HashSet;

public abstract class LivingEntity extends EventListener {

    protected float xAcceleration = 0.1f;
    protected float yAcceleration = 0.1f;
    protected Vec2f pos;
    protected Vec2f velocity = new Vec2f(1, -1);
    protected Vec2f wishdir = new Vec2f(0, 0);
    protected HashSet<Direction> directions = new HashSet<>();
    protected String texture;

    public LivingEntity(String texture, int posX,int posY) {
        pos = new Vec2f(posX, posY);
        this.texture = texture;
    }



}
