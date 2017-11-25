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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.zum.study")
@PropertySource("classpath:/property/database.properties")
@Import({ SqlServiceConfiguration.class })
public class SpringPracticeConfiguration {

    @Value("${db.driverClass}")
    private Class<? extends Driver> driverClass;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Configuration
    @Profile("test")
    public class SpringPracticeTestConfiguration {

        @Bean
        public UserService testUserService() {
            return new TestUserServiceImpl();
        }

        @Bean
        public MailSender mailSender() {
            return new TestMailSender();
        }

        @Bean
        public MessageFactoryBean message() {

            MessageFactoryBean bean = new MessageFactoryBean();
            bean.setText("youngwoo");

            return bean;
        }
    }

    @Configuration
    @Profile("production")
    public class SpringPracticeProductionConfiguration {

        @Bean
        public MailSender mailSender() {

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("localhost");

            return mailSender;
        }
    }



    @Bean
    public DataSource dataSource() {

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);


        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());

        return transactionManager;
    }


    @Bean
    public SqlService sqlService() {

        return new OxmSqlService();

    }

}
