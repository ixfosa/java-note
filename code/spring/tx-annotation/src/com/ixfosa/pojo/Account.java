package com.ixfosa.pojo;

/**
 * Created by ixfosa on 2021/4/9 11:05
 */
public class Account {
    private int id;
    private String username;
    private int money;

    public Account() {
    }

    public Account(int id, String username, int money) {
        this.id = id;
        this.username = username;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", money=" + money +
                '}';
    }
}
