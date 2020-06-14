package io.github.donggi.reminder.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.donggi.reminder.annotation.NoAuthAction;

@Controller
public class IndexAction {

    @NoAuthAction
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index() {
        return "index";
    }

}
