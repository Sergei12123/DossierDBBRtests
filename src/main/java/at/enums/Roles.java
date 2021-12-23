package at.enums;

import lombok.Getter;

public enum Roles {
    CUSTOMER_EMPLOYEE("CUSTOMER_EMPLOYEE"),
    CUSTOMER_HEAD("CUSTOMER_HEAD"),
    DBBR_HEAD("DBBR_HEAD"),
    DBBR_EMPLOYEE("DBBR_EMPLOYEE"),
    USER("Пользователь");
    @Getter
    private final String role;

    <T extends String> Roles(T type) {
        this.role = type;
    }
}
