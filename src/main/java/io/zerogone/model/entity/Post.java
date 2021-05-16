package io.zerogone.model.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "post")
@DynamicUpdate
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(name = "reviewing", nullable = false, insertable = false)
    private boolean isReviewing;

    @Column(name = "hits", insertable = false)
    private int hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User writer;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "post_has_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @Column(name = "create_date_time", insertable = false, updatable = false)
    private LocalDateTime createDateTime;

    @Column(name = "update_date_time", insertable = false, updatable = false)
    private LocalDateTime updateDateTime;

    Post() {

    }

    public Post(String title, String contents, User writer, Blog blog) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.blog = blog;
        categories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Blog getBlog() {
        return blog;
    }

    public User getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public int getHits() {
        return hits;
    }

    public void hit() {
        this.hits += 1;
    }

    public boolean isReviewing() {
        return isReviewing;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
