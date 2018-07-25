package gui.weng.miaosha.controller.login;

import gui.weng.miaosha.domain.LoginVo;
import gui.weng.miaosha.result.CodeMsg;
import gui.weng.miaosha.result.Result;
import gui.weng.miaosha.services.miaoshauser.IMiaoShaUserService;
import gui.weng.miaosha.util.validator.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginControll {

    private static Logger log = LoggerFactory.getLogger(LoginControll.class);

    @Autowired
    private IMiaoShaUserService miaoShaUserService;

//    @Autowired
//    private IUserService userService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

//    @RequestMapping("/do_login")
//    @ResponseBody
//    public Result<Boolean> doLogin(LoginVo loginVo){
//        log.info(loginVo.toString());
//        String passInput = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if(passInput.isEmpty()){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(mobile.isEmpty()){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return Result.error((CodeMsg.MOBILE_ERROR));
//        }
//        // 登录
//
//        CodeMsg loginMsg = miaoShaUserService.login(loginVo);
//        if(loginMsg.getCode() == 0){
//            return Result.success(true);
//        }else{
//            return Result.error(loginMsg);
//        }
//    }

    // 使用 spring-boot-starter-validation 简化验证过程
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response,@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        String token = miaoShaUserService.login(response,loginVo);
        return Result.success(token);
    }

}
