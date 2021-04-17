package me.pr3.events;

import me.pr3.Main;

public abstract class EventListener {

    public EventListener(){
        Main.EVENT_BUS.register(this);
    }

}
