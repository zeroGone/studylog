package io.zerogone.blog.post.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, updatable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts;

    Category() {

    }

    public Category(String name) {
        this.name = name;
        posts = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
