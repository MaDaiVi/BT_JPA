package vn.mdv.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.mdv.models.User;
import vn.mdv.services.UserService;
import vn.mdv.services.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("account");

        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/waiting");
            return;
        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("username")) {

                    user = userService.get(cookie.getValue());

                    if (user != null) {
                        session.setAttribute("account", user);

                        response.sendRedirect(
                            request.getContextPath() + "/waiting"
                        );
                        return;
                    }
                }
            }
        }

        request.getRequestDispatcher("/views/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String remember = request.getParameter("remember");

        if (userName == null || userName.trim().isEmpty()
                || passWord == null || passWord.trim().isEmpty()) {

            request.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/views/login.jsp")
                   .forward(request, response);
            return;
        }

        User user = userService.login(userName, passWord);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("account", user);

            if ("on".equals(remember)) {
                Cookie cookie = new Cookie("username", user.getUserName());

                cookie.setMaxAge(3 * 60);
                cookie.setPath(request.getContextPath());
                cookie.setHttpOnly(true);

                response.addCookie(cookie);

                System.out.println("Đã tạo Cookie: " + cookie.getName());
            }

            response.sendRedirect(request.getContextPath() + "/waiting");

        } else {

            request.setAttribute("alert", "Tên đăng nhập hoặc mật khẩu không đúng!");

            request.getRequestDispatcher("/views/login.jsp")
                   .forward(request, response);
        }
    }
}