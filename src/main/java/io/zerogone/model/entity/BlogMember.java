package io.zerogone.model.entity;

import io.zerogone.converter.MemberRoleConverter;
import io.zerogone.user.model.User;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "blog_member")
@DynamicUpdate
public class BlogMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false, updatable = false)
    private Blog blog;

    @Column(name = "role_id")
    @Convert(converter = MemberRoleConverter.class)
    private MemberRole role;

    BlogMember() {

    }

    public BlogMember(User user, Blog blog, MemberRole role) {
        this.user = user;
        this.blog = blog;
        this.role = role;
    }

    public int getUserId() {
        return user.getId();
    }

    public int getBlogId() {
        return blog.getId();
    }

    public String getName() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getNickName() {
        return user.getNickName();
    }

    public String getImageUrl() {
        return user.getImageUrl();
    }

    public MemberRole getRole() {
        return role;
    }

    public void acceptBlogInvitation() {
        role = MemberRole.MEMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogMember that = (BlogMember) o;
        return getBlogId() == that.getBlogId() && getUserId() == that.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getBlogId());
    }
}
