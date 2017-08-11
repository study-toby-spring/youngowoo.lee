package com.zum.study.controller;

import com.zum.study.domain.Hello;
import com.zum.study.util.HelloFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Joeylee on 2017-07-22.
 */
@Controller
public class HomeController {

    //가져오기
    @Autowired
    HelloFactory helloFactory;

    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET} )
    @ResponseBody
    public String hello() {

        Hello instance = helloFactory.createInstance();

        return instance.toString();
    }

}
