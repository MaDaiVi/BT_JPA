package vn.mdv.services;

import java.util.List;

import vn.mdv.dao.VideoDAO;
import vn.mdv.dao.VideoDAOImpl;
import vn.mdv.models.Video;

public class VideoServiceImpl implements VideoService {

    private VideoDAO videoDAO = new VideoDAOImpl();

    @Override
    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    @Override
    public Video findById(String videoId) {
        return videoDAO.findById(videoId);
    }

    @Override
    public List<Video> findByTitle(String title) {
        return videoDAO.findByTitle(title);
    }

    @Override
    public List<Video> findByCategory(int cateId) {
        return videoDAO.findByCategory(cateId);
    }

    @Override
    public void insert(Video video) {
        videoDAO.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDAO.update(video);
    }

    @Override
    public void delete(String videoId) {
        videoDAO.delete(videoId);
    }
    
    @Override
    public List<Video> findByUser(int userId) {
        return videoDAO.findByUser(userId);
    }

    @Override
    public List<Video> findByUserWithPagination(int userId, int page, int pageSize) {
        return videoDAO.findByUserWithPagination(userId, page, pageSize);
    }

    @Override
    public long countByUser(int userId) {
        return videoDAO.countByUser(userId);
    }

    @Override
    public List<Video> findTop10Latest() {
        return videoDAO.findTop10Latest();
    }
    
    @Override
    public void incrementViews(String videoId) {
        videoDAO.incrementViews(videoId);
    }
}