package gui.weng.miaosha.services;

import gui.weng.miaosha.dao.GoodsDao;
import gui.weng.miaosha.domain.Goods;
import gui.weng.miaosha.domain.GoodsVo;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.domain.OrderInfo;
import gui.weng.miaosha.services.goods.IGoodsService;
import gui.weng.miaosha.services.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoShaService {

    @Autowired
    public IGoodsService goodsService;

    @Autowired
    public IOrderService orderService;

//    @Autowired
//    private GoodsDao goodsDao;
//
//    @Transactional
//    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
//        //减库存 下订单 写入秒杀订单
//        Goods g = new Goods();
//        g.setId(goods.getId());
//        g.setGoodsStock(goods.getStockCount()-1);
//        goodsDao.reduceStock(g);
//    }
//    Service一般只调用自己的Dao，不调用其他Dao。若要调用其他Dao的话，改为调取其Service
//    如上，不调用GoodsDao，而是改为调用GoodsService


    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        //order_info maiosha_order
        return orderService.createOrder(user, goods);
    }


}
