#language: ru
  Функционал: отправка заявки на уточнение

#    @Ready
    Сценарий: №6, отправка заявки на уточнение
#Предусловие для сценария
      Когда Сотрудник инициатор ВК заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора (ВК)
        И создает заявку с типом Заявка на визуальный контроль
        И статус заявки "Черновик"
      Тогда заполняет сведения о мероприятии
          |Вид мероприятия        |Осмотр места нахождения поднадзорных организаций и их подразделений с фото- видеофиксацией|
          |Уровень значимости     |Средний                          |
          |Уровень срочности      |Не срочная                       |
          |Обоснование значимости |Осмотр места нахождения филиала, внутренних структурных подразделений КО|
          |Обоснование срочности  |Не определен                     |
        И нажимает кнопку "Далее" на виджете "Сведения о мероприятии"
      Когда заполняет сведения о проверяемой организации
          |Регистрационный номер|3202|
          |Наименование организации|АО НОКССБАНК|
        И нажимает кнопку "Найти организацию" на виджете "Сведения о проверяемой организации"
#        И очищает поля с данными о проверяемой организации
#        И заполняет сведения о проверяемой организации
#          |ИНН|7710026574|
#          |ОГРН|1027700186062|
#        И нажимает кнопку "Найти организацию" на виджете "Сведения о проверяемой организации"
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
        И выходит из приложения

      Когда Руководитель подразделения-инициатора заходит в приложение
        И выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения

      Когда Руководитель ГВКиППД ВК заходит в приложение
        И выбирает роль Руководитель УБ/ОБ
        И выбирает сформированную заявку
        И нажимает кнопку "Назначить" на виджете "Заявка"
        И выбирает назначенную заявку
        И выбирает исполнителя "User PU"
        И нажимает кнопку "Изменить исполнителя" на виджете "Заявка"
      Тогда статус заявки "Назначена"
        И выходит из приложения

#Сценарий №6
      Когда Сотрудник ГВКиППД ВК заходит в приложение
        И выбирает роль Сотрудник ГВКиППД
        И выбирает назначенную заявку
        И нажимает кнопку "Взять в обработку" на виджете "Заявка"
      Тогда заполняет результат обработки
          |Обоснование уточнения|Необходимо уточнить данные|
        И нажимает кнопку "Сохранить" на виджете "Результаты обработки заявки"
        И нажимает кнопку "На уточнение" на виджете "Заявка"
        И выходит из приложения

      Когда Руководитель ГВКиППД ВК заходит в приложение
        И выбирает роль Руководитель УБ/ОБ
        И выбирает заявку в работе
      Тогда нажимает кнопку "Отклонить" на виджете "Заявка"
        И выходит из приложения

      Когда Сотрудник ГВКиППД ВК заходит в приложение
        И выбирает роль Сотрудник ГВКиППД
        И выбирает назначенную заявку
      Тогда нажимает кнопку "На уточнение" на виджете "Заявка"
        И выходит из приложения

      Когда Руководитель ГВКиППД ВК заходит в приложение
        И выбирает роль Руководитель УБ/ОБ
        И выбирает заявку в работе
      Тогда нажимает кнопку "Согласовать" на виджете "Заявка"
        И выходит из приложения

      Когда Сотрудник инициатор ВК заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора (ВК)
        И выбирает пункт меню "Заявки"
        И выбирает отправленную на доработку заявку
      Тогда перезаполняет сведения о мероприятии
          |Уровень значимости     |Средний                          |
        И нажимает кнопку "Сохранить" на виджете "Сведения о мероприятии"
        И перезаполняет сведения об объекте проверки
          |Дополнительная информация|Новая дополнительная информация 2|
        И нажимает кнопку "Сохранить" на виджете "Сведения об объекте проверки"
        И нажимает кнопку "Отправить на согласование" на виджете "Заявка"
        И выходит из приложения

      Когда Руководитель подразделения-инициатора заходит в приложение
        И выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения