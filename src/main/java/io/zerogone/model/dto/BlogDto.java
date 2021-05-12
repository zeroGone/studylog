package io.zerogone.model.dto;

import io.zerogone.validator.NotOverlap;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BlogDto {
    private int id;
    @NotEmpty(message = "블로그의 이름이 있어야 합니다")
    private String name;
    private String introduce;
    private String imageUrl;
    @NotOverlap
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
