package com.zum.study.configuration;

import com.mysql.jdbc.Driver;
import com.zum.study.factory.MessageFactoryBean;
import com.zum.study.repository.UserDao;
import com.zum.study.repository.UserDaoJdbc;
import com.zum.study.service.TestUserServiceImpl;
import com.zum.study.service.UserService;
import com.zum.study.service.UserServiceImpl;
import com.zum.study.service.sql.registry.EmbeddedDatabaseSqlRegistry;
import com.zum.study.service.sql.registry.SqlRegistry;
import com.zum.study.service.sql.service.OxmSqlService;
import com.zum.study.service.sql.service.SqlService;
import com.zum.study.support.mail.TestMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class SpringPracticeConfiguration {

    @Bean
    public DataSource dataSource() {

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());

        return transactionManager;
    }

    @Bean
    public UserDao userDao() {

        return new UserDaoJdbc();

    }

    @Bean
    public UserService userService() {

        UserServiceImpl userService = new UserServiceImpl();

        userService.setUserDao(userDao());
        userService.setMailSender(mailSender());

        return userService;
    }

    @Bean
    public UserService testUserService() {

        TestUserServiceImpl testUserService = new TestUserServiceImpl();

        testUserService.setUserDao(userDao());
        testUserService.setMailSender(mailSender());

        return testUserService;
    }

    @Bean
    public MailSender mailSender() {
        return new TestMailSender();
    }

    @Bean
    public SqlService sqlService() {

        return new OxmSqlService();

    }

    @Bean
    public Unmarshaller unmarshaller() {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.webtoonscorp.spring.jaxb");

        return marshaller;
    }

    @Bean
    public SqlRegistry sqlRegistry() {

        EmbeddedDatabaseSqlRegistry registry = new EmbeddedDatabaseSqlRegistry();
        registry.setDataSource(embeddedDatabase());

        return registry;
    }

    @Bean
    public EmbeddedDatabase embeddedDatabase() {

        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("sql/hsqldb/ddl.sql")
                .build();
    }

    @Bean
    public MessageFactoryBean message() {

        MessageFactoryBean bean = new MessageFactoryBean();
        bean.setText("dongheon");

        return bean;
    }
}
