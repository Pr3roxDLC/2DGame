package me.pr3.events;

public class KeyReleaseEvent {

    public int getKey() {
        return key;
    }

    private int key;

    public KeyReleaseEvent(int key){

        this.key = key;

    }


}
