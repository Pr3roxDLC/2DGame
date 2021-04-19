package me.pr3.game.entity;

import com.google.common.eventbus.Subscribe;
import javafx.geometry.BoundingBox;
import me.pr3.enums.Direction;
import me.pr3.enums.RenderLayer;
import me.pr3.events.*;
import me.pr3.game.GameLogicHandler;
import me.pr3.util.MathUtils;
import me.pr3.util.render.TextureUtils;

import java.awt.event.KeyEvent;

@SuppressWarnings("UnstableApiUsage")
public class Player extends LivingEntity {

    private boolean sprint = false;

    public Player(String texture,int posX,int posY) {
        super(texture, posX, posY);
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
            yAcceleration = 0.15f;
        }

        if(!directions.contains(Direction.RIGHT) && !directions.contains(Direction.LEFT)){
            wishdir.x = 0;
            xAcceleration = 0.15f;
        }

        velocity.x += (wishdir.x - velocity.x) * xAcceleration;
        velocity.y += (wishdir.y - velocity.y) * yAcceleration;

        if(Math.abs(velocity.x) < 0.5 && Math.abs(velocity.y) < 0.5){
            velocity.x = 0;
            velocity.y = 0;
        }


        if(sprint) {
            velocity.x = MathUtils.clampFloat(velocity.x, -10, 10);
            velocity.y = MathUtils.clampFloat(velocity.y, -10, 10);
        } else {
            velocity.x = MathUtils.clampFloat(velocity.x, -5, 5);
            velocity.y = MathUtils.clampFloat(velocity.y, -5, 5);
        }


        //Basic Collision Check, needs much more work put into, set velocity according to wall to 0
        //TODO Instead of returning, set the velocity component from which the player collided with to 0, to prevent being stuck on the walls

        if(GameLogicHandler.getCollidableTiles().stream().anyMatch(n -> n.boundingBox.intersects(new BoundingBox((int)(pos.x), (int)(pos.y + velocity.y), 64, 64)))){
            velocity.y = 0;
        }

        if(GameLogicHandler.getCollidableTiles().stream().anyMatch(n -> n.boundingBox.intersects(new BoundingBox((int)(pos.x + velocity.x), (int)(pos.y), 64, 64)))){
            velocity.x = 0;
        }

        if(GameLogicHandler.getCollidableTiles().stream().anyMatch(n -> n.boundingBox.intersects(new BoundingBox((int)(pos.x + velocity.x), (int)(pos.y + velocity.y), 64, 64)))){
            velocity.x = 0;
            velocity.y = 0;
        }

        if(!GameLogicHandler.getMapBorderBoundingBox().intersects(new BoundingBox((int)(pos.x), (int)(pos.y + velocity.y), 64, 64))){
           velocity.y = 0;
        }

        if(!GameLogicHandler.getMapBorderBoundingBox().intersects(new BoundingBox((int)(pos.x + velocity.x), (int)(pos.y), 64, 64))){
           velocity.x = 0;
        }

        if(!GameLogicHandler.getMapBorderBoundingBox().intersects(new BoundingBox((int)(pos.x + velocity.x), (int)(pos.y + velocity.y), 64, 64))){
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
            case KeyEvent.VK_CONTROL:
                sprint = true;
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
            case KeyEvent.VK_CONTROL:
                sprint = false;
                break;
        }
    }

    @Subscribe
    public void onRender(RenderEvent e) {

        if (e.getRenderLayer() == RenderLayer.PLAYER) {
            e.getGraphics().drawImage(TextureUtils.textureMap.get(texture), (int) pos.x, (int) pos.y, null);
        }

    }



}
