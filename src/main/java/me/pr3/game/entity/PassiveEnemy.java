package me.pr3.game.entity;

import com.google.common.eventbus.Subscribe;
import javafx.geometry.BoundingBox;
import me.pr3.enums.Direction;
import me.pr3.enums.RenderLayer;
import me.pr3.events.KeyPressEvent;
import me.pr3.events.KeyReleaseEvent;
import me.pr3.events.RenderEvent;
import me.pr3.events.UpdateEvent;
import me.pr3.game.GameLogicHandler;
import me.pr3.game.entity.LivingEntity;
import me.pr3.util.MathUtils;
import me.pr3.util.render.TextureUtils;

import java.util.Random;

public class PassiveEnemy extends LivingEntity {

    Random rand = new Random();
    int upperbound = 4;
    int intRandom = 0;





    public PassiveEnemy(String texture,int posX,int posY) {
        super(texture, posX, posY);
    }

    @Subscribe
    public void onUpdate(UpdateEvent e) {

        newRandomDirection();

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


        velocity.x = MathUtils.clampFloat(velocity.x, -2, 2);
        velocity.y = MathUtils.clampFloat(velocity.y, -2, 2);



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


    public void newRandomDirection() {

        intRandom = rand.nextInt(upperbound);

        switch (intRandom) {
            case 0:
                if(directions.contains(Direction.UP)) {
                    directions.remove(Direction.UP);
                } else {
                    directions.add(Direction.UP);
                }
                break;
            case 1:
                if(directions.contains(Direction.DOWN)) {
                    directions.remove(Direction.DOWN);
                } else {
                    directions.add(Direction.DOWN);
                }
                break;
            case 2:
                if(directions.contains(Direction.LEFT)) {
                    directions.remove(Direction.LEFT);
                } else {
                    directions.add(Direction.LEFT);
                }
                break;
            case 3:
                if(directions.contains(Direction.RIGHT)) {
                    directions.remove(Direction.RIGHT);
                } else {
                    directions.add(Direction.RIGHT);
                }
                break;
        }
    }

    @Subscribe
    public void onRender(RenderEvent e) {

        if (e.getRenderLayer() == RenderLayer.ENEMIES) {
            e.getGraphics().drawImage(TextureUtils.textureMap.get(texture), (int) pos.x, (int) pos.y, null);
        }

    }

}
