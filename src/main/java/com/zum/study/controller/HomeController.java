package com.zum.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

/**
 * Created by Joeylee on 2017-07-22.
 */
@Controller
public class HomeController {



    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET} )
    @ResponseBody
    public String hello(@RequestParam("id") int id) {
        int i= 1;

        Properties prop = new Properties();
        try {

        prop.load(HomeController.class.getClassLoader().getResourceAsStream("data.properties"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String name = prop.getProperty("sample.name");
        return String.format(id+ "- hello" + name);
    }

}
