package io.zerogone.model.dto;

import io.zerogone.model.BlogDto;
import io.zerogone.model.entity.Blog;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;
import io.zerogone.model.entity.User;
import io.zerogone.model.vo.UserVo;

import java.util.List;
import java.util.stream.Collectors;

public class PostCreateDto implements CreateDto<Post> {
    private String title;
    private String contents;
    private UserVo writer;
    private BlogDto blog;
    private List<String> categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public UserVo getWriter() {
        return writer;
    }

    public void setWriter(UserVo writer) {
        this.writer = writer;
    }

    public BlogDto getBlog() {
        return blog;
    }

    public void setBlog(BlogDto blog) {
        this.blog = blog;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public Post convertToEntity() {
        User writerEntity = new User(
                writer.getId(),
                writer.getName(),
                writer.getEmail(),
                writer.getNickName(),
                writer.getImageUrl());

        Blog blogEntity = new Blog(
                blog.getId(),
                blog.getName(),
                blog.getIntroduce(),
                blog.getImageUrl());

        List<Category> categorieEntities = categories
                .stream()
                .map(Category::new)
                .collect(Collectors.toList());

        return new Post(title, contents, writerEntity, blogEntity, categorieEntities);
    }
}
