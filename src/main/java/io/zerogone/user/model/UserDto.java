package io.zerogone.user.model;

import io.zerogone.blog.model.BlogDto;
import io.zerogone.common.NewEntity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.util.List;

public class UserDto {
    @Range(min = 0, max = 0, groups = NewEntity.class, message = "생성 시 id를 가지고 있으면 안됩니다")
    @Positive(message = "유저 식별자를 포함하고 있어야합니다")
    private int id;

    @NotBlank(message = "유저의 이름이 있어야 합니다", groups = {NewEntity.class, Default.class})
    private String name;

    @NotBlank(message = "유저의 닉네임이 있어야 합니다", groups = {NewEntity.class, Default.class})
    private String nickName;

    @Email(message = "이메일 형식이어야 합니다", groups = NewEntity.class)
    @NotBlank(message = "유저의 이메일이 있어야 합니다", groups = {NewEntity.class, Default.class})
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
