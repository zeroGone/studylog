package io.zerogone.blog.model;

public class BlogVo {
    private final int id;
    private final String name;
    private final String introduce;
    private final String img_url;

    public BlogVo(Blog blog) {
        this.id = blog.getId();
        this.name = blog.getName();
        this.introduce = blog.getIntroduce();
        this.img_url = blog.getImgUrl();
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

    public String getImg_url() {
        return img_url;
    }
}
