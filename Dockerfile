# Используем образ с Java (можно изменить на 11 если нужно)
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем pom.xml и исходники
COPY pom.xml .
COPY src ./src

# Устанавливаем Maven (можно использовать образ с Maven)
RUN apt-get update && apt-get install -y maven

# Собираем приложение
RUN mvn clean package -DskipTests

# Экспонируем порт
EXPOSE 8080

# Команда запуска
CMD ["java", "-jar", "target/*.jar"]