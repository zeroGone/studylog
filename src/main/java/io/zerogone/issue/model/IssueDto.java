package io.zerogone.issue.model;

public class IssueDto {
    private String title;
    private String contents;
    private IssueCategory category;
    private int blogMemeberId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public IssueCategory getCategory() {
        return category;
    }

    public void setCategory(IssueCategory category) {
        this.category = category;
    }

    public int getBlogMemeberId() {
        return blogMemeberId;
    }

    public void setBlogMemeberId(int blogMemeberId) {
        this.blogMemeberId = blogMemeberId;
    }
}
