package io.zerogone.domain.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User extends BaseEntity {
    @Embedded
    private UserName name;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<BlogUser> blogs = new HashSet<>();

    User() {

    }

    public User(String name, String nickName, String email, String imageUrl) {
        this.name = new UserName(name, nickName);
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public UserName getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Set<Blog> getBlogs() {
        return blogs.stream().map(BlogUser::getBlog).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return name.equals(user.getName()) && email.equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
