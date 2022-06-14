#language: ru
  Функционал: отправка на доработку и отклонение заявки

    Сценарий: №3, изменение утверждённой даты
#Предусловие для сценария
      Когда Роль подразделения-инициатора заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора (ППД)
        И создает заявку с типом Заявка на проверку персональных данных
        И статус заявки "Черновик"
      Тогда заполняет сведения о мероприятии
            |Вид мероприятия        |Предоставление фото-видеоматериалов по проведенному контрольному мероприятию|
            |Уровень значимости     |Средний                          |
            |Уровень срочности      |Не срочная                       |
            |Обоснование значимости |Не определен                     |
            |Обоснование срочности  |Не определен                     |
        И нажимает кнопку "Далее" на виджете "Сведения о мероприятии"
      Когда заполняет сведения о проверяемой организации
            |ИНН|7710026574|
            |ОГРН|1027700186062|
        И нажимает кнопку "Найти организацию" на виджете "Сведения о проверяемой организации"
        И нажимает кнопку "Далее" на виджете "Сведения о проверяемой организации"
      Тогда заполняет сведения об объекте проверки
            |Фамилия|Иванов|
            |Имя|Иван|
            |Отчество|Иванович|
            |Дата рождения|01.01.1990|
            |Место рождения|Москва|
            |Гражданство|РФ|
            |ИНН физического лица|123456789012|
            |Серия паспорта|1234|
            |Номер паспорта|567890|
            |Кем выдан документ|Отдел Москва|
            |Код подразделения|123-456|
            |Образование|Высшее|
            |Должность, на которую согласуется|Сотрудник|
            |Дополнительная информация|Заявка направляется в ДББР|
        И нажимает кнопку "Далее" на виджете "Сведения об объекте проверки"
        И нажимает кнопку "Далее" на виджете "Актуальная информация по заявке"
        И добавляет документ на шаге "Добавление документов"
        И нажимает кнопку "Отправить на согласование" на виджете "Документы"

      Когда выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения

#Сценарий №3
      Когда Руководитель ДББР заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает сформированную заявку
        И нажимает кнопку "Назначить" на виджете "Заявка"
        И выбирает назначенную заявку
        И выбирает исполнителя "User PU"
        И нажимает кнопку "Изменить исполнителя" на виджете "Заявка"
      Тогда статус заявки "Назначена"
        И выходит из приложения

      Когда Сотрудник ДББР заходит в приложение
        И выбирает роль Сотрудник ДББР
        И выбирает назначенную заявку
      Тогда нажимает кнопку "Направить на изменение срока" на виджете "Заявка"
        И выходит из приложения

      Когда Руководитель ДББР заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает сформированную заявку
        И нажимает кнопку "Согласовать запрос" на виджете "Заявка"
      Тогда статус заявки "Отложена"
        И выходит из приложения

      Когда Роль подразделения-инициатора заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора (ППД)
        И выбирает пункт меню "Заявки"
        И выбирает заявку в очереди
      Тогда заполняет дату исполнения
            |Утвержденная дата исполнения|01.01.2022|
        И нажимает кнопку "Отправить на согласование" на виджете "Заявка"
        И статус заявки "Отложена"

      Когда выбирает роль Руководитель подразделения-инициатора
        И выбирает заявку в очереди
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения

      Когда Руководитель ДББР заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает сформированную заявку
        И нажимает кнопку "Назначить" на виджете "Заявка"
        И выбирает назначенную заявку
        И выбирает исполнителя "User PU"
        И нажимает кнопку "Изменить исполнителя" на виджете "Заявка"
      Тогда статус заявки "Назначена"
        И выходит из приложения