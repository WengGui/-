package gui.weng.miaosha.services.miaoshauser;

import gui.weng.miaosha.domain.LoginVo;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.result.CodeMsg;

public interface IMiaoShaUserService {

    public MiaoshaUser getById(Long id);

    public boolean login(LoginVo loginVo);
}
