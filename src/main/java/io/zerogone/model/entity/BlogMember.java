package io.zerogone.model.entity;

import io.zerogone.service.MemberRoleConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog_member")
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

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_invitation_key_id")
    private BlogInvitationKey blogInvitationKey;

    @Column(name = "create_date_time", insertable = false, updatable = false)
    private LocalDateTime createDateTime;

    @Column(name = "update_date_time", insertable = false, updatable = false)
    private LocalDateTime updateDateTime;

    BlogMember() {

    }

    public BlogMember(User user, Blog blog, MemberRole role) {
        this.user = user;
        this.blog = blog;
        this.role = role;
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

    public LocalDateTime getBlogCreateDateTime() {
        return blog.getCreateDateTime();
    }

    public LocalDateTime getBlogUpdateDateTime() {
        return blog.getUpdateDateTime();
    }

    public MemberRole getRole() {
        return role;
    }

    public String getBlogInvitationKey() {
        return blogInvitationKey.getValue();
    }

    public void setBlogInvitationKey(BlogInvitationKey blogInvitationKey) {
        this.blogInvitationKey = blogInvitationKey;
    }

    public void acceptBlogInvitation() {
        role = MemberRole.MEMBER;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
