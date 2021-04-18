package me.pr3.util.render;

import com.google.common.eventbus.Subscribe;
import me.pr3.events.InitializationEvent;
import me.pr3.events.EventListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;

@SuppressWarnings("UnstableApiUsage")
public class TextureUtils extends EventListener {

    public static HashMap<String, BufferedImage> textureMap = new HashMap<>();

    @Subscribe
    public void onInit(InitializationEvent.PRE e) throws IOException {

        URL url = getClass().getClassLoader().getResource("dummy.png");
        if(url == null){
            System.out.println("[Error]: Failed to Locate Resources, exiting game");
            System.exit(1);
        }
        String processedURL = url.getPath().split("dummy.png")[0];
        File folder = new File(processedURL);
        for(File file : Objects.requireNonNull(folder.listFiles())){
            if(!file.isDirectory()) {
                textureMap.put(file.getName().split(".png")[0].toLowerCase(), ImageIO.read(file));
            }

        }

      //  textureMap.put("Wall", ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Wall.png"))));

    }




}
