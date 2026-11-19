package vn.mdv.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;

import vn.mdv.dao.OTPDAO;
import vn.mdv.dao.OTPDAOImpl;
import vn.mdv.models.OTPVerification;
import vn.mdv.models.User;

public class OTPService {

    private OTPDAO otpDAO = new OTPDAOImpl();

    private SecureRandom random = new SecureRandom();

    // Sinh mã OTP gồm 6 chữ số
    private String generateOTP() {
        return String.format("%06d", random.nextInt(1000000));
    }

    // Gửi OTP đến email
    public boolean sendOTP(User user, String purpose) {

        if (user == null || user.getEmail() == null
                || user.getEmail().isBlank()) {
            return false;
        }

        if (!purpose.equals("ACTIVATION")
                && !purpose.equals("RESET_PASSWORD")) {
            return false;
        }

        String otpCode = generateOTP();

        // Hash OTP bằng BCrypt
        String otpHash = BCrypt.hashpw(
            otpCode,
            BCrypt.gensalt(12)
        );

        // Tạo đối tượng OTP
        OTPVerification otp = new OTPVerification();

        otp.setUser(user);
        otp.setOtpHash(otpHash);
        otp.setPurpose(purpose);
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otp.setUsed(false);

        // Lưu OTP vào database
        boolean saved = otpDAO.insert(otp);

        if (!saved) {
            return false;
        }

        try {

            String subject = "Ma xac nhan OTP - BT_JPA";

            String content =
                "Xin chao " + user.getUserName() + "!\n\n"
                + "Ma OTP cua ban la: " + otpCode + "\n\n"
                + "Ma OTP co hieu luc trong 5 phut.\n"
                + "Vui long khong chia se ma nay cho nguoi khac.";

            // Gửi email
            EmailService.sendEmail(
                user.getEmail(),
                subject,
                content
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            // Email gửi thất bại, vô hiệu hóa OTP vừa tạo
            otp.setUsed(true);
            otpDAO.update(otp);

            return false;
        }
    }

    // Xác thực OTP
    public boolean verifyOTP(
            int userId,
            String otpCode,
            String purpose) {

        if (otpCode == null || otpCode.isBlank()) {
            return false;
        }

        OTPVerification otp = otpDAO.getValidOTP(userId, purpose);

        if (otp == null) {
            return false;
        }

        // Kiểm tra OTP nhập vào với OTP đã hash
        if (!BCrypt.checkpw(otpCode, otp.getOtpHash())) {
            return false;
        }

        // Đánh dấu OTP đã được sử dụng
        otp.setUsed(true);

        return otpDAO.update(otp);
    }
}