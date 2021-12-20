package at.enums;

import lombok.Getter;

public enum Roles {
    CUSTOMER_EMPLOYEE("Сотрудник");
    @Getter
    private final String role;

    <T extends String> Roles(T type) {
        this.role = type;
    }
}
