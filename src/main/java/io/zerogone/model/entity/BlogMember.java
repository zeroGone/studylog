package io.zerogone.model.entity;

import io.zerogone.service.MemberRoleConverter;
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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "blog_id", nullable = false, updatable = false)
    private int blogId;

    @Column(name = "role_id")
    @Convert(converter = MemberRoleConverter.class)
    private MemberRole role;

    BlogMember() {

    }

    public BlogMember(User user, int blogId, MemberRole role) {
        this.user = user;
        this.blogId = blogId;
        this.role = role;
    }

    public BlogMember(User user, int blogId) {
        this(user, blogId, MemberRole.INVITING);
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return user.getId();
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

    public int getBlogId() {
        return blogId;
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
        return blogId == that.blogId && getUserId() == that.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), blogId);
    }
}
