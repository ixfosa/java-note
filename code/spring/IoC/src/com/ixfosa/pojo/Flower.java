package com.ixfosa.pojo;

/**
 * Created by ixfosa on 2021/4/6 17:23
 */
public class Flower {
    private int id;
    private String name;

    public Flower() {
    }

    public Flower(int id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
