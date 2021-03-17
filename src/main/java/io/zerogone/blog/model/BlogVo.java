package io.zerogone.blog.model;

public class BlogVo {
    private final int id;
    private final String name;
    private final String introduce;
    private final String img_url;

    public BlogVo(int id, String name, String introduce, String img_url) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.img_url = img_url;
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
