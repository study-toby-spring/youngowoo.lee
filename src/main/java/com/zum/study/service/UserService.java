package com.zum.study.service;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import com.zum.study.type.Level;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Joeylee on 2017-09-13.
 */
public class UserService {

    public static final int MIN_LOG_COUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private UserDao userDao;
    private DataSource dataSource;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) {

        if (user.getLevel() == null)
            user.setLevel(Level.BASIC);

        userDao.add(user);
    }

    public void upgradeLevels() throws Exception {

        TransactionSynchronizationManager.initSynchronization();

        Connection connection = DataSourceUtils.getConnection(dataSource);
        connection.setAutoCommit(false);

        try {

            List<User> users = userDao.getAll();

            for (User user : users) {

                if (canUpgradeLevel(user))
                    upgradeLevel(user);
            }

            connection.commit();
        }
        catch (Exception e) {

            connection.rollback();
            throw e;
        }
        finally {

            DataSourceUtils.releaseConnection(connection, dataSource);

            TransactionSynchronizationManager.unbindResource(dataSource);
            TransactionSynchronizationManager.clearSynchronization();
        }
    }

    protected void upgradeLevel(User user) {

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
