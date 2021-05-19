package io.zerogone.model.dto;

import io.zerogone.user.model.UserDto;
import io.zerogone.validator.NewEntity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.util.List;

public class PostDto {
    @Range(min = 0, max = 0, groups = NewEntity.class, message = "생성 시 id를 가지고 있으면 안됩니다")
    private int id;
    @NotBlank(message = "게시글의 제목이 있어야 합니다", groups = {Default.class, NewEntity.class})
    private String title;
    @NotBlank(message = "게시글의 내용이 있어야 합니다", groups = {Default.class, NewEntity.class})
    private String contents;
    private int hits;
    private UserDto writer;
    private BlogDto blog;
    private List<String> categories;
    private LocalDate createDate;
    private LocalDate updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public UserDto getWriter() {
        return writer;
    }

    public void setWriter(UserDto writer) {
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
