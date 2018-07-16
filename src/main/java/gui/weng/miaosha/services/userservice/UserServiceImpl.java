package gui.weng.miaosha.services.userservice;

import gui.weng.miaosha.dao.UserDao;
import gui.weng.miaosha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;

    public User getById(int id){
        return userDao.getById(id);
    }

    @Transactional
    public boolean testInsert() {
        User user1 = new User(2, "张三");
        User user2 = new User(1, "李四");

        userDao.insertUser(user1);
        userDao.insertUser(user2);
        return true;
    }
}
