package gui.weng.miaosha.controller.goods;

import gui.weng.miaosha.domain.GoodsVo;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.services.goods.IGoodsService;
import gui.weng.miaosha.services.miaoshauser.IMiaoShaUserService;
import gui.weng.miaosha.services.miaoshauser.MiaoShaUserServiceImpl;
import gui.weng.miaosha.util.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private IMiaoShaUserService  userService;
	
	@Autowired
	private RedisService redisService;

	@Autowired
	private IGoodsService goodsService;

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

    	// 查询商品列表
		List<GoodsVo> goodsList =  goodsService.listGoodsVo();
		model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId){
		model.addAttribute("user",user);
		GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
		model.addAttribute("goods",goodsVo);

		long startAt = goodsVo.getStartDate().getTime();
 		long endAt = goodsVo.getEndDate().getTime();
 		long now = System.currentTimeMillis();

 		int miaoshaStatus = 0;
		int remainSeconds = 0;

 		if(now < startAt){ // 秒杀还没开始，倒计时
			miaoshaStatus = 0;
			remainSeconds=(int)((startAt-now)/1000);
		}else if( now > endAt){ // 秒杀结束
			miaoshaStatus=2;
			remainSeconds = -1;
		}else{	// 活动进行中
 			miaoshaStatus =1;
 			miaoshaStatus=0;
		}
		model.addAttribute("miaoshaStatus",miaoshaStatus);
		model.addAttribute("remainSeconds",remainSeconds);
		return "goods_detail";
	}
    
}
