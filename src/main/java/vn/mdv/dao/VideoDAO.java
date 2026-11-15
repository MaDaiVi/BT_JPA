package vn.mdv.dao;

import java.util.List;
import vn.mdv.models.Video;

public interface VideoDAO {

    List<Video> findAll();
    
    Video findById(String videoId);
    
    List<Video> findByTitle(String title);
    
    List<Video> findByCategory(int cateId);
    
    void insert(Video video);
    
    void update(Video video);
    
    void delete(String videoId);

}