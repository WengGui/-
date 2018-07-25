package gui.weng.miaosha.services.order;

import gui.weng.miaosha.dao.OrderDao;
import gui.weng.miaosha.domain.GoodsVo;
import gui.weng.miaosha.domain.MiaoshaOrder;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public MiaoshaOrder getMiaoShaOrderByUserIdGoodsId(long userId, long goodsId) {
        return null;
    }

    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    @Override
    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
