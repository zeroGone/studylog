package io.zerogone.issue.controller;

import io.zerogone.issue.model.IssueDto;
import io.zerogone.issue.model.IssueVo;
import io.zerogone.issue.service.IssueCreateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class IssueCreateController {
    private final IssueCreateService issueCreateService;

    public IssueCreateController(IssueCreateService issueCreateService) {
        this.issueCreateService = issueCreateService;
    }

    @PostMapping("issue/write")
    public String redirectToIssuePageAfterCreatingIssue(@RequestBody IssueDto issue) {
        IssueVo createdIssue = issueCreateService.createIssueAfterConverting(issue);
        return "redirect:/issue/" + createdIssue.getId();
    }
}
