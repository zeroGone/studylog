package io.zerogone.model.dto;

import java.util.Set;

public class BlogDto {
    private int id;
    private String name;
    private String introduce;
    private String imageUrl;
    private Set<BlogMemberDto> members;
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

    public Set<BlogMemberDto> getMembers() {
        return members;
    }

    public void setMembers(Set<BlogMemberDto> members) {
        this.members = members;
    }

    public String getInvitationKey() {
        return invitationKey;
    }

    public void setInvitationKey(String invitationKey) {
        this.invitationKey = invitationKey;
    }
}
