package vn.mdv.controllers;

import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import jakarta.servlet.http.Part;
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

import jakarta.servlet.annotation.MultipartConfig;

@WebServlet(urlPatterns = { "/video", "/video/add", "/video/edit", "/video/delete", "/video/detail" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024L * 1024 * 500, maxRequestSize = 1024L * 1024 * 510)
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VideoService videoService = new VideoServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();

	private static final String UPLOAD_VIDEO_DIR = "uploads/videos";
	private static final String UPLOAD_POSTER_DIR = "uploads/categories";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// Kiểm tra người dùng đã đăng nhập hay chưa
		User user = null;

		if (session != null) {
			user = (User) session.getAttribute("account");
		}

		String path = request.getServletPath();

		// Chỉ Admin mới được truy cập chức năng quản lý video
		if (path.equals("/video/add") || path.equals("/video/edit") || path.equals("/video/delete")) {

			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}

			if (user.getRoleid() != 1) {
				response.sendRedirect(request.getContextPath() + "/video");
				return;
			}
		}

		// Chỉ Admin được thêm video
		if (path.equals("/video/add")) {

			List<Category> categories = categoryService.findAll();
			request.setAttribute("categories", categories);

			request.getRequestDispatcher("/views/video-add.jsp").forward(request, response);

			// Chỉ Admin được sửa video
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

			// Chỉ Admin được xóa video
		} else if (path.equals("/video/delete")) {
			String videoId = request.getParameter("videoId");

			if (videoId != null && !videoId.trim().isEmpty()) {
				videoService.delete(videoId);
			}

			response.sendRedirect(request.getContextPath() + "/video");
			return;
		} else if (path.equals("/video/detail")) {
			String videoId = request.getParameter("id");

			Video video = videoService.findById(videoId);

			// Tăng lượt xem
			videoService.incrementViews(videoId);

			// Cập nhật lượt xem để hiển thị số mới
			video.setViews(video.getViews() + 1);

			request.setAttribute("video", video);
			request.getRequestDispatcher("/views/video-detail.jsp").forward(request, response);
		} else {
			int page = 1;
			int pageSize = 6;

			try {
				String pageParam = request.getParameter("page");

				if (pageParam != null) {
					page = Integer.parseInt(pageParam);

					if (page < 1) {
						page = 1;
					}
				}
			} catch (NumberFormatException e) {
				page = 1;
			}

			// Kiểm tra vai trò người dùng
			boolean isAdmin = user != null && user.getRoleid() == 1;

			// Đếm tổng số video theo vai trò
			long totalVideos;

			if (isAdmin) {
				totalVideos = videoService.countAll();
			} else {
				totalVideos = videoService.countActive();
			}

			// Tính tổng số trang
			int totalPages = (int) Math.ceil((double) totalVideos / pageSize);

			// Đảm bảo trang hiện tại không vượt quá tổng số trang
			if (totalPages > 0 && page > totalPages) {
				page = totalPages;
			}

			// Lấy danh sách video theo vai trò
			List<Video> videos;

			if (isAdmin) {
				videos = videoService.findAllWithPagination(page, pageSize);
			} else {
				videos = videoService.findActiveWithPagination(page, pageSize);
			}

			// Truyền thông tin sang JSP
			request.setAttribute("user", user);
			request.setAttribute("videos", videos);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPages", totalPages);

			request.getRequestDispatcher("/views/video-list.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// Kiểm tra đăng nhập
		if (session == null || session.getAttribute("account") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User user = (User) session.getAttribute("account");

		String path = request.getServletPath();

		// Chỉ Admin được thêm và sửa video
		if (user.getRoleid() != 1) {
			response.sendRedirect(request.getContextPath() + "/video");
			return;
		}

		request.setCharacterEncoding("UTF-8");

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

				// Nhận file video từ form
				Part videoPart = request.getPart("videoFile");

				// Lưu file vào thư mục uploads/videos
				String videoPath = saveVideo(request, videoPart);

				// Kiểm tra file video
				if (videoPath == null) {
					request.setAttribute("error", "Vui lòng chọn file video!");
					request.setAttribute("categories", categoryService.findAll());

					request.getRequestDispatcher("/views/video-add.jsp").forward(request, response);
					return;
				}

				// Tạo đối tượng Video
				Video video = new Video();
				video.setVideoId(videoId.trim());
				video.setTitle(title.trim());
				video.setDescription(description);
				video.setPoster(poster);
				video.setVideoPath(videoPath);
				video.setCategory(category);
				video.setUser(user);
				video.setActive(1);
				video.setViews(0);
				video.setCreatedDate(new java.sql.Timestamp(System.currentTimeMillis()));

				videoService.insert(video);
			} else if (path.equals("/video/edit")) {

				Video video = videoService.findById(videoId.trim());

				if (video == null) {
					response.sendRedirect(request.getContextPath() + "/video");
					return;
				}

				// Nhận file video mới
				Part videoPart = request.getPart("videoFile");

				// Mặc định giữ video cũ
				String videoPath = video.getVideoPath();

				// Nếu chọn video mới thì lưu video mới
				if (videoPart != null && videoPart.getSize() > 0) {
					videoPath = saveVideo(request, videoPart);
				}

				// Cập nhật thông tin video
				video.setTitle(title.trim());
				video.setDescription(description);
				video.setPoster(poster);
				video.setVideoPath(videoPath);
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

	private String getUploadPath(HttpServletRequest request, String folder) {
		String uploadPath = request.getServletContext().getRealPath("") + File.separator + folder;

		File directory = new File(uploadPath);

		if (!directory.exists()) {
			directory.mkdirs();
		}

		return uploadPath;
	}

	private String saveVideo(HttpServletRequest request, Part filePart) throws IOException {

		// Không có file được chọn
		if (filePart == null || filePart.getSize() == 0) {
			return null;
		}

		// Lấy tên file gốc
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

		// Tạo tên file mới để tránh trùng lặp
		String newFileName = System.currentTimeMillis() + "_" + fileName;

		// Lấy đường dẫn thư mục uploads/videos
		String uploadPath = getUploadPath(request, UPLOAD_VIDEO_DIR);

		// Lưu file vào thư mục
		filePart.write(uploadPath + File.separator + newFileName);

		// Trả về đường dẫn tương đối để lưu vào database
		return UPLOAD_VIDEO_DIR + "/" + newFileName;
	}
}