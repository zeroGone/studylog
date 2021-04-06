package io.zerogone.model.entity;

import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.model.UserDto;

import javax.persistence.*;

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

    User() {

    }

    public User(UserDto userDto) {
        validate(userDto);
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

    private void validate(UserDto userDto) {
        if (userDto.getName() == null) {
            throw new NotNullPropertyException(User.class, "name");
        }
        if (userDto.getEmail() == null) {
            throw new NotNullPropertyException(User.class, "email");
        }
        if (userDto.getNickName() == null) {
            throw new NotNullPropertyException(User.class, "nickname");
        }
    }
}
