package io.zerogone.model.entity;

import io.zerogone.service.MemberRoleConverter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false)
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

    public BlogMember(User user, Blog blog) {
        this(user, blog, MemberRole.INVITING);
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
        return blog.getId();
    }

    public String getBlogName() {
        return blog.getName();
    }

    public String getBlogIntroduce() {
        return blog.getIntroduce();
    }

    public String getBlogImageUrl() {
        return blog.getImageUrl();
    }

    public MemberRole getRole() {
        return role;
    }

    public void acceptBlogInvitation() {
        role = MemberRole.MEMBER;
    }
}
