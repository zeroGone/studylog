package io.zerogone.model;

import io.zerogone.model.entity.User;

public class UserVo {
    private final int id;
    private final String name;
    private final String email;
    private final String nickName;
    private final String imgUrl;

    public UserVo(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        nickName = user.getNickName();
        imgUrl = user.getImgUrl();
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

    public String getImgUrl() {
        return imgUrl;
    }
}
