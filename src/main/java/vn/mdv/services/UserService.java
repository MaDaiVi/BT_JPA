package vn.mdv.services;

import vn.mdv.models.User;

public interface UserService {

    User login(String userName, String passWord);

    User get(String userName);

    boolean register(User user);
    
    boolean activateAccount(int userId, String otpCode);
    
    boolean resendActivationOTP(int userId);
    
    boolean sendPasswordResetOTP(String email);

    boolean resetPassword(int userId, String otpCode, String newPassword);
    
    User getByEmail(String email);
}