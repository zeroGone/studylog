package io.zerogone.blog.model;

import io.zerogone.blog.service.MemberRoleConverter;

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

    public BlogMember() {

    }

    public BlogMember(int userId, MemberRole role) {
        this.userId = userId;
        this.role = role;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}
