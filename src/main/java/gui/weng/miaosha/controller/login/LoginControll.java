package gui.weng.miaosha.controller.login;

import gui.weng.miaosha.result.Result;
import gui.weng.miaosha.services.userservice.IUserService;
import gui.weng.miaosha.services.userservice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginControll {
    @Autowired
    private IUserService userService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(){
        return null;
    }

}
