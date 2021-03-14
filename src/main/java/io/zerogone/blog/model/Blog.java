package io.zerogone.blog.model;

import javax.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @Column(nullable = false)
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
}
