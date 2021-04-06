package io.zerogone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.zerogone.model.entity.Blog;

import java.time.LocalDateTime;

public class BlogVo {
    private final int id;
    private final String name;
    private final String introduce;
    private final String imageUrl;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private final LocalDateTime createDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private final LocalDateTime updateDateTime;

    public BlogVo(Blog blog) {
        this.id = blog.getId();
        this.name = blog.getName();
        this.introduce = blog.getIntroduce();
        this.imageUrl = blog.getImageUrl();
        this.createDateTime = blog.getCreateDateTime();
        this.updateDateTime = blog.getUpdateDateTime();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
