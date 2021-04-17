package me.pr3.events;

public class KeyReleaseEvent {

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    int key;

    public KeyReleaseEvent(int key){

        this.key = key;

    }


}
