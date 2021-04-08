package io.zerogone.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    public BlogVo(int id, String name, String introduce, String imageUrl, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.imageUrl = imageUrl;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
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
