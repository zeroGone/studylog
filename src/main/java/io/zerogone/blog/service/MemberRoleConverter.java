package io.zerogone.blog.service;

import io.zerogone.blog.model.MemberRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MemberRoleConverter implements AttributeConverter<MemberRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MemberRole enumTypeRole) {
        return enumTypeRole.getId();
    }

    @Override
    public MemberRole convertToEntityAttribute(Integer roleId) {
        return MemberRole.ofMemberRole(roleId);
    }
}
