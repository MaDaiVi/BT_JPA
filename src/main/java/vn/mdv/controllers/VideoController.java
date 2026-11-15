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
import vn.mdv.models.Category;

import vn.mdv.services.VideoService;
import vn.mdv.services.VideoServiceImpl;
import vn.mdv.services.CategoryService;
import vn.mdv.services.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/video", "/video/add", "/video/edit", "/video/delete" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VideoService videoService = new VideoServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Kiểm tra quyền Admin
		if (!isAdmin(request)) {
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("account") == null) {
				response.sendRedirect(request.getContextPath() + "/login");
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}
			return;
		}

		// Kiểm tra đường dẫn được truy cập
		String path = request.getServletPath();

		if (path.equals("/video/add")) {

			List<Category> categories = categoryService.findAll();
			request.setAttribute("categories", categories);

			request.getRequestDispatcher("/views/video-add.jsp").forward(request, response);

		} else if (path.equals("/video/edit")) {

			String videoId = request.getParameter("videoId");

			Video video = videoService.findById(videoId);

			if (video == null) {
				response.sendRedirect(request.getContextPath() + "/video");
				return;
			}

			List<Category> categories = categoryService.findAll();

			request.setAttribute("video", video);
			request.setAttribute("categories", categories);

			request.getRequestDispatcher("/views/video-edit.jsp").forward(request, response);
		} else if (path.equals("/video/delete")) {

		    String videoId = request.getParameter("videoId");

		    if (videoId != null && !videoId.trim().isEmpty()) {
		        videoService.delete(videoId);
		    }

		    response.sendRedirect(request.getContextPath() + "/video");
		    return;

		} else {

			String keyword = request.getParameter("keyword");

			List<Video> videos;

			if (keyword == null || keyword.trim().isEmpty()) {
				videos = videoService.findAll();
			} else {
				videos = videoService.findByTitle(keyword.trim());
			}

			request.setAttribute("keyword", keyword);
			request.setAttribute("videos", videos);

			request.getRequestDispatcher("/views/video-list.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Kiểm tra quyền Admin
		if (!isAdmin(request)) {
		    HttpSession session = request.getSession(false);

		    if (session == null || session.getAttribute("account") == null) {
		        response.sendRedirect(request.getContextPath() + "/login");
		    } else {
		        response.sendRedirect(request.getContextPath() + "/home");
		    }

		    return;
		}

		request.setCharacterEncoding("UTF-8");

		String path = request.getServletPath();

		String videoId = request.getParameter("videoId");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String poster = request.getParameter("poster");
		String cateId = request.getParameter("cateId");

		try {
			if (videoId == null || videoId.trim().isEmpty() || title == null || title.trim().isEmpty() || cateId == null
					|| cateId.trim().isEmpty()) {

				request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
				request.setAttribute("categories", categoryService.findAll());

				String page = path.equals("/video/edit") ? "/views/video-edit.jsp" : "/views/video-add.jsp";

				request.getRequestDispatcher(page).forward(request, response);
				return;
			}

			Category category = categoryService.findById(Integer.parseInt(cateId));

			if (category == null) {
				request.setAttribute("error", "Danh mục không tồn tại!");
				request.setAttribute("categories", categoryService.findAll());

				String page = path.equals("/video/edit") ? "/views/video-edit.jsp" : "/views/video-add.jsp";

				request.getRequestDispatcher(page).forward(request, response);
				return;
			}

			if (path.equals("/video/add")) {

				// Thêm video
				Video video = new Video();

				video.setVideoId(videoId.trim());
				video.setTitle(title.trim());
				video.setDescription(description);
				video.setPoster(poster);
				video.setCategory(category);
				video.setActive(1);
				video.setViews(0);

				videoService.insert(video);

			} else if (path.equals("/video/edit")) {

				// Tìm video cần sửa
				Video video = videoService.findById(videoId.trim());

				if (video == null) {
					response.sendRedirect(request.getContextPath() + "/video");
					return;
				}

				// Cập nhật thông tin
				video.setTitle(title.trim());
				video.setDescription(description);
				video.setPoster(poster);
				video.setCategory(category);

				String active = request.getParameter("active");

				if (active != null) {
					video.setActive(Integer.parseInt(active));
				}

				videoService.update(video);
			}

			response.sendRedirect(request.getContextPath() + "/video");

		} catch (NumberFormatException e) {
			request.setAttribute("error", "Dữ liệu không hợp lệ!");
			request.setAttribute("categories", categoryService.findAll());

			String page = path.equals("/video/edit") ? "/views/video-edit.jsp" : "/views/video-add.jsp";

			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	private boolean isAdmin(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("account") == null) {
			return false;
		}

		User user = (User) session.getAttribute("account");

		return user.getRoleid() == 1;
	}
}