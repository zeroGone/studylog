package io.zerogone.model.dto;

import io.zerogone.model.entity.MemberRole;

public class BlogMemberDto extends DataTransferObject {
    private UserDto user;
    private BlogDto blog;
    private MemberRole role;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public BlogDto getBlog() {
        return blog;
    }

    public void setBlog(BlogDto blog) {
        this.blog = blog;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }
}
