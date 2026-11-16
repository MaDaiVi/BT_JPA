package vn.mdv.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.mdv.models.User;
import vn.mdv.models.Video;
import vn.mdv.services.VideoService;
import vn.mdv.services.VideoServiceImpl;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("account");

        request.setAttribute("user", user);

        // Lấy 10 video mới nhất
        List<Video> latestVideos = videoService.findTop10Latest();

        request.setAttribute("latestVideos", latestVideos);

        request.getRequestDispatcher("/views/home.jsp")
               .forward(request, response);
    }
}