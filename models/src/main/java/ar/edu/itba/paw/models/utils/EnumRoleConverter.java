package ar.edu.itba.paw.models.utils;

import ar.edu.itba.paw.models.user.UserRole;

import javax.persistence.AttributeConverter;

public class EnumRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return userRole.toString();
    }

    @Override
    public UserRole convertToEntityAttribute(String s) {
        return UserRole.valueOf(s);
    }
}
