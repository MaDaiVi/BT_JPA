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

@WebServlet("/verify-otp")
public class VerifyOTPController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();

    // Hiển thị trang nhập OTP
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("otpUserId") == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        request.getRequestDispatcher("/views/verify-otp.jsp").forward(request, response);
    }

    // Xử lý OTP người dùng nhập
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("otpUserId") == null) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        int userId = (Integer) session.getAttribute("otpUserId");
        String otpCode = request.getParameter("otp");

        boolean result = userService.activateAccount(userId, otpCode);

        if (result) {

            // Xác nhận thành công, xóa ID chờ xác thực
            session.removeAttribute("otpUserId");

            request.setAttribute("alert", "Kích hoạt tài khoản thành công! Vui lòng đăng nhập.");

            request.getRequestDispatcher("/views/login.jsp").forward(request, response);

        } else {

            request.setAttribute("alert", "Mã OTP không chính xác hoặc đã hết hạn.");

            request.getRequestDispatcher("/views/verify-otp.jsp").forward(request, response);
        }
    }
}