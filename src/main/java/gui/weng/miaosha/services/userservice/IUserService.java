package gui.weng.miaosha.services.userservice;

import gui.weng.miaosha.domain.User;

public interface IUserService {
    public User getById(int id);

    public boolean testInsert();
}
