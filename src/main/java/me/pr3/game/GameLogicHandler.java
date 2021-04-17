package me.pr3.game;

import me.pr3.events.EventListener;

import java.util.ArrayList;

public class GameLogicHandler extends EventListener {

    private ArrayList<Player> players = new ArrayList<>();

    public GameLogicHandler(){

        createPlayer();

    }


    public void createPlayer(){

        players.add(new Player());

    }

}
