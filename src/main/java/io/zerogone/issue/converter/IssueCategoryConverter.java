package io.zerogone.issue.converter;

import io.zerogone.issue.model.IssueCategory;

import javax.persistence.AttributeConverter;

public class IssueCategoryConverter implements AttributeConverter<IssueCategory, Integer> {
    @Override
    public Integer convertToDatabaseColumn(IssueCategory issueCategory) {
        return issueCategory.getId();
    }

    @Override
    public IssueCategory convertToEntityAttribute(Integer issueCategoryId) {
        return IssueCategory.getIssueCategory(issueCategoryId);
    }
}
