package gui.weng.miaosha.services.order;

import gui.weng.miaosha.domain.GoodsVo;
import gui.weng.miaosha.domain.MiaoshaOrder;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.domain.OrderInfo;

public interface IOrderService {

    public MiaoshaOrder getMiaoShaOrderByUserIdGoodsId(long userId,long goodsId);

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long id, long goodsId);

    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods);
}
