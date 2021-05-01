package io.zerogone.model.dto;

import java.util.List;

public class BlogWithMembersDto extends BlogDto {
    private List<UserDto> members;

    public void setMembers(List<UserDto> members) {
        this.members = members;
    }

    public List<UserDto> getMembers() {
        return members;
    }
}
