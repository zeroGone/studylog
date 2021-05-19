package io.zerogone.blog.model;

import io.zerogone.common.NewEntity;
import io.zerogone.blog.validator.NotOverlap;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.List;

public class BlogDto {
    @Range(min = 0, max = 0, groups = NewEntity.class, message = "생성 시 id를 가지고 있으면 안됩니다")
    private int id;
    @NotEmpty(message = "블로그의 이름이 있어야 합니다", groups = {NewEntity.class, Default.class})
    private String name;
    private String introduce;
    private String imageUrl;
    @NotOverlap(groups = {NewEntity.class, Default.class})
    private List<@Valid BlogMemberDto> members;
    private String invitationKey;

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<BlogMemberDto> getMembers() {
        return members;
    }

    public void setMembers(List<BlogMemberDto> members) {
        this.members = members;
    }

    public String getInvitationKey() {
        return invitationKey;
    }

    public void setInvitationKey(String invitationKey) {
        this.invitationKey = invitationKey;
    }
}
