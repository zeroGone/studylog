package io.zerogone.model.entity;

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

    @Column(name = "post_id", nullable = false)
    private int postId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User writer;

    Comment() {

    }

    public Comment(String contents, int postId, User writer) {
        this.contents = contents;
        this.postId = postId;
        this.writer = writer;
    }

    public Comment(int id, String contents, int postId, User writer) {
        this(contents, postId, writer);
        this.id = id;
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

    public int getPostId() {
        return postId;
    }

    public User getWriter() {
        return writer;
    }
}
