package io.zerogone.model.dto;

public class BlogCreateDto extends BlogWithMembersDto {
    private UserDto admin;

    public void setAdmin(UserDto admin) {
        this.admin = admin;
    }

    public UserDto getAdmin() {
        return admin;
    }
}
