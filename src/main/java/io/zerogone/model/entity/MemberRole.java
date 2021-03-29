package io.zerogone.model.entity;

import io.zerogone.exception.MemberRoleConvertException;

import java.util.Arrays;
import java.util.Objects;

public enum MemberRole {
    ADMIN(1),
    MEMBER(2),
    INVITING(3);

    private final int id;

    MemberRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MemberRole ofMemberRole(int id) {
        return Arrays.stream(MemberRole.values())
                .filter(memberRole -> Objects.equals(memberRole.getId(), id))
                .findAny()
                .orElseThrow(() -> new MemberRoleConvertException(
                        String.format("[id = %d]의 MemberRole은 존재하지 않습니다", id)));
    }
}
