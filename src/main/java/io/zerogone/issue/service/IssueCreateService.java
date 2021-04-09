package io.zerogone.issue.service;

import io.zerogone.issue.model.Issue;
import io.zerogone.issue.model.IssueDto;
import io.zerogone.issue.model.IssueVo;
import io.zerogone.issue.repository.IssueSaveDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IssueCreateService {
    private final IssueSaveDao issueSaveDao;

    public IssueCreateService(IssueSaveDao issueSaveDao) {
        this.issueSaveDao = issueSaveDao;
    }

    @Transactional
    public IssueVo createIssueAfterConverting(IssueDto issueDto) {
        Issue entityBeforeSaving = convertToEntity(issueDto);
        Issue entity = createIssue(entityBeforeSaving);
        return convertToVo(entity);
    }

    private Issue convertToEntity(IssueDto dto) {
        return new Issue(dto.getTitle(), dto.getContents(), dto.getCategory(), dto.getBlogMemeberId());
    }

    private Issue createIssue(Issue issue) {
        return issueSaveDao.save(issue);
    }

    private IssueVo convertToVo(Issue entity) {
        return new IssueVo(entity.getId());
    }
}
