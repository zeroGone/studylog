package io.zerogone.model.entity;

import io.zerogone.converter.MemberRoleConverter;
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

    private BlogMember(Builder builder) {
        this.user = new User(builder.userId, builder.name, builder.email, builder.nickName, builder.imageUrl);
        this.blog = builder.blog;
        this.role = builder.role;
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

    public static class Builder {
        private final Blog blog;
        private final int userId;
        private String name;
        private String nickName;
        private String email;
        private String imageUrl;
        private MemberRole role;

        public Builder(Blog blog, int userId) {
            this.blog = blog;
            this.userId = userId;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder role(MemberRole role) {
            this.role = role;
            return this;
        }

        public BlogMember build() {
            return new BlogMember(this);
        }
    }
}
