package com.example;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("hello")
public class HelloController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @RequestMapping(method = RequestMethod.GET)
    public void show() {
        LOG.info("Visited the main page");
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateSession(@RequestParam("name") String name, @RequestParam("value") String value, HttpSession session) {
        LOG.info("Adding attribute `{}` to session with value of `{}`", name, value);
        session.setAttribute(name, value);
    }
}
