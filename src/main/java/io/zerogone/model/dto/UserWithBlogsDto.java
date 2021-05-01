package io.zerogone.model.dto;

import java.util.List;

public class UserWithBlogsDto extends UserDto {
    private List<BlogDto> blogs;

    public void setBlogs(List<BlogDto> blogs) {
        this.blogs = blogs;
    }

    public List<BlogDto> getBlogs() {
        return blogs;
    }
}
