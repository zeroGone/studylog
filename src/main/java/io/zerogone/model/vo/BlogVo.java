package io.zerogone.model.vo;

import io.zerogone.model.entity.Blog;

public class BlogVo extends ValueObject {
    private final String name;
    private final String introduce;
    private final String imageUrl;

    public BlogVo(int id, String name, String introduce, String imageUrl) {
        super(id);
        this.name = name;
        this.introduce = introduce;
        this.imageUrl = imageUrl;
    }

    public BlogVo(Blog blog) {
        super(blog.getId());
        name = blog.getName();
        introduce = blog.getIntroduce();
        imageUrl = blog.getImageUrl();
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
}
