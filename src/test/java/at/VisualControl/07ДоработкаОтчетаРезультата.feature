#language: ru
  Функционал: доработка отчёта о результате выполнения заявки в ДББР

    @Ready
    Сценарий: №7, доработка отчёта о результате выполнения заявки
#Предусловие для сценария
      Когда Администратор1 заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора
        И создает заявку с типом Заявка на визуальный контроль
        И статус заявки "Черновик"
      Тогда заполняет сведения о мероприятии
          |Вид мероприятия        |Проверка соблюдения режима работы поднадзорных организаций с фото- видеофиксацией|
          |Уровень значимости     |Средний                          |
          |Уровень срочности      |Не срочная                       |
          |Обоснование значимости |Проверка режима работы НФО       |
          |Обоснование срочности  |Не определен                     |
        И нажимает кнопку "Далее" на виджете "Сведения о мероприятии"
      Когда заполняет сведения о проверяемой организации
          |Регистрационный номер|3202|
          |Наименование организации|АО НОКССБАНК|
        И нажимает кнопку "Найти организацию" на виджете "Сведения о проверяемой организации"
        И очищает поля с данными о проверяемой организации
        И заполняет сведения о проверяемой организации
          |ИНН|7710026574|
          |ОГРН|1027700186062|
        И нажимает кнопку "Найти организацию" на виджете "Сведения о проверяемой организации"
        И нажимает кнопку "Далее" на виджете "Сведения о проверяемой организации"
      Тогда заполняет сведения об объекте проверки
          |Индекс|123456|
          |Регион|г. Москва|
          |Город/населенный пункт|Москва|
          |Улица|2-й Войковский проезд|
          |Дом|5|
          |Корпус|а|
          |Помещение|123|
          |Дополнительная информация|Заявка направляется в ДББР в связи с невозможностью проверки паспортных данных через ПК МЭВ|
        И нажимает кнопку "Далее" на виджете "Сведения об объекте проверки"
        И нажимает кнопку "Далее" на виджете "Актуальная информация по заявке"
        И добавляет документ на шаге "Добавление документов"
        И нажимает кнопку "Отправить на согласование" на виджете "Документы"

      Когда выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения

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
        И нажимает кнопку "В очередь" на виджете "Заявка"
        И нажимает кнопку "В работу" на виджете "Заявка"
        И статус заявки "В работе"
        И переходит во вкладку "Отчет"
      Тогда заполняет отчет по мероприятию
          |Дата проведения мероприятия|01.01.2022|
          |Время проведения мероприятия (мин)|10 |
          |Время подготовки документов (мин)|10|
          |Время на оформление отчета (мин)|10|
          |Время на выезд и возвращение (мин)|10|
          |При проведении мероприятия установлено|Нарушений не обнаружено|
          |Информация негативного характера|Не обнаружено|
          |Примечание|Отсутствует|
        И нажимает кнопку "Сохранить" на виджете "Отчет по мероприятию"
        И нажимает кнопку "Отправить отчет на согласование" на виджете "Отчет по мероприятию"
        И выходит из приложения

#Сценарий №7
      Когда Администратор2 заходит в приложение
        И выбирает роль Руководитель ДББР
        И выбирает заявку в работе
        И переходит во вкладку "Отчет"
        И переходит во вкладку "Заявка"
      Тогда нажимает кнопку "Отклонить" на виджете "Заявка"
        И выходит из приложения

      Когда Пользователь1 заходит в приложение
        И выбирает роль Сотрудник ДББР
        И выбирает назначенную заявку
        И переходит во вкладку "Отчет"
      Тогда заполняет отчет по мероприятию
          |Время проведения мероприятия (мин)|15 |
          |Время подготовки документов (мин)|15|
          |Время на оформление отчета (мин)|15|
          |Время на выезд и возвращение (мин)|15|
        И нажимает кнопку "Сохранить" на виджете "Отчет по мероприятию"
        И нажимает кнопку "Отправить отчет на согласование" на виджете "Отчет по мероприятию"
        И выходит из приложения