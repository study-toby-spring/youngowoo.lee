package com.zum.study.service;

import com.zum.study.domain.User;

/**
 * Created by Joeylee on 2017-09-13.
 */
public class TestUserService extends UserService {

    private String id;

    public TestUserService(String id) {
        this.id = id;
    }

    @Override
    protected void upgradeLevel(User user) {

        if (user.getId().equals(id))
            throw new TestUserServiceException();

        super.upgradeLevel(user);
    }
}
