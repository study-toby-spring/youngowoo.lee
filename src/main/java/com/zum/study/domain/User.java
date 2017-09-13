package com.zum.study.domain;

import com.zum.study.type.Level;

/**
 * Created by Joeylee on 2017-09-01.
 */

public class User {

    private String id;
    private String name;
    private String password;

    private Level level;
    private int login;
    private int recommend;

    public User() {

    }

    public User(String id, String name, String password, Level level, int login, int recommend) {

        setId(id);
        setName(name);
        setPassword(password);
        setLevel(level);
        setLogin(login);
        setRecommend(recommend);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public void upgradeLevel() {

        Level next = level.next();

        if (next == null) {
            throw new IllegalStateException("Cannot upgrade : " + level);
        }

        this.level = next;
    }
}
