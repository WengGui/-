package gui.weng.miaosha.services.goods;

import gui.weng.miaosha.domain.Goods;
import gui.weng.miaosha.domain.GoodsVo;

import java.util.List;

public interface IGoodsService {

    public List<GoodsVo> listGoodsVo();

    public GoodsVo getGoodsVoByGoodsId(long goodsId);
}
