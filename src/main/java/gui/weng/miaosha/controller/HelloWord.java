package gui.weng.miaosha.controller;

import gui.weng.miaosha.domain.User;
import gui.weng.miaosha.result.CodeMsg;
import gui.weng.miaosha.result.Result;
import gui.weng.miaosha.services.userservice.IUserService;
import gui.weng.miaosha.util.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWord {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisService redisService;

//    @Autowired
//    private RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    public String test(){
        return "HelloWord";
    }

    @RequestMapping("/success")
    @ResponseBody
    public Result<String> Success(){
       return Result.success("Success");
    }

    @RequestMapping("/fail")
    @ResponseBody
    public Result<String> Fail(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","Weng");
        return "Hello";
    }

    @RequestMapping("/ok")
    @ResponseBody
    public String ok(Model model){
        model.addAttribute("name","Weng");
        return "Hello";
    }

    @RequestMapping(value = "/sayhello", method = RequestMethod.GET)
    @ResponseBody
    public String sayhello(){
        return "Hello SpringBoot !";
    }

    @RequestMapping(value = "/getDB")
    @ResponseBody
    public Result<User> TestDB(){
        User user = userService.getById(1);
       return Result.success(user);
    }

    @RequestMapping(value = "/testTransactional")
    @ResponseBody
    public Result<String> testTransactional(){
        userService.testInsert();
        return  Result.success("测试事务");
    }

    @RequestMapping(value = "/redis/get")
    @ResponseBody
    public Result<Long> redisGet(){
        Long v1 = redisService.get("key1", Long.class);
        return  Result.success(v1);
    }
}

