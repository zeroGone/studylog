package io.zerogone.model;

public class BlogVo {
    private final int id;
    private final String name;
    private final String introduce;
    private final String imageUrl;

    public BlogVo(int id, String name, String introduce, String imageUrl) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.imageUrl = imageUrl;
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
}
