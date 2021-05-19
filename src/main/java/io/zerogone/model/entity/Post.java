package io.zerogone.model.entity;

import io.zerogone.user.model.User;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    @JoinColumn(name = "blog_id", referencedColumnName = "id", updatable = false)
    private Blog blog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    private User writer;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "post_has_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @Column(name = "create_date_time", insertable = false, updatable = false)
    private LocalDateTime createDateTime;

    @Column(name = "update_date_time", insertable = false, updatable = false)
    private LocalDateTime updateDateTime;

    Post() {

    }

    public Post(int id, String title, String contents, Blog blog, User writer) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.blog = blog;
        categories = new HashSet<>();
        createDateTime = LocalDateTime.now();
        updateDateTime = LocalDateTime.now();
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

    public Set<Category> getCategories() {
        return categories;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
