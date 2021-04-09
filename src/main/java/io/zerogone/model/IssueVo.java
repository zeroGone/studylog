package io.zerogone.model;

import io.zerogone.model.entity.IssueCategory;

import java.util.List;

public class IssueVo {
    private final int id;
    private final String title;
    private final String contents;
    private final int closeStatus;
    private final int createDate;
    private final List<IssueCategory> categorries;
    private int blogId;

    public IssueVo(int id, String title, String contents, int closeStatus, int createDate, List<IssueCategory> categorries, int blogId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.closeStatus = closeStatus;
        this.createDate = createDate;
        this.categorries = categorries;
        this.blogId = blogId;
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

    public int getCreateDate() {
        return createDate;
    }

    public List<IssueCategory> getCategorries() {
        return categorries;
    }

    public int getBlogId() {
        return blogId;
    }
}
