package com.zum.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Joeylee on 2017-07-22.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET} )
    @ResponseBody
    public String hello(@RequestParam("id") int id) {
        int i= 1;
        return String.format(id+ "- hello");
    }

}
