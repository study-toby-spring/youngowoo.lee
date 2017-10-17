package com.zum.study.repository;

import com.zum.study.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joeylee on 2017-10-17.
 */
public class TestUserDao implements UserDao {

    private List<User> users;
    private List<User> updated = new ArrayList<User>();

    public TestUserDao(List<User> users) {
        this.users = users;
    }

    public List<User> getUpdated() {
        return updated;
    }

    public List<User> getAll() {
        return users;
    }

    public void update(User user) {
        updated.add(user);
    }

    public void add(User user) {
        throw new UnsupportedOperationException();
    }

    public User get(String id) {
        throw new UnsupportedOperationException();
    }

    public int getCount() {
        throw new UnsupportedOperationException();
    }

    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
