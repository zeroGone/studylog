package io.zerogone.blog.model;

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

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public BlogMember() {

    }

    public BlogMember(int userId, MemberRole role) {
        this.userId = userId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

}
