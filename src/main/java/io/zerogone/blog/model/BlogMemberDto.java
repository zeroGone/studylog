package io.zerogone.blog.model;

import io.zerogone.model.entity.MemberRole;

import javax.validation.constraints.Positive;
import java.util.Objects;

public class BlogMemberDto {
    @Positive(message = "블로그 멤버의 아이디는 명시되어야 합니다")
    private int id;//userId
    private String name;
    private String nickName;
    private String email;
    private String imageUrl;
    private int blogId;
    private MemberRole role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogMemberDto that = (BlogMemberDto) o;
        return id == that.id && blogId == that.blogId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogId);
    }
}
