package me.pr3;
//new tile test
import com.google.common.eventbus.EventBus;
import me.pr3.events.*;
import me.pr3.enums.RenderLayer;
import me.pr3.game.GameLogicHandler;
import me.pr3.util.render.TextureUtils;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


@SuppressWarnings("UnstableApiUsage")
public class Main extends JFrame implements Runnable, KeyListener {

    public static EventBus EVENT_BUS = new EventBus();
    public static GameLogicHandler GAME_LOGIC_HANDLER = new GameLogicHandler();
    public static TextureUtils TEXTURE_UTILS = new TextureUtils();
    public static boolean shouldClose = false;


    long time = System.currentTimeMillis();

    public static void main(String[] args) {
        Thread f = new Thread(new Main());
        f.start();
    }

    @Override
    public void run() {
        init();
        while (!shouldClose) {
            try {
                update();
                repaint();
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Decentralized Rendering code to declutter the Main class
    public void paint(Graphics g) {
        Image offscreen = createImage(1920, 1080);
        Graphics2D offscreenGraphics = (Graphics2D) offscreen.getGraphics();
        for(RenderLayer renderLayer : RenderLayer.values()){
            EVENT_BUS.post(new RenderEvent(offscreenGraphics, renderLayer));
        }
        g.drawImage(offscreen, 0, 0, null);
    }


    private void update() {
        EVENT_BUS.post(new UpdateEvent());
    }


    private void init() {
        //Game IO Related Init Work
        EVENT_BUS.post(new InitializationEvent.PRE());

        setSize(1920, 1080);
        addKeyListener(this);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Gameplay Related Init Work
        EVENT_BUS.post(new InitializationEvent.POST());
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        EVENT_BUS.post(new KeyPressEvent(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
            EVENT_BUS.post(new KeyReleaseEvent(e.getKeyCode()));
    }
}
