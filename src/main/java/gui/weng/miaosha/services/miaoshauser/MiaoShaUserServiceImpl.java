package gui.weng.miaosha.services.miaoshauser;

import com.sun.org.apache.bcel.internal.classfile.Code;
import gui.weng.miaosha.dao.MiaoShaUserDao;
import gui.weng.miaosha.domain.LoginVo;
import gui.weng.miaosha.domain.MiaoshaUser;
import gui.weng.miaosha.result.CodeMsg;
import gui.weng.miaosha.util.md5.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaUserServiceImpl implements  IMiaoShaUserService{

    @Autowired
    private MiaoShaUserDao miaoShaUserDao;

    @Override
    public MiaoshaUser getById(Long id) {
        return miaoShaUserDao.getById(id);
    }

    @Override
    public CodeMsg login(LoginVo loginVo) {
        if(loginVo == null)
            return CodeMsg.SERVER_ERROR;

        String mobile = loginVo.getMobile();
        String pass = loginVo.getPassword();
        // 手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null)
            return CodeMsg.MOBILE_NOT_EXIST;

        // 验证密码
        String dbPass = user.getPassword();
        String dbSlat = user.getSalt();
        String calcPass1 = MD5Util.fromPass2DBPass(pass, dbSlat);
        if(!calcPass1.equals(dbPass))
            return CodeMsg.PASSWORD_ERROR;
        return CodeMsg.SUCCESS;
    }
}
