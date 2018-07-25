package gui.weng.miaosha.services.miaoshauser;

import gui.weng.miaosha.domain.LoginVo;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.result.CodeMsg;

import javax.servlet.http.HttpServletResponse;

public interface IMiaoShaUserService {

    public MiaoshaUser getById(Long id);

    public String login(HttpServletResponse response,LoginVo loginVo);

    public MiaoshaUser getByToken(HttpServletResponse response, String token);
}
