package io.cloudtype.mymemory.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class PageController {
    @RequestMapping(value = "/users/login", method= RequestMethod.GET)
    public String gologin(Model model) {
        return "content/login";
    }

    @RequestMapping(value = "/home", method= RequestMethod.GET)
    public String goHome(Model model) {
        return "content/home";
    }

    @RequestMapping(value = "/users/write", method= RequestMethod.GET)
    public String goWrite(Model model) {
        return "content/write";
    }
}
