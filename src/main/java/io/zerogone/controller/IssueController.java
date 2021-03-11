package io.zerogone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IssueController {
    @GetMapping("issues")
    public String getIssueListViewName() {
        return "issue_list";
    }
}
