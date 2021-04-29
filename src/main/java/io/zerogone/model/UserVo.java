package io.zerogone.model;

import java.util.ArrayList;
import java.util.List;

public class UserVo {
    private final int id;
    private final String name;
    private final String email;
    private final String nickName;
    private final String imageUrl;
    private final List<BlogVo> blogs;

    public UserVo(int id, String name, String email, String nickName, String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        blogs = new ArrayList<>();
    }

    public UserVo(int id, String name, String email, String nickName, String imageUrl, List<BlogVo> blogs) {
        this(id, name, email, nickName, imageUrl);
        this.blogs.addAll(blogs);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<BlogVo> getBlogs() {
        return blogs;
    }
}
