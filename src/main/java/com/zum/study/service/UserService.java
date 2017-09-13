package com.zum.study.service;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import com.zum.study.type.Level;

import java.util.List;

/**
 * Created by Joeylee on 2017-09-13.
 */
public class UserService {

    public static final int MIN_LOG_COUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

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

            if (canUpgradeLevel(user))
                upgradeLevel(user);
        }
    }

    private void upgradeLevel(User user) {

        user.upgradeLevel();
        userDao.update(user);
    }

    private boolean canUpgradeLevel(User user) {

        Level level = user.getLevel();

        switch (level) {

            case BASIC: return user.getLogin() >= MIN_LOG_COUNT_FOR_SILVER;
            case SILVER: return user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD;
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown level " + level);
        }
    }
}