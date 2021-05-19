package io.zerogone.model.dto;

import io.zerogone.user.model.UserDto;
import io.zerogone.validator.NewEntity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDate;

public class CommentDto {
    @Range(min = 0, max = 0, message = "생성 시 id를 가지고 있으면 안됩니다", groups = {Default.class, NewEntity.class})
    private int id;
    @NotBlank(groups = {Default.class, NewEntity.class})
    private String contents;
    @NotNull(groups = NewEntity.class)
    private PostDto post;
    @NotNull(groups = NewEntity.class)
    private UserDto writer;
    private LocalDate createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public UserDto getWriter() {
        return writer;
    }

    public void setWriter(UserDto writer) {
        this.writer = writer;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
