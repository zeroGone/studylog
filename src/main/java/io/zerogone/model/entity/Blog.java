package io.zerogone.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @Column(updatable = false)
    private String introduce;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "blog")
    private List<BlogMember> members;

    Blog() {

    }

    public Blog(String name, String introduce, String imageUrl) {
        this.name = name;
        this.introduce = introduce;
        this.imageUrl = imageUrl;
    }

    public Blog(int id, String name, String introduce, String imageUrl) {
        this(name, introduce, imageUrl);
        this.id = id;
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

    public List<BlogMember> getMembers() {
        return members;
    }
}
