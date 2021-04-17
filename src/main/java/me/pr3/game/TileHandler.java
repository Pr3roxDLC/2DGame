package me.pr3.game;


import com.google.common.eventbus.Subscribe;
import me.pr3.events.EventListener;
import me.pr3.events.InitializationEvent;
import me.pr3.enums.RenderLayer;
import me.pr3.util.render.TextureUtils;

import java.awt.*;
import java.util.HashMap;

public class TileHandler extends EventListener {

    public static HashMap<Point, Tile> tileMap = new HashMap<>();


    @Subscribe
    public void onInitPost(InitializationEvent.POST e) {

        for(int y = 0; y < 1080; y+=64){
            for(int x = 0; x < 1920; x +=64){
               tileMap.put(new Point(x,y), new Tile(TextureUtils.textureMap.get("Wall"), new Point(x,y), RenderLayer.BACKGROUND));
            }

        }

    }

}
