package vn.mdv.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Videos")
public class Video {

    @Id
    @Column(name = "video_id", length = 50)
    private String videoId;

    @Column(name = "active", nullable = false)
    private int active;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "poster", length = 500)
    private String poster;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "views", nullable = false)
    private int views;

    @ManyToOne
    @JoinColumn(name = "cate_id", nullable = false)
    private Category category;

    public Video() {
    }

    // Getters và Setters

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}