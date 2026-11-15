package vn.mdv.test;

import java.util.List;

import vn.mdv.models.Video;
import vn.mdv.services.VideoService;
import vn.mdv.services.VideoServiceImpl;

public class Test {

    public static void main(String[] args) {

        VideoService videoService = new VideoServiceImpl();

        // Kiểm tra lấy tất cả Video
        List<Video> videos = videoService.findAll();

        for (Video video : videos) {
            System.out.println("Video ID: " + video.getVideoId());
            System.out.println("Title: " + video.getTitle());
            System.out.println("Category: " + video.getCategory().getCateName());
            System.out.println("-------------------------");
        }
    }
}