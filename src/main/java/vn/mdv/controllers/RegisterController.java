package vn.mdv.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.mdv.models.User;
import vn.mdv.services.UserService;
import vn.mdv.services.UserServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setEmail(email);
        user.setFullName(fullname);
        user.setPhone(phone);

        boolean result = userService.register(user);

        if (result) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("alert",
                    "Đăng ký thất bại! Tên đăng nhập có thể đã tồn tại hoặc dữ liệu không hợp lệ.");

            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}