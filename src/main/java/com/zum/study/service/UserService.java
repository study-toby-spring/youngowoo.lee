package com.zum.study.service;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import com.zum.study.type.Level;

import java.util.List;

/**
 * Created by Joeylee on 2017-09-13.
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {

        if (user.getLevel() == null)
            user.setLevel(Level.BASIC);

        userDao.add(user);
    }

    public void upgradeLevels() {

        List<User> users = userDao.getAll();

        for (User user : users) {

            boolean changed = false;
            Level level = user.getLevel();

            if (level == Level.BASIC && user.getLogin() >= 50) {

                user.setLevel(Level.SILVER);
                changed = true;
            }
            else if (level == Level.SILVER && user.getRecommend() >= 30) {

                user.setLevel(Level.GOLD);
                changed = true;
            }
            else if (level == Level.GOLD) {

                changed = false;
            }

            if (changed) {

                userDao.update(user);
            }
        }
    }
}