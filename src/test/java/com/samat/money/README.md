# Помощь
Тесты можно запускать все сразу, нажав правой кнопкой по test.java и нажав **Run** или для отдельной папки/класса/метода. 
Тесты выполняются на тестовом бд. Перед выполнением тесты дропают все таблицы бд и загружают все миграции.

[//]: # (Возможно в будущем добавля логику для запуска тестов по созданию до несколько тестовых авторизаций)

Интеграционные запускаются с контекстом всего приложения и не должны перезапускать приложение.  
Они проверяют не все возможные случаи, а должны показать работоспособность своей части. 

Unit - запускаются только с частью приложения и проверяют широкий спектр приложения

# Интеграционные тесты
## repository
Тесты для репозиториев. Проверяем ORM запросы к бд из-за его возможного изменения миграциями. 
Делаем по 2 теста на один запрос: предполагаемый и ломающий. Первый должен пройти, второй проверить на ошибку. 
## service
Проверяем не все методы, а только с дополнительной логикой (с различием для ролей, входных данных) на желаемые изменения entity
## web 
Проверяются только endpoint'ы на то, можно ли к ним обратиться или нет.
Делаем на каждый endpoint контроллера по тесту + по дополнительному тесту на проверку обработки 403, 404, 502 ошибок.

# Unit
## mapper
Просто проверим все желаемые сценарии на каждый map.