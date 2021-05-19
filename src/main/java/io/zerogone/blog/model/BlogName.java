package io.zerogone.blog.model;

import javax.validation.constraints.NotBlank;

public class BlogName {
    @NotBlank
    private final String name;

    public BlogName(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
