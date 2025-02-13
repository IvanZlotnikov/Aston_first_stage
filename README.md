Задание проекта
Написать приложение, которое будет реализовывать алгоритм сортировки по переданным классам.
На гитхаб/гитлаб, количество веток с кодом должно соответствовать количеству участников (минимум),
все ветки в итоге должны быть смерджены в master/main ветку. Вся архитектура и код стайл должны 
соответствовать конвенциям языка Java.Программа должна выполняться в цикле.
Выход из цикла возможен только путем соответствующего выбора пользователя.Пользователь должен иметь
возможность выбирать варианты заполнения исходного массива данных (из файла, рандом, вручную) и его длину.
Также у пользователя должна быть возможность найти какой-либо элемент отсортированной коллекции при помощи 
алгоритма бинарного поиска, которые также необходимо реализовать. Сотрировки и бинарный поиск должны быть 
реализованы с использованием дженериков и быть универсальными под любые классы программы. В программе должен 
использовать паттерн стратегия. Сортировать необходимо кастомные классы - класс должен иметь реализованный паттерн Builder.
Для вводимых данных (в тч из файла) должна производится валидация данных.
Доп. задание: дополнительно к основным сортировкам реализовать эти же алгоритмы сортировки таким образом, 
что классы будут сортироваться по какому-либо числовому полю таким образом, что классы с четными значениями будут сортироваться 
в натуральном порядке, а с нечетными оставаться на своих местах.
Доп. доп. задание: Необходимо реализовать функционал для записи отсортированных коллекций/найденных значений в файл в режиме добавления данных
Все классы должны базово имплементировать сортировку по всем 3 полям. Для кастомной сортировки можно использовать компаратор.
Использовать готовые реализации сортировок и поиска нельзя. Работа должна производится с массивами.
Классы: Автобус (Номер, Модель, Пробег),
Пользователь (Имя, Пароль, Почта), 
Студент (Номер группы, Средний балл, Номер зачетной книжки)
Сортировка - Сортировка выбором
