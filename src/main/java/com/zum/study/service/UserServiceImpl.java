package com.zum.study.service;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import com.zum.study.type.Level;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

/**
 * Created by Joeylee on 2017-10-17.
 */
public class UserServiceImpl implements UserService{

    public static final int MIN_LOG_COUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;


    private UserDao userDao;
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {

        if (user.getLevel() == null)
            user.setLevel(Level.BASIC);

        userDao.add(user);
    }
    public User get(String id) {
        return userDao.get(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void upgradeLevels() {

        List<User> users = userDao.getAll();

        for (User user : users) {

            if (canUpgradeLevel(user))
                upgradeLevel(user);
        }

    }

    protected void upgradeLevel(User user) {

        user.upgradeLevel();
        userDao.update(user);
        sendUpgradeMail(user);
    }

    private void sendUpgradeMail(User user) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setFrom("admin@ksug.org");
        message.setSubject("Upgrade 안내");
        message.setText("사용자님의 등급이 " + user.getLevel().name() + " 로 업그레이드 되었습니다.");

        mailSender.send(message);
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
