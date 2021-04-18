package me.pr3.game;

import com.google.common.eventbus.Subscribe;
import javafx.geometry.BoundingBox;
import me.pr3.enums.RenderLayer;
import me.pr3.events.EventListener;
import me.pr3.events.InitializationEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameLogicHandler extends EventListener {

    private ArrayList<Player> players = new ArrayList<>();
    public static HashMap<Point, Tile> tileMap = new HashMap<>();

    public GameLogicHandler(){

        createPlayer();

    }


    public void createPlayer(){

        players.add(new Player());

    }

    @Subscribe
    public void onInitPost(InitializationEvent.POST e) {

        for(int y = 0; y < 1080; y+=64){
            for(int x = 0; x < 1920; x +=64){
                tileMap.put(new Point(x,y), new Floor("floor", new BoundingBox(x,y,64,64), RenderLayer.BACKGROUND));
            }

        }

    }

}
