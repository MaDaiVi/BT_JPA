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

import vn.mdv.models.User;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	// Hiển thị trang quên mật khẩu
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
	}

	// Xử lý gửi OTP
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");

		boolean result = userService.sendPasswordResetOTP(email);

		if (result) {

			HttpSession session = request.getSession();

			User user = userService.getByEmail(email);

			if (user != null) {
				session.setAttribute("resetPasswordUserId", user.getId());
				response.sendRedirect(request.getContextPath() + "/reset-password");
				return;
			}

		} else {

			request.setAttribute("alert", "Email không tồn tại hoặc tài khoản chưa được kích hoạt!");

			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
		}
	}
}