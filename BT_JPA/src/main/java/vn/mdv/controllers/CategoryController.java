package vn.mdv.controllers;

import jakarta.servlet.http.HttpSession;
import vn.mdv.models.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.mdv.models.Category;
import vn.mdv.services.CategoryService;
import vn.mdv.services.CategoryServiceImpl;
import vn.mdv.util.Constant;

import vn.mdv.models.Video;
import vn.mdv.services.VideoService;
import vn.mdv.services.VideoServiceImpl;

@WebServlet(urlPatterns = { "/category", "/category/add", "/category/edit", "/category/delete", "/category/videos" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 25)
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CategoryService categoryService = new CategoryServiceImpl();

	private VideoService videoService = new VideoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!isAdmin(request)) {
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("account") == null) {
				response.sendRedirect(request.getContextPath() + "/login");
			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}

			return;
		}

		String path = request.getServletPath();

		switch (path) {
		case "/category":
			String keyword = request.getParameter("keyword");

			List<Category> categories = categoryService.search(keyword);

			request.setAttribute("categories", categories);
			request.setAttribute("keyword", keyword);

			request.getRequestDispatcher("/views/category-list.jsp").forward(request, response);
			break;

		case "/category/add":
			request.getRequestDispatcher("/views/category-add.jsp").forward(request, response);
			break;

		case "/category/edit":
			int editId = Integer.parseInt(request.getParameter("id"));
			Category editCategory = categoryService.findById(editId);

			if (editCategory == null) {
				response.sendRedirect(request.getContextPath() + "/category");
				return;
			}

			request.setAttribute("category", editCategory);
			request.getRequestDispatcher("/views/category-edit.jsp").forward(request, response);
			break;

		case "/category/delete":
			int deleteId = Integer.parseInt(request.getParameter("id"));
			categoryService.delete(deleteId);
			response.sendRedirect(request.getContextPath() + "/category");
			break;
		case "/category/videos":

			int cateId = Integer.parseInt(request.getParameter("id"));

			// Tìm danh mục
			Category category = categoryService.findById(cateId);

			if (category == null) {
				response.sendRedirect(request.getContextPath() + "/category");
				return;
			}

			// Lấy danh sách video thuộc danh mục
			List<Video> videos = videoService.findByCategory(cateId);

			request.setAttribute("category", category);
			request.setAttribute("videos", videos);

			// Chuyển đến trang danh sách video của danh mục
			request.getRequestDispatcher("/views/category-videos.jsp").forward(request, response);

			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		switch (path) {
		case "/category/add":
			String cateName = request.getParameter("cateName");

			try {
				Part imagePart = request.getPart("icons");

				String imageName = uploadImage(imagePart);

				Category category = new Category();
				category.setCateName(cateName);
				category.setIcons(imageName);

				boolean result = categoryService.insert(category);

				if (result) {
					response.sendRedirect(request.getContextPath() + "/category");
				} else {
					request.setAttribute("error", "Thêm danh mục thất bại!");
					request.getRequestDispatcher("/views/category-add.jsp").forward(request, response);
				}

			} catch (IOException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/views/category-add.jsp").forward(request, response);
			}

			break;

		case "/category/edit":
			int cateId = Integer.parseInt(request.getParameter("cateId"));
			String cateName1 = request.getParameter("cateName");

			try {
				Category oldCategory = categoryService.findById(cateId);

				if (oldCategory == null) {
					response.sendRedirect(request.getContextPath() + "/category");
					return;
				}

				String imageName = oldCategory.getIcons();

				Part imagePart = request.getPart("icons");

				if (imagePart != null && imagePart.getSize() > 0) {
					String newImage = uploadImage(imagePart);

					if (newImage != null) {
						imageName = newImage;
					}
				}

				Category category = new Category();
				category.setCateId(cateId);
				category.setCateName(cateName1);
				category.setIcons(imageName);

				boolean result = categoryService.update(category);

				if (result) {
					response.sendRedirect(request.getContextPath() + "/category");
				} else {
					request.setAttribute("error", "Cập nhật danh mục thất bại!");
					request.setAttribute("category", category);
					request.getRequestDispatcher("/views/category-edit.jsp").forward(request, response);
				}

			} catch (IOException e) {
				request.setAttribute("error", e.getMessage());

				Category category = categoryService.findById(cateId);
				if (category != null) {
					category.setCateName(cateName1);
					request.setAttribute("category", category);
				}

				request.getRequestDispatcher("/views/category-edit.jsp").forward(request, response);
			}

			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	private String uploadImage(Part part) throws IOException {

		// Không chọn ảnh mới
		if (part == null || part.getSize() == 0) {
			return null;
		}

		// Lấy tên file
		String submittedFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

		// Lấy phần mở rộng
		String extension = "";

		int dotIndex = submittedFileName.lastIndexOf(".");

		if (dotIndex >= 0) {
			extension = submittedFileName.substring(dotIndex).toLowerCase();
		}

		// Chỉ chấp nhận các định dạng ảnh
		if (!extension.equals(".jpg") && !extension.equals(".jpeg") && !extension.equals(".png")
				&& !extension.equals(".gif") && !extension.equals(".webp")) {
			throw new IOException("Định dạng ảnh không hợp lệ!");
		}

		// Tạo tên file mới
		String fileName = System.currentTimeMillis() + extension;

		// Lấy đường dẫn thư mục uploads/categories trong ứng dụng
		String uploadPath = getServletContext().getRealPath(Constant.DIR);

		if (uploadPath == null) {
			throw new IOException("Không xác định được thư mục lưu ảnh!");
		}

		Path uploadDir = Paths.get(uploadPath);

		// Tạo thư mục nếu chưa tồn tại
		Files.createDirectories(uploadDir);

		// Đường dẫn file ảnh
		Path filePath = uploadDir.resolve(fileName);

		// Lưu ảnh
		try (InputStream inputStream = part.getInputStream()) {
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}

		return fileName;
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