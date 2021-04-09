package io.zerogone.service;

import io.zerogone.model.IssueVo;
import io.zerogone.repository.IssueDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueSearchService {

    private final IssueDao issueDao;

    public IssueSearchService(IssueDao issueDao) {
        this.issueDao = issueDao;
    }


}
