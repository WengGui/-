package gui.weng.miaosha.controller.goods;

import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.services.miaoshauser.IMiaoShaUserService;
import gui.weng.miaosha.services.miaoshauser.MiaoShaUserServiceImpl;
import gui.weng.miaosha.util.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private IMiaoShaUserService  userService;
	
	@Autowired
	private RedisService redisService;

//  使用 HandlerMethodArgumentResolver 优化前的代码
//	@RequestMapping("/to_list")
//	public String toLogin(Model model,HttpServletResponse response,
//						  @CookieValue(value = MiaoShaUserServiceImpl.COOKI_NAME_TOKEN,required = false)String cookToken,
//						  @RequestParam(value = MiaoShaUserServiceImpl.COOKI_NAME_TOKEN,required = false)String paramToken) {
//
//		if(StringUtils.isEmpty(cookToken) && StringUtils.isEmpty(paramToken)){
//			return "login";
//		}
//		String token = StringUtils.isEmpty(paramToken) ? cookToken : paramToken;
//		MiaoshaUser user = userService.getByToken(response, token);
//
//		model.addAttribute("user", user);
//		return "goods_list";
//	}

	//  使用 HandlerMethodArgumentResolver 优化后的代码
    @RequestMapping("/to_list")
    public String list(Model model,MiaoshaUser user) {
    	model.addAttribute("user", user);
        return "goods_list";
    }
    
}
