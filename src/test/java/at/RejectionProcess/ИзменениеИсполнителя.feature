ОтправкаНаДоработкуИОтклонение.feature#language: ru
  Функционал: отправка на доработку и отклонение заявки
    @Ready
    Сценарий: №4, изменение исполнителя заявки
#Предусловие для сценария
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
          |Регистрационный номер|3202|
          |Наименование организации|АО НОКССБАНК|
        И нажимает кнопку "Найти организацию" на виджете "Сведения о проверяемой организации"
        И нажимает кнопку "Далее" на виджете "Сведения о проверяемой организации"
      Тогда заполняет сведения об объекте проверки
            |Фамилия|Иванов|
            |Имя|Иван|
            |Отчество|Иванович|
            |Дата рождения|01.01.1990|
            |Место рождения|Москва|
            |Гражданство|РФ|
            |Серия паспорта|1234|
            |Номер паспорта|567890|
            |Кем выдан документ|Отдел Москва|
            |Код подразделения|12345|
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