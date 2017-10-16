package com.zum.study.service;

import com.zum.study.domain.User;
import com.zum.study.repository.UserDao;
import com.zum.study.type.Level;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * Created by Joeylee on 2017-09-13.
 */
public interface UserService {
    void add(User user);
    void upgradeLevels();

}
