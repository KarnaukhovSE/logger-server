# Создание проекта

Создаваемый сервис предоставляет REST API /log при обращении к 
которому происходит логирование даты и времени вызова REST-а
и при передаче параметра `name` в лог помещается значение этого
параметра (в противном случае помещается значение `defaultLog`)

Для создания проекта необходимо выбрать File - New - Project...

Выбрать springInitializr, defaultURL = http://start.spring.io. Next

Выбрать зависимости Web - SpringWeb. Finish

#Исходный код

`LoggerServerApplication` - главный класс приложения.

`LoggerController` - контроллер, обрабатывающий обращение к REST API
/log. Аннотации `@RestControlleg` и `GetMapping` обеспечивают эту 
возможность.

Для публикации исходного кода проекта на GitHub нужно выбрать в меню
`VCS - Share Project on GitHub`

# Сборка проекта

Собирается приложение командой в терминале Intellij IDEA 

    ./mvnw clean install

# Запуск сервиса

## Запуск из Intellij IDEA
Сервис можно стартовать из среды разработки Intellij IDEA нажав в главном
классе сервиса `LoggerServerApplication` зелёную стрелочку рядом
с именем класса `LoggerServerApplication` или рядом с именем метода
`main`

Второй способ - в окне терминала в Intellij IDEA выполнить команду

    `./mvnw spring-boot:run`

В файле `./src/main/resources/application.properties` прописана переменная
`server.port=9000`, указывающая на каком порту поднимается сервис.
Для обращения к сервису по REST API необходимо в адресной строке браузера
перейти по адресу 

    `http://localhost:9000/log?name=XXX`

при этом в консоли 
сервиса появитья сообщение о вызове сервиса примерно следующего содержания: 

    `2022-09-03 16:41:38.108  INFO 9868 --- [nio-9000-exec-1] ru.kse.loggerserver.LoggerController     : == XXX == Sat Sep 03 16:41:38 MSK 2022 ==`

Также к сервису можно обратиться из консоли командой

    curl http://localhost:9000/log?name=YYY

Для запуска сервиса на другом порту необходимый порт можно указать в файле
`./src/main/resources/application.properties`

## Запуск из терминала

Собрать приложение так, как описано в разделе 
[Сборка проекта](#сборка-проекта). То есть в терминале Intellij IDEA
выполнить команду 

    `./mvnw clean install`. 

При этом должен появиться
каталог `./target` содержащий файл `logger-server-<version>.jar`.

Затем перейти в консоль терминала. Перейти в каталог
`<project_home>/target`
и выполнить команду 

    java -jar ./logger-server-0.0.1-SNAPSHOT.jar

При этом запустится сервис на порту, который был указан в файле
`./src/main/resources/application.properties`
во время сборки сервиса.

Если необходимо запустить сервис с параметрами, отличными от тех, что
были прописаны при сборке в файле
`./src/main/resources/application.properties`,
то требуемые параметры можно указать в командной строке запуска сервиса.
Например, изменить порт, на котором поднимается сервис, можно командой: 

    java -jar ./logger-server-0.0.1-SNAPSHOT.jar --server.port=8080

Также можно создать локальный файл со свойствами и передать его через
параметр командной строки запускаемому сервису. Для этого можно рядом 
jar-файлом создать файл свойств, например `alternative.logger.properties`
прописать в нём переменную, например `server.port=9990` и запустить
сервис командой

    java -jar ./logger-server-0.0.1-SNAPSHOT.jar --spring.config.location=./alternative.logger.properties

