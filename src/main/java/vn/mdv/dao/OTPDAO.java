package vn.mdv.dao;

import vn.mdv.models.OTPVerification;

public interface OTPDAO {

    boolean insert(OTPVerification otp);

    OTPVerification getValidOTP(
        int userId,
        String purpose
    );

    boolean update(OTPVerification otp);
}