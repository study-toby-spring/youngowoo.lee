package com.zum.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Joeylee on 2017-07-22.
 */
@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET} )
    @ResponseBody
    public String hello(@RequestParam("hi") String message) {
        int i= 1;
        logger.info("Hi : {}", message);
        return String.format(message + "- hello");
    }

}
