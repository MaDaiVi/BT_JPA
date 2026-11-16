package vn.mdv.services;

import org.mindrot.jbcrypt.BCrypt;

import vn.mdv.dao.UserDAO;
import vn.mdv.dao.UserDAOImpl;
import vn.mdv.models.User;

public class UserServiceImpl implements UserService {

    private UserDAO userDao = new UserDAOImpl();
    private OTPService otpService = new OTPService();

    @Override
    public User get(String userName) {
        return userDao.get(userName);
    }

    @Override
    public User login(String userName, String passWord) {

        User user = userDao.get(userName);

        if (user == null || !user.isActive()) {
            return null;
        }

        if (BCrypt.checkpw(passWord, user.getPassWord())) {
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

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()
                || user.getEmail().length() > 255) {
            return false;
        }

        if (user.getFullName() != null && user.getFullName().length() > 100) {
            return false;
        }

        if (user.getPhone() != null && user.getPhone().length() > 20) {
            return false;
        }

        // 1. Mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(
                user.getPassWord(),
                BCrypt.gensalt(12)
        );

        user.setPassWord(hashedPassword);

        // 2. Tài khoản chưa được kích hoạt
        user.setActive(false);

        // 3. Lưu tài khoản vào database
        boolean result = userDao.register(user);

        if (!result) {
            return false;
        }

        // 4. Gửi OTP kích hoạt qua email
        return otpService.sendOTP(user, "ACTIVATION");
    }
    
    @Override
    public boolean activateAccount(int userId, String otpCode) {

        // 1. Kiểm tra OTP
        boolean valid = otpService.verifyOTP(
                userId,
                otpCode,
                "ACTIVATION"
        );

        if (!valid) {
            return false;
        }

        // 2. OTP hợp lệ thì kích hoạt tài khoản
        return userDao.activate(userId);
    }
    
    @Override
    public boolean resendActivationOTP(int userId) {

        // Tìm tài khoản theo ID
        User user = userDao.getById(userId);

        if (user == null || user.isActive()) {
            return false;
        }

        // Gửi OTP kích hoạt mới
        return otpService.sendOTP(user, "ACTIVATION");
    }
    
    @Override
    public boolean sendPasswordResetOTP(String email) {

        // 1. Tìm tài khoản bằng email
        User user = userDao.getByEmail(email);

        // 2. Kiểm tra tài khoản
        if (user == null || !user.isActive()) {
            return false;
        }

        // 3. Gửi OTP đặt lại mật khẩu
        return otpService.sendOTP(user, "RESET_PASSWORD");
    }
    
    @Override
    public boolean resetPassword(int userId, String otpCode, String newPassword) {

        // 1. Kiểm tra mật khẩu mới
        if (newPassword == null || newPassword.trim().isEmpty()
                || newPassword.length() > 72) {
            return false;
        }

        // 2. Tìm tài khoản
        User user = userDao.getById(userId);

        if (user == null || !user.isActive()) {
            return false;
        }

        // 3. Kiểm tra OTP
        boolean valid = otpService.verifyOTP(
                userId,
                otpCode,
                "RESET_PASSWORD"
        );

        if (!valid) {
            return false;
        }

        // 4. Mã hóa mật khẩu mới
        String hashedPassword = BCrypt.hashpw(
                newPassword,
                BCrypt.gensalt(12)
        );

        // 5. Cập nhật mật khẩu vào database
        return userDao.updatePassword(userId, hashedPassword);
    }
    
    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}