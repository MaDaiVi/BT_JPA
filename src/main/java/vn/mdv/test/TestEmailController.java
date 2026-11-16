package vn.mdv.test;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.mdv.services.EmailService;

@WebServlet("/test-email")
public class TestEmailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            EmailService.sendEmail(
                "ngocdeptraibodoivodich@gmail.com",
                "Test Email BT_JPA",
                "Xin chào! Email gửi từ dự án BT_JPA thành công."
            );

            response.getWriter().println("Gửi email thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Gửi email thất bại: " + e.getMessage());
        }
    }
}