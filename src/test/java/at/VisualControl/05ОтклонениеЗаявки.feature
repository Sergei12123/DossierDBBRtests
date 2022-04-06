#language: ru
  Функционал: отклонение заявки

    @Ready
    Сценарий: №5, отклонение заявки на проверку персональных данных
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