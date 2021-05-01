package io.zerogone.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.WhereJoinTable;

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

    @OneToMany
    @Fetch(FetchMode.SELECT)
    @WhereJoinTable(clause = "role_id = 1 OR 2")
    @JoinTable(name = "blog_member",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members;

    Blog() {

    }

    public Blog(int id, String name, String introduce, String imageUrl) {
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

    public List<User> getMembers() {
        return members;
    }
}
