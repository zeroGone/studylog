package io.zerogone.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BlogMember> blogs;

    User() {

    }

    public User(int id, String name, String email, String nickName, String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
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
