package com.ixfosa.pojo;

/**
 * Created by ixfosa on 2021/4/22 16:37
 */
public class ViewStudent {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "ViewStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

