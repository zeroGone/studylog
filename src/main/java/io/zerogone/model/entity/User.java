package io.zerogone.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
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

    @OneToMany
    @Fetch(FetchMode.SELECT)
    @WhereJoinTable(clause = "role_id = 1 OR 2")
    @JoinTable(name = "blog_member",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_id"))
    private List<Blog> blogs;

    User() {

    }

    public User(String name, String email, String nickName, String imageUrl) {
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
    }

    public User(int id, String name, String email, String nickName, String imageUrl) {
        this(name, email, nickName, imageUrl);
        this.id = id;
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

    public List<Blog> getBlogs() {
        return blogs;
    }
}
