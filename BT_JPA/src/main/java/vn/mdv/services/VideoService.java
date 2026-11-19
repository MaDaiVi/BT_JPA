package vn.mdv.services;

import java.util.List;
import vn.mdv.models.Video;

public interface VideoService {

    List<Video> findAll();

    Video findById(String videoId);

    List<Video> findByTitle(String title);

    List<Video> findByCategory(int cateId);

    void insert(Video video);

    void update(Video video);

    void delete(String videoId);

    List<Video> findByUser(int userId);

    List<Video> findByUserWithPagination(int userId, int page, int pageSize);

    long countByUser(int userId);

    // Lấy tất cả video có phân trang
    List<Video> findAllWithPagination(int page, int pageSize);

    // Đếm tất cả video
    long countAll();
    
 // Lấy video đang hoạt động có phân trang
    List<Video> findActiveWithPagination(int page, int pageSize);

    // Đếm số video đang hoạt động
    long countActive();

    List<Video> findTop10Latest();

    void incrementViews(String videoId);
}