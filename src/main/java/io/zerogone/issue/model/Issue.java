package io.zerogone.issue.model;

import io.zerogone.issue.converter.IssueCategoryConverter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(name = "close_status")
    private boolean isClose;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDate createDate;

    @Convert(converter = IssueCategoryConverter.class)
    @Column(name = "issue_category_id", nullable = false)
    private IssueCategory category;

    @Column(name = "blog_member_id")
    private int blogMemberId;

    public Issue() {
    }

    public Issue(String title, String contents, IssueCategory category, int blogMemberId) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.blogMemberId = blogMemberId;
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

    public boolean isClose() {
        return isClose;
    }

    public LocalDate getCreateDate() {
        return LocalDate.of(createDate.getYear(), createDate.getMonth(), createDate.getDayOfMonth());
    }

    public IssueCategory getCategory() {
        return category;
    }

    public int getBlogMemberId() {
        return blogMemberId;
    }
}
