package io.zerogone.model.vo;

import io.zerogone.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserVo extends ValueObject {
    private final String name;
    private final String email;
    private final String nickName;
    private final String imageUrl;
    private final List<BlogVo> blogs;

    public UserVo(int id, String name, String email, String nickName, String imageUrl) {
        super(id);
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

    public UserVo(User user) {
        super(user.getId());
        name = user.getName();
        email = user.getEmail();
        nickName = user.getNickName();
        imageUrl = user.getImageUrl();
        blogs = user.getBlogs()
                .stream()
                .map(BlogVo::new)
                .collect(Collectors.toList());
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
