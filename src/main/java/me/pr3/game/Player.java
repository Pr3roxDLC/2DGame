package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import com.sun.javafx.geom.Vec2f;
import me.pr3.enums.Direction;
import me.pr3.events.*;
import me.pr3.enums.RenderLayer;
import me.pr3.util.render.TextureUtils;

import java.util.HashSet;

public class Player extends EventListener {

    private float xAcceleration = 0.1f;
    private float yAcceleration = 0.1f;
    private Vec2f pos = new Vec2f(100, 100);
    private Vec2f velocity = new Vec2f(1, -1);
    private Vec2f wishdir = new Vec2f(0, 0);
    private HashSet<Direction> directions = new HashSet<>();


    public Player() {


    }


    @Subscribe
    public void onUpdate(UpdateEvent e) {

        for (Direction dir : directions) {

            switch (dir) {
                case UP:
                    wishdir.y = -10;
                    break;
                case DOWN:
                    wishdir.y = 10;
                    break;
                case RIGHT:
                    wishdir.x = 10;
                    break;
                case LEFT:
                    wishdir.x = -10;
                    break;
            }

        }

        xAcceleration = 0.05f;
        yAcceleration = 0.05f;

        if(!directions.contains(Direction.UP) && !directions.contains(Direction.DOWN)){
            wishdir.y = 0;
            yAcceleration = 0.1f;
        }

        if(!directions.contains(Direction.RIGHT) && !directions.contains(Direction.LEFT)){
            wishdir.x = 0;
            xAcceleration = 0.1f;
        }

        velocity.x += (wishdir.x - velocity.x) * xAcceleration;
        velocity.y += (wishdir.y - velocity.y) * yAcceleration;

        if(Math.abs(velocity.x) < 0.5 && Math.abs(velocity.y) < 0.5){
            velocity.x = 0;
            velocity.y = 0;
        }

        pos.x += velocity.x;
        pos.y += velocity.y;


    }


    @Subscribe
    public void onKeyPress(KeyPressEvent e) {

        switch (e.getKey()) {
            case 38:
                directions.add(Direction.UP);
                break;
            case 37:
                directions.add(Direction.LEFT);
                break;
            case 40:
                directions.add(Direction.DOWN);
                break;
            case 39:
                directions.add(Direction.RIGHT);
                break;
        }
    }


    @Subscribe
    public void onKeyRelease(KeyReleaseEvent e) {

        switch (e.getKey()) {
            case 38:
                directions.remove(Direction.UP);
                break;
            case 37:
                directions.remove(Direction.LEFT);
                break;
            case 40:
                directions.remove(Direction.DOWN);
                break;
            case 39:
                directions.remove(Direction.RIGHT);
                break;
        }
    }


    @Subscribe
    public void onRender(RenderEvent e) {

        if (e.getRenderLayer() == RenderLayer.PLAYER) {

            e.getGraphics().drawImage(TextureUtils.textureMap.get("Wall"), (int) pos.x, (int) pos.y, null);

        }

    }

}
