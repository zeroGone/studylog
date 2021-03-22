package io.zerogone.blog.model;

import javax.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    private String introduce;

    @Column(name = "img_url")
    private String imgUrl;

    public Blog() {

    }

    public Blog(String name, String introduce, String imgUrl) {
        this.name = name;
        this.introduce = introduce;
        this.imgUrl = imgUrl;
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
