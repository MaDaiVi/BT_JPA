package vn.mdv.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.mdv.services.UserService;
import vn.mdv.services.UserServiceImpl;

@WebServlet("/resend-otp")
public class ResendOTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("otpUserId") == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        int userId = (Integer) session.getAttribute("otpUserId");

        boolean result = userService.resendActivationOTP(userId);

        if (result) {
            request.setAttribute("alert", "Đã gửi mã OTP mới đến email của bạn!");
        } else {
            request.setAttribute("alert", "Gửi OTP thất bại! Vui lòng thử lại.");
        }

        request.getRequestDispatcher("/views/verify-otp.jsp").forward(request, response);
    }
}