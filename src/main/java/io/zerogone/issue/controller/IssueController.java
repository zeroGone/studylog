package io.zerogone.issue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IssueController {
    @GetMapping("issue/list")
    public String getIssueListViewName() {
        return "issue_list";
    }

    @GetMapping("issue/write")
    public String getIssueWriteViewName() {
        return "issue_write";
    }
}
