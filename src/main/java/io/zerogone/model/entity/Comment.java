package io.zerogone.model.entity;

import io.zerogone.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String contents;

    @Column(name = "create_date_time", insertable = false, updatable = false)
    private LocalDateTime createDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User writer;

    Comment() {

    }

    public Comment(int id, String contents, Post post, User writer) {
        this.id = id;
        this.contents = contents;
        this.post = post;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public Post getPost() {
        return post;
    }

    public User getWriter() {
        return writer;
    }
}
