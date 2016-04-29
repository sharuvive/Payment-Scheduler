package com.example.username.weddingplanning;

/**
 * Created by Sharu Vive on 2/07/2016.
 */
public class category {
    private int id;
    private String name;

    public category() {
    }

    public category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
