package vn.mdv.services;

import vn.mdv.dao.UserDAO;
import vn.mdv.dao.UserDAOImpl;
import vn.mdv.models.User;

public class UserServiceImpl implements UserService {

    private UserDAO userDao = new UserDAOImpl();

    @Override
    public User get(String userName) {
        return userDao.get(userName);
    }

    @Override
    public User login(String userName, String passWord) {

        User user = userDao.get(userName);

        if (user != null && user.getPassWord().equals(passWord)) {
            return user;
        }

        return null;
    }

    @Override
    public boolean register(User user) {

        if (user.getUserName() == null || user.getUserName().trim().isEmpty()
                || user.getPassWord() == null || user.getPassWord().trim().isEmpty()) {
            return false;
        }

        if (user.getUserName().length() > 50
                || user.getPassWord().length() > 100) {
            return false;
        }

        if (user.getEmail() != null && user.getEmail().length() > 255) {
            return false;
        }

        if (user.getFullName() != null && user.getFullName().length() > 100) {
            return false;
        }

        if (user.getPhone() != null && user.getPhone().length() > 20) {
            return false;
        }

        return userDao.register(user);
    }
}