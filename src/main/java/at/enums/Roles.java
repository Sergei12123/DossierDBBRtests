package at.enums;

import lombok.Getter;

public enum Roles {
    CUSTOMER_EMPLOYEE("Сотрудник подразделения-инициатора"),
    CUSTOMER_HEAD("Руководитель подразделения-инициатора"),
    DBBR_HEAD("Руководитель ДББР"),
    DBBR_EMPLOYEE("Сотрудник ДББР"),
    USER("Пользователь");
    @Getter
    private final String role;

    <T extends String> Roles(T type) {
        this.role = type;
    }
}
