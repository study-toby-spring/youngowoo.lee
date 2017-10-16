package com.zum.study.service;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import com.zum.study.type.Level;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/context/application-context.xml")
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlatformTransactionManager manager;

    List<User> users;

    @Before
    public void setup() {

        users = new ArrayList<User>();

        users.add(new User("1", "a", "a_pw","lee@gmail.com", Level.BASIC, 49, 0));
        users.add(new User("2", "b", "b_pw","young@gmail.com", Level.BASIC, 50, 0));
        users.add(new User("3", "c", "c_pw","woo@gmail.com", Level.SILVER, 60, 29));
        users.add(new User("4", "d", "d_pw","joey@gmail.com", Level.SILVER, 60, 30));
        users.add(new User("5", "e", "e_pw","joeylee@gmail.com", Level.GOLD, 100, 100));
    }

    @Test
    public void shouldNotNullWithInjection() {

        assertNotNull(userService);
    }

    @Test
    public void upgradeLevels() throws Exception {

        userDao.deleteAll();

        for (User user : users)
            userDao.add(user);

        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);
    }

    private void checkLevelUpgraded(User user, boolean upgraded) {

        Level fromDao = userDao.get(user.getId()).getLevel();

        if (upgraded) {
            assertThat(fromDao, is(user.getLevel().next()));
        }
        else {
            assertThat(fromDao, is(user.getLevel()));
        }
    }

    @Test
    public void upgradeAllOrNothing() throws Exception {

        User texture = users.get(3);

        TestUserService mock = new TestUserService(texture.getId());

        mock.setUserDao(userDao);
        mock.setMailSender(new TestMailSender());

        UserServiceTx txUserService = new UserServiceTx();

        txUserService.setTransactionManager(manager);
        txUserService.setUserService(mock);

        userDao.deleteAll();

        for (User user : users)
            userDao.add(user);

        try {

            txUserService.upgradeLevels();
            fail("TestUserServiceException expected");
        }
        catch (TestUserServiceException e) {

        }

        checkLevelUpgraded(users.get(1), false);
    }

    @Test
    public void add() {

        userDao.deleteAll();

        User userWithLevel = users.get(0);
        User userWithoutLevel = users.get(1);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        assertEquals(userWithLevel, userDao.get(userWithLevel.getId()));
        assertEquals(userWithoutLevel, userDao.get(userWithoutLevel.getId()));

    }

    private void assertEquals(User user1, User user2) {

        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }
}