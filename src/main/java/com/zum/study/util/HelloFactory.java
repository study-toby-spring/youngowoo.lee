package com.zum.study.util;

import com.zum.study.domain.Hello;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by Joeylee on 2017-07-29.
 */
@Data
//@Component
public class HelloFactory {


//    @Resource`
//    @Autowired
//    @Qualifier("idGenerator")
    private Random random;

//    @PostConstruct
//    public void initialize() {
//        this.random = new Random();
//    }

    public Hello createInstance() {
        return new Hello(random.nextInt(100),"Hello");
    }

}
