package at.enums;

import lombok.Getter;

public enum Roles {
    CUSTOMER_EMPLOYEE_VK("Сотрудник подразделения-инициатора (ВК)"),
    CUSTOMER_EMPLOYEE_PPD("Сотрудник подразделения-инициатора (ППД)"),
    CUSTOMER_HEAD("Руководитель подразделения-инициатора"),
    DBBR_HEAD("Руководитель ДББР"),
    DBBR_EMPLOYEE("Сотрудник ДББР"),
    GVKPPD_HEAD("Руководитель УБ/ОБ"),
    GVKPPD_EMPLOYEE("Сотрудник ГВКиППД"),
    USER("Пользователь");
    @Getter
    private final String role;

    <T extends String> Roles(T type) {
        this.role = type;
    }
}
