package io.zerogone.model.entity;

import io.zerogone.model.UserDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
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

    @OneToMany(mappedBy = "user")
    private List<BlogMember> blogs;

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

    public User(UserDto userDto) {
        id = userDto.getId();
        name = userDto.getName();
        email = userDto.getEmail();
        nickName = userDto.getNickName();
        imgUrl = userDto.getImgUrl();
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