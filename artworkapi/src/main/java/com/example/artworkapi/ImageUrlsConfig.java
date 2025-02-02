package com.example.artworkapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "image-urls")
public class ImageUrlsConfig {
//
//    // List for anime images
//    private List<ImageDetail> anime;
//
//    // List for JJK images
//    private List<ImageDetail> jjk;
//
//    // Getters and Setters
//    public List<ImageDetail> getAnime() {
//        return anime;
//    }
//
//    public void setAnime(List<ImageDetail> anime) {
//        this.anime = anime;
//    }
//
//    public List<ImageDetail> getJjk() {
//        return jjk;
//    }
//
//    public void setJjk(List<ImageDetail> jjk) {
//        this.jjk = jjk;
//    }
//
//    // Inner static class to hold details for each image
//    public static class ImageDetail {
//        private String url;
//        private String description;
//        private String category;
//
//        // Getters and Setters
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public String getCategory() {
//            return category;
//        }
//
//        public void setCategory(String category) {
//            this.category = category;
//        }
//    }
}
