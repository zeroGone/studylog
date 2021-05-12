package io.zerogone.model.dto;

import io.zerogone.validator.NewEntity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.List;

public class UserDto {
    @Range(min = 0, max = 0, groups = NewEntity.class, message = "생성 시 id를 가지고 있으면 안됩니다")
    private int id;
    @NotEmpty(message = "유저의 이름이 있어야 합니다", groups = {NewEntity.class, Default.class})
    private String name;
    @NotEmpty(message = "유저의 닉네임이 있어야 합니다", groups = {NewEntity.class, Default.class})
    private String nickName;
    @NotEmpty(message = "유저의 이메일이 있어야 합니다", groups = {NewEntity.class, Default.class})
    private String email;
    private String imageUrl;
    private List<BlogDto> blogs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<BlogDto> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogDto> blogs) {
        this.blogs = blogs;
    }
}
