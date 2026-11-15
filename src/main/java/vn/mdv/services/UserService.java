package vn.mdv.services;

import vn.mdv.models.User;

public interface UserService {

    User login(String userName, String passWord);

    User get(String userName);

    boolean register(User user);
}