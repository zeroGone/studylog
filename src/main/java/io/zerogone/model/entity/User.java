package io.zerogone.model.entity;

import io.zerogone.exception.NotNullPropertyException;
import io.zerogone.model.UserDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    private int id;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "nick_name", nullable = false, unique = true, updatable = false)
    private String nickName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "create_date_time", insertable = false, updatable = false)
    private LocalDateTime createDateTime;

    @Column(name = "update_date_time", insertable = false, updatable = false)
    private LocalDateTime updateDateTime;

    User() {

    }

    public User(UserDto userDto) {
        validate(userDto);
        id = userDto.getId();
        name = userDto.getName();
        email = userDto.getEmail();
        nickName = userDto.getNickName();
        imageUrl = userDto.getImageUrl();
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
