package io.zerogone.blog.model;

public class BlogVo {
    private final int id;
    private final String name;
    private final String introduce;
    private final String imgUrl;

    public BlogVo(Blog blog) {
        this.id = blog.getId();
        this.name = blog.getName();
        this.introduce = blog.getIntroduce();
        this.imgUrl = blog.getImgUrl();
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

    public String getImgUrl() {
        return imgUrl;
    }
}
