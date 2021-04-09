package io.zerogone.issue.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IssueController {
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @GetMapping("{issues/name}")
    public String getIssueListViewName(@PathVariable String name) {
        logger.debug("blog name: " + name);
        return "issue_list";
    }

    @GetMapping("issue/list")
    public String getIssueListViewName() {
        return "issue_list";
    }

    @GetMapping("issue/write")
    public String getIssueWriteViewName() {
        return "issue_write";
    }
}
