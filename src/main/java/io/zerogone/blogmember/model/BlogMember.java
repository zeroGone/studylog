package io.zerogone.blogmember.model;

import io.zerogone.blogmember.converter.MemberRoleConverter;

import javax.persistence.*;

@Entity
@Table(name = "blog_member")
public class BlogMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "blog_id", nullable = false)
    private int blogId;

    @Column(name = "role_id")
    @Convert(converter = MemberRoleConverter.class)
    private MemberRole role;

    public BlogMember(int userId, int blogId, MemberRole role) {
        this.userId = userId;
        this.blogId = blogId;
        this.role = role;
    }

    public BlogMember() {

    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBlogId() {
        return blogId;
    }

    public MemberRole getRole() {
        return role;
    }
}
