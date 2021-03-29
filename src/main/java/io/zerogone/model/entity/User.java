package io.zerogone.model.entity;

import io.zerogone.model.CurrentUserInfo;
import io.zerogone.model.UserCreateDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "userId")
    private List<BlogMember> blogs;

    public User() {

    }

    public User(UserCreateDto userDto) {
        id = userDto.getId();
        name = userDto.getName();
        email = userDto.getEmail();
        nickName = userDto.getNickName();
        imgUrl = userDto.getImgUrl();
    }

    public User(CurrentUserInfo userInfo) {
        id = userInfo.getId();
        name = userInfo.getName();
        email = userInfo.getEmail();
        nickName = userInfo.getNickName();
        imgUrl = userInfo.getImgUrl();
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
