package io.zerogone.model.entity;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "blogId")
    private List<BlogMember> members;

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
