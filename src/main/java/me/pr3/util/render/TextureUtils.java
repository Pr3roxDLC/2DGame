package me.pr3.util.render;

import com.google.common.eventbus.Subscribe;
import me.pr3.events.InitializationEvent;
import me.pr3.events.EventListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class TextureUtils extends EventListener {

    public static HashMap<String, BufferedImage> textureMap = new HashMap<>();

    @Subscribe
    public void onInit(InitializationEvent.PRE e) throws IOException {

        textureMap.put("Wall", ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Wall.png"))));

    }

}
