package io.zerogone.model.entity;

import io.zerogone.service.MemberRoleConverter;

import javax.persistence.*;

@Entity
@Table(name = "blog_member")
public class BlogMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(targetEntity = Blog.class)
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(name = "role_id")
    @Convert(converter = MemberRoleConverter.class)
    private MemberRole role;

    public BlogMember(User user, Blog blog, MemberRole role) {
        this.user = user;
        this.blog = blog;
        this.role = role;
    }

    public BlogMember() {

    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Blog getBlog() {
        return blog;
    }

    public MemberRole getRole() {
        return role;
    }
}
