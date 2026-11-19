
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

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Kiểm tra người dùng đã yêu cầu đặt lại mật khẩu chưa
        if (session == null
                || session.getAttribute("resetPasswordUserId") == null) {

            response.sendRedirect(
                request.getContextPath() + "/forgot-password");
            return;
        }

        request.getRequestDispatcher("/views/reset-password.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("resetPasswordUserId") == null) {

            response.sendRedirect(
                request.getContextPath() + "/forgot-password");
            return;
        }

        int userId = (Integer) session.getAttribute("resetPasswordUserId");

        String otpCode = request.getParameter("otpCode");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra xác nhận mật khẩu
        if (newPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("alert", "Mật khẩu xác nhận không khớp!");

            request.getRequestDispatcher("/views/reset-password.jsp")
                   .forward(request, response);
            return;
        }

        boolean result = userService.resetPassword(
            userId, otpCode, newPassword
        );

        if (result) {

            // Xóa thông tin đặt lại mật khẩu khỏi session
            session.removeAttribute("resetPasswordUserId");

            request.setAttribute("alert",
                "Đặt lại mật khẩu thành công! Vui lòng đăng nhập.");

            request.getRequestDispatcher("/views/login.jsp")
                   .forward(request, response);

        } else {

            request.setAttribute("alert",
                "OTP không hợp lệ, đã hết hạn hoặc mật khẩu không hợp lệ!");

            request.getRequestDispatcher("/views/reset-password.jsp")
                   .forward(request, response);
        }
    }
}
