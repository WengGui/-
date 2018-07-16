package gui.weng.miaosha.dao;

import gui.weng.miaosha.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public User getById(int id);

    public boolean insertUser(User user);
}
