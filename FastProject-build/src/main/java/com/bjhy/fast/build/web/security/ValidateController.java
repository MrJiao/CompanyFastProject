package com.bjhy.fast.build.web.security;


import com.bjhy.fast.build.core.log.LogIgnore;
import com.bjhy.fast.build.security.service.SecurityUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create by: Jackson
 */
@Controller
@LogIgnore
public class ValidateController {

    @Autowired
    SecurityUserInfoService securityUserInfoService;

    @LogIgnore
    @RequestMapping(value = "/login/page",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @LogIgnore
    @RequestMapping(value = "/loginFailure",method = RequestMethod.GET)
    public String loginFailure() {
        return "loginFailure";
    }

    @LogIgnore
    @RequestMapping(value = "/register/page",method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

}
