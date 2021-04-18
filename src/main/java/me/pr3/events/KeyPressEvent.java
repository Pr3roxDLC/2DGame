package me.pr3.events;

import java.security.Key;

public class KeyPressEvent {


    public int getKey() {
        return key;
    }

    private int key;

    public KeyPressEvent(int key){
        this.key = key;
    }

}
