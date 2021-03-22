package io.zerogone.user.model;

import io.zerogone.blog.model.CurrentUserBlog;

import java.util.List;

//TODO NotNull 속성 적용
public class CurrentUserInfo {
    private int id;
    private String name;
    private String nickName;
    private String email;
    private String imgUrl;
    private List<CurrentUserBlog> blogs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CurrentUserBlog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<CurrentUserBlog> blogs) {
        this.blogs = blogs;
    }
}
