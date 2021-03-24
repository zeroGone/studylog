package io.zerogone.user.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(name = "img_url")
    private String imgUrl;

    public User() {

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
