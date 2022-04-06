#language: ru
  Функционал: доработка заявки у инициатора

    @Ready
    Сценарий: №2, 3, 4, 5, 6

      Когда Администратор1 заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора
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

#Сценарий №2
      Когда выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
      Тогда нажимает кнопку "На доработку" на виджете "Заявка"

      Когда выбирает роль Сотрудник подразделения-инициатора
        И выбирает пункт меню "Заявки"
        И выбирает отправленную на доработку заявку
      Тогда перезаполняет сведения о мероприятии
            |Уровень значимости     |Низкий                          |
        И нажимает кнопку "Сохранить" на виджете "Сведения о мероприятии"
        И перезаполняет сведения об объекте проверки
            |Дополнительная информация|Новая дополнительная информация|
        И нажимает кнопку "Сохранить" на виджете "Сведения об объекте проверки"
        И нажимает кнопку "Отправить на согласование" на виджете "Заявка"

      Когда выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения

#Сценарий №4
      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает сформированную заявку
        И выбирает исполнителя "User PU"
        И нажимает кнопку "Назначить" на виджете "Заявка"
      Тогда статус заявки "Назначена"
        И выходит из приложения

      Когда Пользователь1 заходит в приложение
        И выбирает роль Сотрудник ДББР
        И выбирает назначенную заявку
      Тогда нажимает кнопку "Направить на изменение срока" на виджете "Заявка"
        И выходит из приложения

#В будущем подставить другую роль
      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает сформированную заявку
        И выбирает исполнителя "User PU"
        И нажимает кнопку "Назначить" на виджете "Заявка"
      Тогда статус заявки "Назначена"
        И выходит из приложения

#Сценарий №6
      Когда Пользователь1 заходит в приложение
        И выбирает роль Сотрудник ДББР
        И выбирает назначенную заявку
        И нажимает кнопку "Взять в обработку" на виджете "Заявка"
      Тогда заполняет результат обработки
            |Обоснование уточнения|Необходимо уточнить данные|
        И нажимает кнопку "Сохранить" на виджете "Результаты обработки заявки"
        И нажимает кнопку "На уточнение" на виджете "Заявка"
        И выходит из приложения

      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает заявку в работе
      Тогда нажимает кнопку "Отклонить" на виджете "Заявка"
        И выходит из приложения

      Когда Пользователь1 заходит в приложение
        И выбирает роль Сотрудник ДББР
        И выбирает назначенную заявку
      Тогда нажимает кнопку "На уточнение" на виджете "Заявка"
        И выходит из приложения

      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает заявку в работе
      Тогда нажимает кнопку "Согласовать" на виджете "Заявка"
        И выходит из приложения

      Когда Администратор1 заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора
        И выбирает пункт меню "Заявки"
        И выбирает отправленную на доработку заявку
      Тогда перезаполняет сведения о мероприятии
            |Уровень значимости     |Средний                          |
        И нажимает кнопку "Сохранить" на виджете "Сведения о мероприятии"
        И перезаполняет сведения об объекте проверки
            |Дополнительная информация|Новая дополнительная информация 2|
        И нажимает кнопку "Сохранить" на виджете "Сведения об объекте проверки"
        И нажимает кнопку "Отправить на согласование" на виджете "Заявка"

      Когда выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения


#Сценарий №5
      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает сформированную заявку
        И выбирает исполнителя "User PU"
        И нажимает кнопку "Назначить" на виджете "Заявка"
      Тогда статус заявки "Назначена"
        И выходит из приложения

      Когда Пользователь1 заходит в приложение
        И выбирает роль Сотрудник ДББР
        И выбирает назначенную заявку
        И нажимает кнопку "Взять в обработку" на виджете "Заявка"
      Тогда заполняет результат обработки
            |Обоснование отклонения|Некорректное оформление заявки|
        И нажимает кнопку "Сохранить" на виджете "Результаты обработки заявки"
        И нажимает кнопку "Отклонить заявку" на виджете "Заявка"
        И выходит из приложения

      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает заявку в работе
      Тогда нажимает кнопку "Отклонить" на виджете "Заявка"
        И выходит из приложения

      Когда Пользователь1 заходит в приложение
        И выбирает роль Сотрудник ДББР
        И выбирает назначенную заявку
      Тогда нажимает кнопку "Отклонить заявку" на виджете "Заявка"
        И выходит из приложения

      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает заявку в работе
      Тогда нажимает кнопку "Согласовать" на виджете "Заявка"
        И выходит из приложения

      Когда Администратор1 заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора
        И выбирает пункт меню "Заявки"
        И выбирает отправленную на доработку заявку
      Тогда статус заявки "Отклонена"
        И выходит из приложения