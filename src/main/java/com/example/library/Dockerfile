# Используйте официальный образ Java 17 Runtime Environment
FROM openjdk:17

# Копируйте JAR-файл вашего приложения в контейнер
COPY target/Library-0.0.1-SNAPSHOT.jar app.jar

# Запускаем приложение
ENTRYPOINT ["java","-jar","/app.jar"]