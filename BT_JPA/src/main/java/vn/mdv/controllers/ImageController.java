package vn.mdv.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.mdv.util.Constant;

@WebServlet("/image")
public class ImageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy tên ảnh từ URL
        String fileName = request.getParameter("fname");

        if (fileName == null || fileName.trim().isEmpty()
                || fileName.contains("/")
                || fileName.contains("\\")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Lấy đường dẫn thư mục ảnh
        String uploadPath = getServletContext().getRealPath(Constant.DIR);

        if (uploadPath == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Path uploadDir = Paths.get(uploadPath)
                              .toAbsolutePath()
                              .normalize();

        Path filePath = uploadDir.resolve(fileName)
                                 .normalize();

        // Kiểm tra đường dẫn và sự tồn tại của ảnh
        if (!filePath.startsWith(uploadDir)
                || !Files.isRegularFile(filePath)) {

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Kiểm tra định dạng ảnh
        String contentType = Files.probeContentType(filePath);

        if (contentType == null || !contentType.startsWith("image/")) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        // Trả ảnh về trình duyệt
        response.setContentType(contentType);
        response.setContentLengthLong(Files.size(filePath));

        try (var inputStream = Files.newInputStream(filePath)) {
            inputStream.transferTo(response.getOutputStream());
        }
    }
}