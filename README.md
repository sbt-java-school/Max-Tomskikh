# Max-Tomskikh
#  Truck
Задана грузоподъемность грузовика w (в тоннах). На него последовательно грузятся предметы массами a1, a2,..., an, где ai — масса i-го груза в тоннах. Если при погрузке очередного предмета рабочие замечают, что в случае завершения процесса суммарный вес погруженных предметов превысит грузоподъемность, то погрузка этого предмета не осуществляется (он пропускается). Выведите количество предметов, которые будут погружены в соответствии с алгоритмом выше и их суммарную массу.

---

# TruckMultimap
Выполнение задачи Truck только теперь грузовики храняться в Map,так же добавлен ENUM  с моделями грузовиков.
## Реализация
MultiMap - интерфейс для реализации MultiMap
MultimapList - реализация MultiMap
Truck - класс для создания объектов (Конструктор,get,set)
TruckDao - интерфейс для заполниея объектов
TruckDaoImpl - создание  объектов Truck 
## Итог
Получит опыт использования Collections.

---

# UrlReader

Реализовать отображение в консоли содержимое сайта, ссылка на который задаётся параметром url
Отбработать всевозможные исключения
## Реализация
Main - класс отображает на экран содержимое сайта
UrlException - Обработчик исключений
## Итог
Реализовал отображение содержимого сайта. Получил опыт в обработке исключении и с InputStreamReader.

---

# ClassInfo
С поошью рефлексии отобразить всю иерахию любого класса
Отобразить все конструкторы, поля, методы, интерфейся класса
Проверить, являеться ли метод геттером или сеттером
## Реализация
ClassInfo - основной класс где описаны все методы для получения информации он классе.
Test - тестовый класс в котором составлена иерархия классов,getter,setter для проверки работы программы.
## Итог

Получил опыт работы с рефлексией и научился получать важную информацию о классе, которую в дальнейшем могу использовать.

---

# ProxyCache
Реализовать кэшируший прокси
## Реализация
Cache - интерфейс для аннтоации
ProxyUtils - класс реализует кэшируешйи прокси
Calculator - класс реализации интерфейса калькулятора с двумя фунциями (числа фибоначи и корень квадратный)
CalculatorImpl -интерфейс калькулятора с двумя функциями (числа фибоначи и корень квадратный)
Main - основной класс для запуска программы
## Итог
Реализовал кэшируший прокси. Работа с аннотациями.

---

# BookSerialization
1 Разобраться с методами writeReplace() и readResolve()
2 Реализовать Serialization Proxy Pattern
## Реализация
Book - класс реализующий Serialization Proxy Pattern
BookTest - класс для тестирования работоспособности Serialization Proxy Pattern с помощью junit
## Итог
Опыт работы с Serialization и с методами: writeReplace(),readResolve()

---

# ThreadPoolWithExecutor
Разобраться в потоках и реализовать ThreadPoll с использованием  Executors
## Реализация
SimpleThreadPool - реализация ThreadPool с использованием Executors
DoThread - реализация  работы потока
## Итог
Реализовал ThreadPoll с использованием Executors.

---

# ThreadPoolWithoutExecutor
Реализовать ThreadPool без использования  Executors
## Реализация
BlockingQueue - класс реализующий блокирующую очередь
ThreadWork - класс реализующий работу потока, создание рандомного числа [0,7]
ThreadPool - создание потока
WorkExecutor  - класс выолняющий задачу из очереди ThreadPool 
## Итог 
Реализовал  ThreadPool без использования Executors, создал блокирующую очередь для замены.

---

# BarberShop
Реализовать программу решения задачи "Проблемы спящего парикмахера"
[Спящий парикмахер Wiki](https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D0%BE%D0%B1%D0%BB%D0%B5%D0%BC%D0%B0_%D1%81%D0%BF%D1%8F%D1%89%D0%B5%D0%B3%D0%BE_%D0%BF%D0%B0%D1%80%D0%B8%D0%BA%D0%BC%D0%B0%D1%85%D0%B5%D1%80%D0%B0)
## Реализация
Задача реализована для одного парикмахера поэтому использовал Semaphore.
Client - класс описывающий клиента парикмахерской
BarberShop - класс реализующий работу парикмахерской
Hairdresser - класс описывающий парикмахера
BarberShopTest - тесты junit
## Итог 
Получил опыт и знания по Semaphore и CountDownLatch и их разница

---

# MagicWoman (Гадалка)
Создать Клиент-Сервер которые общаются через сокеты. Сервер (Гадалка загадывает число) клиент должен его отдадать.
## Реализация
Для данной задачи реализован ThreadPoll, благодаря которому сервер реагирует сразу на нескольких клиентов.
MagicWoman - реализация сервера с ThreadPoll (Guessing)
Customer - реализация клиента с классом Listener который создает отдельный поток для вывода ответа от клиента.
## Итог 
Получил опыт в Socket и на более сложном примере использовал предыдущие знания по ThreadPool, больше понимания потока и многопоточности.

---

# Chat
Клиент - Сервер. У клиента есть функции которые он может получить от сервера:
1. getMessages - выводит сообщения, адресуемые текущему пользователю от других участников чата
2. activeUsers - выводит логины текущих участников чата 
3. disconnect - отправляет на сервер сообщение, что пользователь отключается из чата 
4. clientTo>>message -отправляет сообщение message пользователю с логином clientTo

## Реализация
Server - реализация сервера
Customer - реализация клиента

## Итог
Создал фунционал для клиента которые получает дополнительную информацию от сервера
