package io.zerogone.model;

import io.zerogone.model.entity.User;

import java.time.LocalDateTime;

public class UserVo {
    private final int id;
    private final String name;
    private final String email;
    private final String nickName;
    private final String imageUrl;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public UserVo(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        nickName = user.getNickName();
        imageUrl = user.getImageUrl();
        createDateTime = user.getCreateDateTime();
        updateDateTime = user.getUpdateDateTime();
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
