package io.zerogone.issue.model;

import io.zerogone.exception.EnumConvertException;

import java.util.Arrays;
import java.util.Objects;

public enum IssueCategory {
    NOTICE(1),
    QUESTION(2),
    CHAT(3),
    REQUEST(4);

    private final int id;

    IssueCategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static IssueCategory getIssueCategory(int id) {
        return Arrays.stream(IssueCategory.values())
                .filter(issueCategory -> Objects.equals(issueCategory.getId(), id))
                .findAny()
                .orElseThrow(() -> new EnumConvertException(
                        String.format("[id = %d]를 가진 issue의 카테고리는 없습니다", id)));
    }
}
