package gui.weng.miaosha.controller.miaosha;

import gui.weng.miaosha.domain.GoodsVo;
import gui.weng.miaosha.domain.MiaoshaOrder;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.domain.OrderInfo;
import gui.weng.miaosha.result.CodeMsg;
import gui.weng.miaosha.services.MiaoShaService;
import gui.weng.miaosha.services.goods.IGoodsService;
import gui.weng.miaosha.services.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/miaosh")
public class MiaoShaController {
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private MiaoShaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String list(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if(user == null){
            return "login";
        }
        // 判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        Integer stockCount = goodsVo.getStockCount();
        if(stockCount<=0){
            model.addAttribute("errmsg", CodeMsg.MIAO_OVER.getMsg());
            return "miaosha_fail";
        }
        // 判断是否已经秒杀过，避免一人秒杀多个
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        // 减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goodsVo);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goodsVo);

        return "goods_list";
    }
}
