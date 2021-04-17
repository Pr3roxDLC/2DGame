package me.pr3.events;

import java.security.Key;

public class KeyPressEvent {


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    int key;

    public KeyPressEvent(int key){
        this.key = key;
    }

}
