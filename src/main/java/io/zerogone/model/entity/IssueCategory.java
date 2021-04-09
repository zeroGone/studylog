package io.zerogone.model.entity;

import javax.persistence.*;

//@Entity
@Table(name = "issue_category")
public class IssueCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int categoryId;

    @Column(nullable = false, unique = true)
    private String name;

    public IssueCategory() {

    }

    public IssueCategory(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }
}
