package com.zum.study.service;

import com.zum.study.domain.User;
import com.zum.study.repository.TestUserDao;
import com.zum.study.repository.UserDao;
import com.zum.study.support.mail.TestMailSender;
import com.zum.study.support.transaction.TransactionHandler;
import com.zum.study.type.Level;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.PlatformTransactionManager;

import static org.mockito.Mockito.*;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
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

    @Autowired
    private ApplicationContext context;

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
    public void mockUpgradeLevels() {

        UserDao userDao = mock(UserDao.class);
        when(userDao.getAll()).thenReturn(users);

        MailSender mailSender = mock(MailSender.class);

        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao);
        userService.setMailSender(mailSender);

        userService.upgradeLevels();

        verify(userDao, times(2)).update(any(User.class));
        verify(userDao, times(2)).update(any(User.class));

        verify(userDao).update(users.get(1));
        assertThat(users.get(1).getLevel(), is(Level.SILVER));

        verify(userDao).update(users.get(3));
        assertThat(users.get(3).getLevel(), is(Level.GOLD));


        ArgumentCaptor<SimpleMailMessage> message = ArgumentCaptor.forClass(SimpleMailMessage.class);

        verify(mailSender, times(2)).send(message.capture());

        List<SimpleMailMessage> messages = message.getAllValues();

        assertThat(messages.get(0).getTo()[0], is(users.get(1).getEmail()));
        assertThat(messages.get(1).getTo()[0], is(users.get(3).getEmail()));
    }

    @Test
    public void shouldNotNullWithInjection() {

        assertNotNull(userService);
    }

    @Test
    public void upgradeLevels() throws Exception {

        TestMailSender mailSender = new TestMailSender();
        TestUserDao userDao = new TestUserDao(this.users);

        userService = new UserServiceImpl();

        userService.setUserDao(userDao);
        userService.setMailSender(mailSender);

        userService.upgradeLevels();

        List<User> updated = userDao.getUpdated();

        assertThat(updated.size(), is(2));

        checkUserAndLevel(users.get(0), "a", Level.BASIC);
        checkUserAndLevel(users.get(1), "b", Level.SILVER);

        List<String> requests = mailSender.getRequests();

        assertThat(requests.size(), is(2));
        assertThat(requests.get(0), is(users.get(1).getEmail()));
        assertThat(requests.get(1), is(users.get(3).getEmail()));
    }

    private void checkUserAndLevel(User user, String name, Level level) {

        assertThat(user.getName(), is(name));
        assertThat(user.getLevel(), is(level));
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
    @DirtiesContext
    public void upgradeAllOrNothing() throws Exception {

        User target = users.get(3);

        TestUserService mock = new TestUserService(target.getId());
        TransactionHandler handler = new TransactionHandler();

        handler.setTarget(mock);
        handler.setPattern("upgradeLevels");
        handler.setManager(manager);

        mock.setUserDao(userDao);
        mock.setMailSender(new TestMailSender());

        UserService userService = (UserService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { UserService.class }, handler);

        userDao.deleteAll();

        for (User user : users)
            userDao.add(user);

        try {

            userService.upgradeLevels();
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