#language: ru
  Функционал: отмена заявки

    @Ready
    Сценарий: №1, отмена заявки на проверку персональных данных на этапе создания

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
          И нажимает кнопку "Отменить заявку" на виджете "Документы"
          И выходит из приложения
#Дописать проверку введённых данных в отменённой заявке