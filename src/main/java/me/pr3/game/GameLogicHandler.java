package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import javafx.geometry.BoundingBox;
import me.pr3.enums.RenderLayer;
import me.pr3.events.EventListener;
import me.pr3.events.InitializationEvent;
import me.pr3.game.entity.PassiveEnemy;
import me.pr3.game.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

@SuppressWarnings("UnstableApiUsage")
public class GameLogicHandler extends EventListener {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<PassiveEnemy> passiveEnemies = new ArrayList<>();
    public static HashMap<Point, Tile> tileMap = new HashMap<>();

    public GameLogicHandler(){

    }


    public void createPlayer(){

        players.add(new Player("wall" , 100, 100));

    }

    public void createPassiveEnemy(){
        passiveEnemies.add(new PassiveEnemy("wall", 500, 200));
    }


    public static HashSet<Tile> getCollidableTiles(){
        return tileMap.values().stream().filter(n -> n instanceof Wall).collect(Collectors.toCollection(HashSet::new));
    }

    //return Map Border Bounding Box
    public static BoundingBox getMapBorderBoundingBox() {
        return new BoundingBox(64,64,1856,1016);
    }

    @Subscribe
    public void onInitPost(InitializationEvent.POST e) {

        createPlayer();
        createPassiveEnemy();


        for(int y = 0; y < 1080; y+=64){
            for(int x = 0; x < 1920; x +=64){
                tileMap.put(new Point(x,y), new Floor("floor", new BoundingBox(x,y,64,64), RenderLayer.BACKGROUND));
            }

        }

        for(int x = 0; x < 5; x++){

            tileMap.put(new Point(400 + x*64, 500), new Wall("wall", new BoundingBox(400 + x * 64, 500, 64, 64),RenderLayer.WALLS));

        }

    }

}
