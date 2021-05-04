package io.zerogone.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.zerogone.model.entity.Category;
import io.zerogone.model.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostVo extends ValueObject {
    private final String title;
    private final String contents;
    private final boolean isReviewing;
    private final BlogVo blog;
    private final UserVo writer;
    private final List<String> categories;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private final LocalDateTime createDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private final LocalDateTime updateDateTime;

    public PostVo(Post post) {
        super(post.getId());
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.isReviewing = post.isReviewing();
        this.blog = post.getBlog();
        this.writer = post.getWriter();
        this.categories = post.getCategories().stream().map(Category::getName).collect(Collectors.toList());
        this.createDateTime = post.getCreateDateTime();
        this.updateDateTime = post.getUpdateDateTime();
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public boolean isReviewing() {
        return isReviewing;
    }

    public BlogVo getBlog() {
        return blog;
    }

    public UserVo getWriter() {
        return writer;
    }

    public List<String> getCategories() {
        return categories;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
