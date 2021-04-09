package io.zerogone.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Entity
@Table(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(name = "close_status")
    private int closeStatus;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "id")
    private List<IssueCategory> categories;

    @Column(name = "blog_id", nullable = false)
    private int blogId;


    public Issue() {

    }

    public Issue(int id, String title, String contents, int closeStatus, Date createDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.closeStatus = closeStatus;
        this.createDate = createDate;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public int getCloseStatus() {
        return closeStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
