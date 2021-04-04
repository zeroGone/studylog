package io.zerogone.model;

import java.util.List;

public class CurrentUserInfo extends UserDto {
    private List<CurrentUserBlog> blogs;

    public CurrentUserInfo() {
    }

    public CurrentUserInfo(UserVo userVo) {
        this.id = userVo.getId();
        this.name = userVo.getName();
        this.nickName = userVo.getNickName();
        this.imgUrl = userVo.getImgUrl();
        this.email = userVo.getEmail();
    }

    public List<CurrentUserBlog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<CurrentUserBlog> blogs) {
        this.blogs = blogs;
    }
}
