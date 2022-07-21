#language: ru
  Функционал: создание заявки

    @Ready
    Сценарий: заявка на проверку персональных данных

      Когда Сотрудник инициатор ППД заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора (ППД)
        И создает заявку с типом Заявка на проверку персональных данных
        И статус заявки "Черновик"
      Тогда заполняет сведения о мероприятии
          |Вид мероприятия        |Проверка достоверности сведений и (или) отдельных документов при оценке соответствия квалификационным требованиям и требованиям к деловой репутации лиц, занимающих должности в клиринговой организации, организатора торговли|
          |Уровень значимости     |Средний                          |
          |Уровень срочности      |Не срочная                       |
          |Обоснование значимости |Проверка соответствия квалификационным требованиям и (или) требованиям к деловой репутации лиц, занимающих должности клиринговой организации, организатора торговли|
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
        И выходит из приложения

      Когда Руководитель подразделения-инициатора ППД заходит в приложение
        И выбирает роль Руководитель подразделения-инициатора
        И выбирает созданную заявку
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Сформирована"
        И выходит из приложения

      Когда Руководитель ГВКиППД ППД заходит в приложение
        И выбирает роль Руководитель УБ/ОБ
        И выбирает сформированную заявку
        И выбирает исполнителя "Машарина Эвелина Андрияновна"
        И нажимает кнопку "Назначить" на виджете "Заявка"
      Тогда статус заявки "Назначена"
        И выходит из приложения

      Когда Сотрудник ГВКиППД ППД заходит в приложение
        И выбирает роль Сотрудник ГВКиППД
        И выбирает назначенную заявку
        И нажимает кнопку "Взять в обработку" на виджете "Заявка"
        И нажимает кнопку "В очередь" на виджете "Заявка"
        И нажимает кнопку "В работу" на виджете "Заявка"
        И статус заявки "В работе"
        И переходит во вкладку "Отчет"
      Тогда заполняет отчет по мероприятию
          |Количество нарушений|2|
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

      Когда Руководитель ГВКиППД ППД заходит в приложение
        И выбирает роль Руководитель УБ/ОБ
        И выбирает заявку с результатом
        И переходит во вкладку "Отчет"
        И переходит во вкладку "Заявка"
        И нажимает кнопку "Согласовать" на виджете "Заявка"
      Тогда статус заявки "Исполнена"
        И выходит из приложения

      Когда Сотрудник инициатор ППД заходит в приложение
        И выбирает роль Сотрудник подразделения-инициатора (ППД)
        И выбирает пункт меню "Заявки"
        И выбирает согласованную заявку
#        И нажимает кнопку "Заявка исполнена" на виджете "Заявка"
      Тогда выходит из приложения