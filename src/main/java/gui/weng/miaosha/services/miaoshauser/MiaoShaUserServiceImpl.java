package gui.weng.miaosha.services.miaoshauser;

import com.sun.org.apache.bcel.internal.classfile.Code;
import gui.weng.miaosha.dao.MiaoShaUserDao;
import gui.weng.miaosha.domain.LoginVo;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.exception.GobalException;
import gui.weng.miaosha.result.CodeMsg;
import gui.weng.miaosha.util.md5.MD5Util;
import gui.weng.miaosha.util.prefixkey.impl.MiaoshaUserKey;
import gui.weng.miaosha.util.redis.RedisService;
import gui.weng.miaosha.util.uuid.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoShaUserServiceImpl implements  IMiaoShaUserService{

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private MiaoShaUserDao miaoShaUserDao;

    @Autowired
    private RedisService redisService;

    @Override
    public MiaoshaUser getById(Long id) {
        return miaoShaUserDao.getById(id);
    }

    @Override
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null)
            throw  new GobalException(CodeMsg.SERVER_ERROR);

        String mobile = loginVo.getMobile();
        String pass = loginVo.getPassword();
        // 手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null)
            throw  new GobalException(CodeMsg.MOBILE_NOT_EXIST);

        // 验证密码
        String dbPass = user.getPassword();
        String dbSlat = user.getSalt();
        String calcPass1 = MD5Util.fromPass2DBPass(pass, dbSlat);
        if(!calcPass1.equals(dbPass))
            throw  new GobalException(CodeMsg.PASSWORD_ERROR);

        // 生成token（uuid）
        addCookie(response, user);
        return true;
    }




    @Override
    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token,token,MiaoshaUser.class);
        if(user !=null){
            // 延长有效性
            addCookie(response,user);
        }
        return user;
    }


    private void addCookie(HttpServletResponse response, MiaoshaUser user) {
        String token = UuidUtil.uuid();
        redisService.set(MiaoshaUserKey.token,token,user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
